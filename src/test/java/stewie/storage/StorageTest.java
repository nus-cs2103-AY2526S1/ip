package stewie.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import stewie.task.DeadlineTask;
import stewie.task.EventTask;
import stewie.task.TaskList;
import stewie.task.ToDoTask;


/**
 * Tests for {@link Storage}.
 */
class StorageTest {

    @TempDir
    Path tempDir;

    private Path testFile;
    private Storage storage;

    @BeforeEach
    void setUp() {
        testFile = tempDir.resolve("tasks.txt");
        storage = new Storage(testFile.toString());
    }

    @Test
    void saveTasks_andLoadTasks() {
        TaskList taskList = new TaskList();
        taskList.addTask(new ToDoTask("Buy milk"));
        taskList.addTask(new DeadlineTask("Submit report",
                LocalDateTime.of(2025, 9, 20, 18, 0)));
        taskList.addTask(new EventTask("Meeting",
                LocalDateTime.of(2025, 9, 21, 10, 0),
                LocalDateTime.of(2025, 9, 21, 12, 0)));

        storage.saveTasks(taskList);

        TaskList loaded = storage.loadTaskList();

        assertEquals(taskList.size(), loaded.size());
        assertEquals(taskList.getTasks().get(0).getDescription(),
                loaded.getTasks().get(0).getDescription());
    }

    @Test
    void loadTaskList_fileDoesNotExist_returnsEmptyTaskList() {
        Storage newStorage = new Storage(tempDir.resolve("missing.txt").toString());
        TaskList result = newStorage.loadTaskList();
        assertEquals(0, result.size());
    }

    @Test
    void loadTaskList_withCorruptedLine_skipsAndCleansFile() throws IOException {
        Files.write(testFile, (
                "T | 0 | Buy milk\n"
                    + "X | ? | invalid line\n" // corrupted
                    + "D | 1 | Submit report | 20/09/2025 18:00\n"
        ).getBytes());

        TaskList result = storage.loadTaskList();

        // Only 2 valid tasks should remain
        assertEquals(2, result.size());
        assertTrue(result.getTasks().stream()
                .anyMatch(t -> t.getDescription().contains("Buy milk")));
    }
}
