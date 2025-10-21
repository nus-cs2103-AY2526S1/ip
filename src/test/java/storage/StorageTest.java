package storage;

import org.junit.jupiter.api.Test;

import task.Task;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    void readFromFile_parsesValidTasksAndIgnoresCorrupted() throws Exception {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

        // Backup original file if present
        List<String> original = Files.exists(path) ? Files.readAllLines(path) : null;

        List<String> lines = new ArrayList<>();
        lines.add("T | 0 | read book");
        lines.add("D | 0 | submit assignment | 2025-09-30");
        lines.add("E | 0 | conference | 2025-12-05 | 2025-12-06");
        lines.add("CORRUPTED LINE");

        // Ensure parent directory exists and write test content
        Files.createDirectories(path.getParent());
        Files.write(path, lines);

        try {
            List<Task> tasks = Storage.readFromFile();

            // Should parse 3 valid tasks and remove corrupted line
            assertEquals(3, tasks.size(), "Should parse exactly 3 valid tasks");

            // Verify each task type and content
            assertTrue(tasks.get(0).toString().contains("read book"));
            assertTrue(tasks.get(1).toString().contains("submit assignment"));
            assertTrue(tasks.get(2).toString().contains("conference"));

            // File should be rewritten without corrupted line
            List<String> written = Files.readAllLines(path);
            assertEquals(3, written.size(), "File should have 3 lines after corrupted removal");
        } finally {
            // Restore original file state
            if (original != null) {
                Files.write(path, original);
            } else {
                Files.deleteIfExists(path);
            }
        }
    }

    @Test
    void storage_isEmpty_whenNoTasks() throws Exception {
        Path path = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");
        List<String> original = Files.exists(path) ? Files.readAllLines(path) : null;

        // Write empty file
        Files.createDirectories(path.getParent());
        Files.write(path, new ArrayList<>());

        try {
            Storage storage = new Storage();
            assertTrue(storage.isEmpty(), "Storage should be empty when file has no tasks");
        } finally {
            if (original != null) {
                Files.write(path, original);
            } else {
                Files.deleteIfExists(path);
            }
        }
    }
}
