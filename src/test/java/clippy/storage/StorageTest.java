package clippy.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import clippy.ClippyException;
import clippy.task.Task;
import clippy.task.ToDoTask;

/**
 * Test class for Storage functionality.
 * Written with the assistance of GitHub Copilot.
 * The AI mainly helped with the logic of @BeforeEach and @AfterEach methods.
 * The annotations above are used to reset the test environment before and after each test case.
 */
public class StorageTest {
    private static final String TEST_DIR = "test_data";
    private static final String TEST_FILE = "test_data/test_tasks.txt";
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        Files.createDirectories(Paths.get(TEST_DIR));
        Files.deleteIfExists(Paths.get(TEST_FILE));
        storage = new Storage(TEST_FILE);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
        Files.deleteIfExists(Paths.get(TEST_DIR));
    }

    @Test
    void createsDirectoryAndFile_ifNotExist() {
        assertTrue(Files.exists(Paths.get(TEST_FILE)));
    }

    @Test
    void load_returnsEmptyList_ifFileIsEmpty() {
        List<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty(), "Loaded task list should be empty for an empty file");
    }

    @Test
    void saveAndLoadTask() throws ClippyException {
        ToDoTask task = new ToDoTask("JUnit Test ToDo");
        storage.save(List.of(task));
        List<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertEquals("JUnit Test ToDo", loadedTasks.get(0).getDescription());
    }
}
