package bobby;

import bobby.storage.Storage;
import bobby.task.Deadline;
import bobby.task.Task;
import bobby.task.ToDo;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import java.util.ArrayList;

class StorageTest {
    private static final String TEST_FILE = "data/test_storage_tasks.txt";

    @BeforeEach
    void cleanUp() {
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void saveAndLoadTasks_success() throws Exception {
        Storage storage = new Storage(TEST_FILE);
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDo("task1"));
        tasks.add(new Deadline("task2", "18/9/2025 1200"));
        storage.saveTasks(tasks);
        ArrayList<Task> loaded = storage.loadTasks();
        assertEquals(2, loaded.size());
        assertTrue(loaded.get(0) instanceof ToDo);
        assertTrue(loaded.get(1) instanceof Deadline);
        assertEquals("task1", loaded.get(0).getDescription());
        assertEquals("task2", loaded.get(1).getDescription());
    }

    @Test
    void loadTasks_fileNotExist_returnsEmptyList() throws Exception {
        Storage storage = new Storage("data/nonexistent_file.txt");
        ArrayList<Task> loaded = storage.loadTasks();
        assertNotNull(loaded);
        assertTrue(loaded.isEmpty());
    }
}

