package mikey.storage;

import mikey.task.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Claude AI was used for implementing these tests
class StorageTest {
    @TempDir
    Path tempDir;

    private Storage storage;
    private Path testFile;

    @BeforeEach
    void setUp() {
        testFile = tempDir.resolve("test_data.txt");
        storage = new Storage(testFile.toString());
    }

    @Test
    @DisplayName("Should save and load tasks correctly")
    void testSaveAndLoad() throws Exception {
        // Create test tasks
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test todo"));
        tasks.add(new Deadline("Test deadline", LocalDateTime.of(2024, 1, 1, 12, 0)));

        // Save tasks
        storage.save(tasks);

        // Load tasks
        ArrayList<Task> loadedTasks = storage.load();

        assertEquals(2, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertTrue(loadedTasks.get(1) instanceof Deadline);
    }

    @Test
    @DisplayName("Should handle empty file")
    void testLoadEmptyFile() throws IOException {
        Files.createFile(testFile);
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("Should handle non-existent file")
    void testLoadNonExistentFile() {
        ArrayList<Task> tasks = storage.load();
        assertTrue(tasks.isEmpty());
    }

    @Test
    @DisplayName("Should preserve task completion status")
    void testTaskCompletionStatus() throws Exception {
        List<Task> tasks = new ArrayList<>();
        Todo completedTodo = new Todo("Completed task");
        completedTodo.markDone();
        tasks.add(completedTodo);

        storage.save(tasks);
        ArrayList<Task> loaded = storage.load();

        assertTrue(loaded.get(0).isDone());
    }

    @Test
    @DisplayName("Should handle corrupted data gracefully")
    void testCorruptedData() throws IOException {
        // Write invalid data to file
        Files.writeString(testFile, "invalid|data|here\nZ|1|invalid type\n");

        ArrayList<Task> tasks = storage.load();
        // Should return empty list instead of crashing
        assertTrue(tasks.isEmpty() || tasks.size() < 2);
    }

    @Test
    @DisplayName("Should handle tagged tasks")
    void testTaggedTasks() throws Exception {
        List<Task> tasks = new ArrayList<>();
        Todo taggedTodo = new Todo("Tagged task");
        taggedTodo.setTag("important");
        tasks.add(taggedTodo);

        storage.save(tasks);
        ArrayList<Task> loaded = storage.load();

        assertTrue(loaded.get(0).isTagged());
    }
}