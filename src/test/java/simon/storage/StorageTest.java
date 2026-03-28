package simon.storage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.api.Assertions;

import simon.task.Task;
import simon.task.Todo;
import simon.task.Deadline;
import simon.task.Event;

/**
 * Unit tests for the Storage class, namely the load() method.
 */
public class StorageTest {

    /**
     * Tests loading from an empty file. Asserts that the returned task list is empty.
     *
     * @param tempDir Temporary directory provided by JUnit.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void testLoad_emptyFile(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(filePath.toString());
        ArrayList<Task> tasks = storage.load();
        Assertions.assertTrue(tasks.isEmpty());
    }

    /**
     * Tests loading a Todo task from file. Asserts that the task is loaded correctly.
     *
     * @param tempDir Temporary directory from JUnit.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void testLoad_todo(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write("T | 1 | read book\n");
        }
        Storage storage = new Storage(filePath.toString());
        ArrayList<Task> tasks = storage.load();
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertTrue(tasks.get(0) instanceof Todo);
        Assertions.assertEquals("read book", tasks.get(0).getDescription());
        Assertions.assertTrue(tasks.get(0).isDone());
    }

    /**
     * Tests loading a Deadline task from file. Asserts that the task is loaded correctly.
     *
     * @param tempDir Temporary directory from JUnit.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void testLoad_deadline(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write("D | 0 | submit report | 2025-09-01\n");
        }
        Storage storage = new Storage(filePath.toString());
        ArrayList<Task> tasks = storage.load();
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertTrue(tasks.get(0) instanceof Deadline);
        Assertions.assertEquals("submit report", tasks.get(0).getDescription());
        Assertions.assertEquals("2025-09-01", ((Deadline) tasks.get(0)).getBy());
        Assertions.assertFalse(tasks.get(0).isDone());
    }

    /**
     * Tests loading an Event task from file. Asserts that the task is loaded correctly.
     *
     * @param tempDir Temporary directory from JUnit.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void testLoad_event(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write("E | 1 | project meeting | 2025-09-01 10:00 | 2025-09-01 12:00\n");
        }
        Storage storage = new Storage(filePath.toString());
        ArrayList<Task> tasks = storage.load();
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertTrue(tasks.get(0) instanceof Event);
        Assertions.assertEquals("project meeting", tasks.get(0).getDescription());
        Assertions.assertEquals("2025-09-01 10:00", ((Event) tasks.get(0)).getStart());
        Assertions.assertEquals("2025-09-01 12:00", ((Event) tasks.get(0)).getEnd());
        Assertions.assertTrue(tasks.get(0).isDone());
    }

    /**
     * Tests loading from a file with malformed lines. Only valid tasks should be loaded.
     *
     * @param tempDir Temporary directory from JUnit.
     * @throws IOException If an I/O error occurs.
     */
    @Test
    void testLoad_malformedLines(@TempDir Path tempDir) throws IOException {
        Path filePath = tempDir.resolve("tasks.txt");
        try (FileWriter writer = new FileWriter(filePath.toFile())) {
            writer.write("X | 0 | something\n");
            writer.write("D | 1\n");
            writer.write("T | 0 | valid todo\n");
        }
        Storage storage = new Storage(filePath.toString());
        ArrayList<Task> tasks = storage.load();
        Assertions.assertEquals(1, tasks.size());
        Assertions.assertTrue(tasks.get(0) instanceof Todo);
        Assertions.assertEquals("valid todo", tasks.get(0).getDescription());
    }
}