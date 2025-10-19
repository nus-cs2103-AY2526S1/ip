package reboot;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import reboot.task.Task;
import reboot.task.Todo;

// Tests are recommended by ChatGPT.
public class StorageTest {
    private static final String TEST_FILE = "test_storage.txt";

    @AfterEach
    public void cleanup() {
        new File(TEST_FILE).delete();
    }

    @Test
    public void saveAndLoad_tasksPersisted() throws Exception {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book", false));

        Storage storage = new Storage(TEST_FILE);
        storage.writeFile(tasks);

        TaskList loaded = new TaskList(storage.load());
        assertEquals(1, loaded.size());
        assertEquals("read book", loaded.get(0).getDescription());
    }

    @Test
    public void load_nonexistentFile_returnsEmpty() throws Exception {
        Storage storage = new Storage("no_such_file.txt");
        TaskList loaded = new TaskList(storage.load());
        assertEquals(0, loaded.size());
    }
}
