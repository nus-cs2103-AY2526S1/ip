package karl.storage;

import karl.task.*;
import karl.exception.KarlException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    private final String testFilePath = "data/storage_test.txt";
    private Storage storage;

    @BeforeEach
    public void setUp() {
        storage = new Storage(testFilePath);
        // Ensure test file/dir is clean before each run
        File file = new File(testFilePath);
        if (file.exists()) file.delete();
        File dir = new File("testdata");
        if (dir.exists() && dir.isDirectory()) dir.delete();
    }

    @Test
    public void saveTasks_withTodos_savesAndLoadsCorrectly() throws Exception {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Todo("Read megabytes"));
        tasksToSave.add(new Todo("Eat tacos"));
        tasksToSave.get(1).markAsDone();

        storage.saveTasks(tasksToSave);

        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(2, loaded.size());
        assertEquals("Read megabytes", loaded.get(0).getDescription());
        assertFalse(loaded.get(0).isDone());
        assertEquals("Eat tacos", loaded.get(1).getDescription());
        assertTrue(loaded.get(1).isDone());
    }

    @Test
    public void saveTasks_withDeadlineAndEvent_savesAndLoadsCorrectly() throws Exception {
        ArrayList<Task> tasksToSave = new ArrayList<>();
        tasksToSave.add(new Deadline("do something", "2026-01-01"));
        tasksToSave.add(new Event("party", "2026-02-01", "2026-02-02"));

        storage.saveTasks(tasksToSave);
        ArrayList<Task> loaded = storage.loadTasks();

        assertEquals(2, loaded.size());
        assertTrue(loaded.get(0) instanceof Deadline);
        assertTrue(loaded.get(1) instanceof Event);
        assertEquals("do something", loaded.get(0).getDescription());
        assertEquals("party", loaded.get(1).getDescription());
    }

    @Test
    public void loadTasks_fileDoesNotExist_returnsEmptyList() throws KarlException {
        Storage nonExistentStorage = new Storage("testdata/no_such_file.txt");
        ArrayList<Task> loaded = nonExistentStorage.loadTasks();
        assertNotNull(loaded);
        assertEquals(0, loaded.size());
    }

    @Test
    public void loadTasks_withMalformedLines_skipsMalformedTasks() throws IOException, KarlException {
        // Write a malformed line to the file manually
        File dir = new File("testdata");
        dir.mkdirs();
        File file = new File(testFilePath);
        java.nio.file.Files.write(file.toPath(), (
                "T | 1 | This is valid\n" +
                        "INVALID LINE\n" +
                        "D | 0 | do it | 2027-05-06\n"
        ).getBytes());

        ArrayList<Task> loaded = storage.loadTasks();
        assertEquals(2, loaded.size());
        assertEquals("This is valid", loaded.get(0).getDescription());
        assertTrue(loaded.get(1) instanceof Deadline);
    }

    @Test
    public void saveTasks_ioErrors_throwsKarlException() {
        Storage storageWithBadPath = new Storage("/root/forbidden_dir/notallowed.txt");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("something"));

        assertThrows(KarlException.class, () -> storageWithBadPath.saveTasks(tasks));
    }

    @AfterEach
    public void tearDown() {
        File file = new File(testFilePath);
        if (file.exists()) file.delete();
    }
}
