
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import floydai.storage.Storage;
import floydai.task.Task;
import floydai.task.Todo;

class StorageTest {

    @Test
    void testSaveAndLoad() throws Exception {
        File temp = File.createTempFile("tasks", ".txt");
        temp.deleteOnExit();
        Storage storage = new Storage(temp.getAbsolutePath());

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("read book"));
        storage.save(tasks);

        ArrayList<Task> loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals("read book", loaded.get(0).getDescription());
    }
}
