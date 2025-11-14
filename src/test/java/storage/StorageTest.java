package storage;

import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import exception.BaymaxException;


import static org.junit.jupiter.api.Assertions.*;

import storage.Storage;
import task.*;

/**
 * JUnit tests for the storage.Storage class.
 *
 * Tests saving and loading tasks to ensure persistence works correctly.
 */
class StorageTest {

    private Storage storage;
    private Path testFile;

    @BeforeEach
    void setUp() throws IOException {
        testFile = Paths.get("data", "test_baymax.txt");
        Files.createDirectories(testFile.getParent());
        Files.deleteIfExists(testFile);

        List<String> lines = Arrays.asList(
                "T | 0 | Test todo",
                "D | 0 | Test deadline | 2025-08-30",
                "E | 0 | Test event | 2025-08-30 | 2025-09-01"
        );
        Files.write(testFile, lines);

        storage = new Storage(testFile);
    }

    @AfterEach
    void tearDown() throws IOException {
        Files.deleteIfExists(testFile);
    }

    @Test
    void saveAndLoadTasks() throws BaymaxException {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Test todo", TaskType.TODO));
        tasksToSave.add(new Deadline("Test deadline", TaskType.DEADLINE, "30/8/2025"));
        tasksToSave.add(new Event("Test event", TaskType.EVENT, "30/8/2025", "1/9/2025"));

        storage.save(tasksToSave);

        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(3, loadedTasks.size(), "Should load 3 tasks");
        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertTrue(loadedTasks.get(1) instanceof Deadline);
        assertTrue(loadedTasks.get(2) instanceof Event);

        assertEquals("Test todo", loadedTasks.get(0).getDescription());
        assertEquals("Test deadline", loadedTasks.get(1).getDescription());
        assertEquals("Test event", loadedTasks.get(2).getDescription());
    }
}
