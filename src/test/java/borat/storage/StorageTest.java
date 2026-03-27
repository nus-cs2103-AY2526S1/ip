package borat.storage;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Path;
import java.nio.file.Files;
import java.util.List;
import java.util.ArrayList;

import borat.task.Task;
import borat.task.ToDo;
import borat.task.Deadline;
import borat.task.Event;

public class StorageTest {

    private Storage storage;
    @TempDir
    Path tempDir;

    @BeforeEach
    void setUp() {
        storage = new Storage(tempDir.resolve("test-tasks.txt").toString());
    }

    @Test
    @DisplayName("Test Storage creation")
    void testStorageCreation() {
        assertNotNull(storage);
    }

    @Test
    @DisplayName("Test loading from non-existent file returns empty list")
    void testLoadFromNonExistentFile() {
        List<Task> tasks = storage.loadTasks();
        assertNotNull(tasks);
        assertEquals(0, tasks.size());
    }

    @Test
    @DisplayName("Test saving and loading ToDo task")
    void testSaveAndLoadToDo() throws Exception {
        List<Task> tasksToSave = new ArrayList<>();
        ToDo todo = new ToDo("Test todo");
        tasksToSave.add(todo);

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof ToDo);
        assertTrue(loadedTasks.get(0).toString().contains("Test todo"));
        assertTrue(loadedTasks.get(0).toString().contains("[ ]"));
    }

    @Test
    @DisplayName("Test saving and loading Deadline task")
    void testSaveAndLoadDeadline() throws Exception {
        List<Task> tasksToSave = new ArrayList<>();
        Deadline deadline = new Deadline("Test deadline", "Dec 31 2023 23:59");
        tasksToSave.add(deadline);

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Deadline);
        assertTrue(loadedTasks.get(0).toString().contains("Test deadline"));
        assertTrue(loadedTasks.get(0).toString().contains("Dec 31 2023 23:59"));
        assertTrue(loadedTasks.get(0).toString().contains("[ ]"));
    }

    @Test
    @DisplayName("Test saving and loading Event task")
    void testSaveAndLoadEvent() throws Exception {
        List<Task> tasksToSave = new ArrayList<>();
        Event event = new Event("Test event", "Dec 31 2023 20:00", "Dec 31 2023 22:00");
        tasksToSave.add(event);

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Event);
        assertTrue(loadedTasks.get(0).toString().contains("Test event"));
        assertTrue(loadedTasks.get(0).toString().contains("Dec 31 2023 20:00"));
        assertTrue(loadedTasks.get(0).toString().contains("Dec 31 2023 22:00"));
        assertTrue(loadedTasks.get(0).toString().contains("[ ]"));
    }

    @Test
    @DisplayName("Test saving and loading marked tasks")
    void testSaveAndLoadMarkedTasks() throws Exception {
        List<Task> tasksToSave = new ArrayList<>();
        ToDo todo = new ToDo("Test todo");
        todo.setDone(true);
        tasksToSave.add(todo);

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0).toString().contains("[X]"));
    }

    @Test
    @DisplayName("Test saving and loading mixed marked/unmarked tasks")
    void testSaveAndLoadMixedMarkedTasks() throws Exception {
        List<Task> tasksToSave = new ArrayList<>();

        ToDo todo1 = new ToDo("Task 1");
        todo1.setDone(true);
        tasksToSave.add(todo1);

        ToDo todo2 = new ToDo("Task 2");
        tasksToSave.add(todo2);

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(2, loadedTasks.size());
        assertTrue(loadedTasks.get(0).toString().contains("[X]"));
        assertTrue(loadedTasks.get(1).toString().contains("[ ]"));
    }

    @Test
    @DisplayName("Test saving empty list")
    void testSaveEmptyList() throws Exception {
        List<Task> emptyList = new ArrayList<>();
        storage.save(emptyList);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(0, loadedTasks.size());
    }

    @Test
    @DisplayName("Test saving tasks with special characters")
    void testSaveTasksWithSpecialCharacters() throws Exception {
        List<Task> tasksToSave = new ArrayList<>();
        ToDo specialTodo = new ToDo("Task with special chars: !@#$%^&*()");
        tasksToSave.add(specialTodo);

        storage.save(tasksToSave);

        List<Task> loadedTasks = storage.loadTasks();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0).toString().contains("Task with special chars: !@#$%^&*()"));
    }

    @Test
    @DisplayName("Test handling corrupted file format smoothly")
    void testHandleCorruptedFileGracefully() throws Exception {
        // create a file with corrupted format
        Path corruptedFile = tempDir.resolve("corrupted-tasks.txt");
        List<String> corruptedLines = new ArrayList<>();
        corruptedLines.add("Invalid format line");
        corruptedLines.add("T | 1 | Valid todo");
        corruptedLines.add("D | 0 | Valid deadline | Dec 31 2023 23:59");
        Files.write(corruptedFile, corruptedLines);

        Storage corruptedStorage = new Storage(corruptedFile.toString());
        List<Task> loadedTasks = corruptedStorage.loadTasks();

        // should just load valid tasks and skip invalid ones
        assertEquals(2, loadedTasks.size());
    }

    @Test
    @DisplayName("Test handling empty file")
    void testHandleEmptyFile() throws Exception {
        Path emptyFile = tempDir.resolve("empty-tasks.txt");
        Files.createFile(emptyFile);

        Storage emptyStorage = new Storage(emptyFile.toString());
        List<Task> loadedTasks = emptyStorage.loadTasks();

        assertEquals(0, loadedTasks.size());
    }
}