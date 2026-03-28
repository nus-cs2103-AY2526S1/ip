package chani;

import chani.tasks.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StorageTest {

    private Path tempFile;

    @BeforeEach
    void setup() throws Exception {
        tempFile = Files.createTempFile("test", ".txt");
    }

    @AfterEach
    void cleanup() throws Exception {
        Files.deleteIfExists(tempFile);
    }


    @Test
    void load_EmptyFile_returnsEmptyList() {
        Storage storage = assertDoesNotThrow(() -> new Storage(tempFile.toString()));

        List<Task> tasks = assertDoesNotThrow(storage::load);

        assertTrue(tasks.isEmpty(), "Expected empty list for empty storage file");
    }

    @Test
    void load_TasksFromFile() throws Exception {
        // write a sample task line to file
        String line = "T | 1 | Buy milk";
        Files.writeString(tempFile, line + "\n");

        Storage storage = new Storage(tempFile.toString());
        List<Task> tasks = storage.load();

        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals("Buy milk", task.getDescription());
        assertTrue(task.isDone());
    }

    @Test
    void load_MultipleTasks() throws Exception {
        String content = """
                T | 0 | Task 1
                D | 1 | Task 2 | 2025-09-01
                E | 0 | Task 3 | 2025-09-01 | 2025-09-02
                """;
        Files.writeString(tempFile, content);

        Storage storage = new Storage(tempFile.toString());
        List<Task> tasks = storage.load();

        assertEquals(3, tasks.size());
        assertFalse(tasks.get(0).isDone());
        assertTrue(tasks.get(1).isDone());
        assertFalse(tasks.get(2).isDone());
    }
}
