package friday.storage;

import friday.exceptions.FridayTaskDecodeException;
import friday.tasks.Task;
import friday.tasks.ToDos;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FridayStorageTest {
    private static final String TEST_FILE = "test_data/friday_test.txt";

    /**
     * Sets up the test environment before each test.
     */
    @BeforeEach
    void setUp() {
        // Clean up before each test
        File file = new File(TEST_FILE);
        file.getParentFile().mkdirs();
        file.delete();
        FridayStorage.filePath = TEST_FILE;
    }

    /**
     * Cleans up the test environment after each test.
     * @throws FridayTaskDecodeException if reading the file fails
     */
    @Test
    void testFileCreatedIfMissing() throws FridayTaskDecodeException {
        File file = new File(TEST_FILE);
        assertFalse(file.exists());
        FridayStorage.readFileToList();
        assertTrue(file.exists());
    }

    /**
     * Tests writing and reading tasks to/from the file.
     * @throws FridayTaskDecodeException if reading the file fails
     */
    @Test
    void testWriteAndReadTasks() throws FridayTaskDecodeException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new ToDos("Test todo 1", "#test1"));
        tasks.add(new ToDos("Test todo 2", "#test2"));
        FridayStorage.writeListToFile(tasks);

        ArrayList<Task> readTasks = FridayStorage.readFileToList();
        assertEquals(2, readTasks.size());
        assertEquals("Test todo 1", readTasks.get(0).getDescription());
        assertEquals("Test todo 2", readTasks.get(1).getDescription());
    }

    /**
     * Tests writing and reading an empty task list.
     * @throws FridayTaskDecodeException if reading the file fails.
     */
    @Test
    void testWriteEmptyList() throws FridayTaskDecodeException {
        ArrayList<Task> tasks = new ArrayList<>();
        FridayStorage.writeListToFile(tasks);

        ArrayList<Task> readTasks = FridayStorage.readFileToList();
        assertTrue(readTasks.isEmpty());
    }

    /**
     * Tests reading from a corrupted file.
     * @throws Exception if file operations fail.
     */
    @Test
    void testReadCorruptedFile() throws Exception {
        File file = new File(TEST_FILE);
        file.getParentFile().mkdirs();
        try (java.io.FileWriter fw = new java.io.FileWriter(file)) {
            fw.write("corrupted data\n");
        }
        // Should not throw, but return empty or partial list
        ArrayList<Task> readTasks = FridayStorage.readFileToList();
        assertNotNull(readTasks);
    }

    /**
     * Tests that the static filePath is shared across instances.
     */
    @Test
    void testFilePathStatic() {
        FridayStorage storage1 = new FridayStorage("path1.txt");
        FridayStorage storage2 = new FridayStorage("path2.txt");
        assertEquals("path2.txt", FridayStorage.filePath);
    }
}
