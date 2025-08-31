package poopiemeow.storage;

import poopiemeow.task.Task;
import poopiemeow.task.Todo;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.exception.EmptyDescriptionException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

class StorageTest {

    @TempDir
    Path tempDir;

    private Storage storage;
    private Path testFilePath;

    @BeforeEach
    void setUp() {
        testFilePath = tempDir.resolve("test-tasks.txt");
        storage = new Storage(testFilePath.toString());
    }

    @Test
    void testStorageCreation() {
        assertNotNull(storage);
    }

    @Test
    void testSaveAndLoadEmptyTaskList() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();

        storage.save(tasks);
        assertTrue(Files.exists(testFilePath));

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(0, loadedTasks.size());
    }

    @Test
    void testSaveAndLoadTodo() throws IOException, EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todo = new Todo("Test todo");
        tasks.add(todo);

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertEquals("Test todo", loadedTasks.get(0).toString().substring(4)); // Remove "[ ] " prefix
        assertFalse(loadedTasks.get(0).getStatusIcon().equals("X"));
    }

    @Test
    void testSaveAndLoadDeadline() throws IOException, EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        tasks.add(deadline);

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Deadline);
        assertTrue(loadedTasks.get(0).toString().contains("Test deadline"));
        assertFalse(loadedTasks.get(0).getStatusIcon().equals("X"));
    }

    @Test
    void testSaveAndLoadEvent() throws IOException, EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        tasks.add(event);

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size());
        assertTrue(loadedTasks.get(0) instanceof Event);
        assertTrue(loadedTasks.get(0).toString().contains("Test event"));
        assertFalse(loadedTasks.get(0).getStatusIcon().equals("X"));
    }

    @Test
    void testSaveAndLoadMultipleTasks() throws IOException, EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Todo 1"));
        tasks.add(new Deadline("Deadline 1", "2023-12-25 1430"));
        tasks.add(new Event("Event 1", "2023-12-25 1400", "2023-12-25 1600"));
        tasks.add(new Todo("Todo 2"));

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(4, loadedTasks.size());

        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertTrue(loadedTasks.get(1) instanceof Deadline);
        assertTrue(loadedTasks.get(2) instanceof Event);
        assertTrue(loadedTasks.get(3) instanceof Todo);
    }

    @Test
    void testSaveAndLoadMarkedTasks() throws IOException, EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        Todo todo = new Todo("Test todo");
        todo.markAsDone();
        tasks.add(todo);

        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        deadline.markAsDone();
        tasks.add(deadline);

        storage.save(tasks);

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(2, loadedTasks.size());
        assertTrue(loadedTasks.get(0).getStatusIcon().equals("X"));
        assertTrue(loadedTasks.get(1).getStatusIcon().equals("X"));
    }

    @Test
    void testLoadNonExistentFile() throws IOException {
        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(0, loadedTasks.size());
    }

    @Test
    void testLoadCorruptedFile() throws IOException {
        // Create a corrupted file
        Files.write(testFilePath, "Invalid format line\n".getBytes());

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(0, loadedTasks.size());
    }

    @Test
    void testLoadFileWithIncompleteLines() throws IOException {
        // Create file with incomplete task data
        Files.write(testFilePath, "T|0|Test todo\nD|0|Incomplete deadline\n".getBytes());

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(1, loadedTasks.size()); // Only the todo should be loaded
        assertTrue(loadedTasks.get(0) instanceof Todo);
    }

    @Test
    void testLoadFileWithMixedValidAndInvalidLines() throws IOException {
        // Create file with mix of valid and invalid data
        String content = "T|0|Valid todo\n" +
                        "Invalid line\n" +
                        "D|0|Valid deadline|2023-12-25 1430\n" +
                        "E|0|Incomplete event\n" +
                        "E|0|Valid event|2023-12-25 1400|2023-12-25 1600\n";
        Files.write(testFilePath, content.getBytes());

        ArrayList<Task> loadedTasks = storage.load();
        assertEquals(3, loadedTasks.size()); // Should load 3 valid tasks

        assertTrue(loadedTasks.get(0) instanceof Todo);
        assertTrue(loadedTasks.get(1) instanceof Deadline);
        assertTrue(loadedTasks.get(2) instanceof Event);
    }

    @Test
    void testSaveCreatesParentDirectories() throws IOException, EmptyDescriptionException {
        Path nestedPath = tempDir.resolve("nested").resolve("deep").resolve("tasks.txt");
        Storage nestedStorage = new Storage(nestedPath.toString());

        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test todo"));

        nestedStorage.save(tasks);
        assertTrue(Files.exists(nestedPath));

        ArrayList<Task> loadedTasks = nestedStorage.load();
        assertEquals(1, loadedTasks.size());
    }

    @Test
    void testFileFormatConsistency() throws IOException, EmptyDescriptionException {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Todo("Test todo"));
        tasks.add(new Deadline("Test deadline", "2023-12-25 1430"));
        tasks.add(new Event("Test event", "2023-12-25 1400", "2023-12-25 1600"));

        storage.save(tasks);

        // Read the raw file content
        String fileContent = Files.readString(testFilePath);
        String[] lines = fileContent.trim().split("\n");

        assertEquals(3, lines.length);
        assertEquals("T|0|Test todo", lines[0]);
        assertEquals("D|0|Test deadline|2023-12-25 1430", lines[1]);
        assertEquals("E|0|Test event|2023-12-25 1400|2023-12-25 1600", lines[2]);
    }
}
