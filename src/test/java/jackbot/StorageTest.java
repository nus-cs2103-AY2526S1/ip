package jackbot;

import jackbot.task.Deadline;
import jackbot.task.Event;
import jackbot.task.Task;
import jackbot.task.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    @TempDir
    Path tmp;

    private String path(String name) {
        return tmp.resolve(name).toString();
    }

    @Test
    void saveAndLoad_roundTrip() throws Exception {
        String file = path("tasks.txt");
        Storage storage = new Storage(file);

        List<Task> toSave = new ArrayList<>();
        toSave.add(new Todo("alpha"));
        toSave.add(new Deadline("submit report", "Fri 5pm"));
        toSave.add(new Event("team sync", "Mon 2-3pm", "Fri 6pm"));

        storage.save(toSave);

        List<Task> loaded = storage.load();
        assertEquals(3, loaded.size());
        assertTrue(loaded.get(0).toString().contains("alpha"));
        assertTrue(loaded.get(1).toString().contains("submit report"));
        assertTrue(loaded.get(2).toString().contains("team sync"));
    }

    @Test
    void load_emptyFile_ok() throws Exception {
        String file = path("empty.txt");
        // create empty file
        new File(file).createNewFile();

        Storage storage = new Storage(file);
        List<Task> loaded = storage.load();
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }

    @Test
    void load_withBadLine_reportsErrorAndThrows() throws Exception {
        String file = path("badlines.txt");
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("T|1|good\\|desc|");
            bw.newLine();
            bw.write("BAD|LINE|WILL|BREAK"); // unknown type -> deserialize throws
            bw.newLine();
        }
        Storage storage = new Storage(file);
        // Your Storage propagates an exception on bad line
        assertThrows(JackbotException.class, storage::load);
    }
}
