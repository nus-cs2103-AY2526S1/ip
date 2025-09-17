package bob.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import bob.exception.InvalidInputException;
import bob.task.Task;
import bob.task.TaskList;

public class StorageTest {
    @TempDir
    Path tempDir;
    
    private Storage storage;
    private String testFilePath;

    @BeforeEach
    public void setUp() {
        testFilePath = tempDir.resolve("test_tasks.txt").toString();
        storage = new Storage(testFilePath);
    }

    @AfterEach
    public void tearDown() {
        try {
            Files.deleteIfExists(Paths.get(testFilePath));
        } catch (IOException e) {
            // Ignore cleanup errors
        }
    }

    // File creation tests
    @Test
    public void loadTaskList_nonExistentFile_createsEmptyTaskList() throws InvalidInputException, IOException {
        // The storage constructor already creates the file
        TaskList taskList = storage.loadTaskList();
        
        assertTrue(Files.exists(Paths.get(testFilePath)));
        assertTrue(taskList.isEmpty());
    }

    @Test
    public void loadTaskList_existingEmptyFile_returnsEmptyTaskList() throws InvalidInputException, IOException {
        TaskList taskList = storage.loadTaskList();
        
        assertTrue(taskList.isEmpty());
    }

    // Save functionality tests
    @Test
    public void saveTaskList_emptyTaskList_createsEmptyFile() throws InvalidInputException, IOException {
        TaskList taskList = new TaskList(storage);
        
        storage.saveTaskList(taskList);
        
        assertTrue(Files.exists(Paths.get(testFilePath)));
        String content = Files.readString(Paths.get(testFilePath));
        assertEquals("", content);
    }

    @Test
    public void saveTaskList_taskListWithTodos_writesCorrectFormat() throws InvalidInputException, IOException {
        TaskList taskList = new TaskList(storage);
        taskList.add(Task.createFromString("todo read book"));
        taskList.add(Task.createFromString("todo write code"));
        
        storage.saveTaskList(taskList);
        
        String content = Files.readString(Paths.get(testFilePath));
        assertTrue(content.contains("0 todo read book"));
        assertTrue(content.contains("0 todo write code"));
    }

    @Test
    public void saveTaskList_taskListWithMarkedTasks_writesCorrectFormat() throws InvalidInputException, IOException {
        TaskList taskList = new TaskList(storage);
        taskList.add(Task.createFromString("todo read book"));
        taskList.markDone(0);
        
        storage.saveTaskList(taskList);
        
        String content = Files.readString(Paths.get(testFilePath));
        assertTrue(content.contains("1 todo read book"));
    }

    // Load functionality tests
    @Test
    public void loadTaskList_fileWithUnmarkedTodo_createsCorrectTask() throws InvalidInputException, IOException {
        String fileContent = "0 todo read book\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(1, taskList.size());
        assertFalse(taskList.get(0).isDone());
        assertEquals("[T][ ] read book", taskList.get(0).toString());
    }

    @Test
    public void loadTaskList_fileWithMarkedTodo_createsCorrectTask() throws InvalidInputException, IOException {
        String fileContent = "1 todo read book\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(1, taskList.size());
        assertTrue(taskList.get(0).isDone());
        assertEquals("[T][X] read book", taskList.get(0).toString());
    }

    @Test
    public void loadTaskList_fileWithDeadline_createsCorrectTask() throws InvalidInputException, IOException {
        String fileContent = "0 deadline submit report /by 01/01/2025 1200\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(1, taskList.size());
        assertFalse(taskList.get(0).isDone());
        assertTrue(taskList.get(0).toString().contains("[D]"));
        assertTrue(taskList.get(0).toString().contains("submit report"));
    }

    @Test
    public void loadTaskList_fileWithEvent_createsCorrectTask() throws InvalidInputException, IOException {
        String fileContent = "0 event meeting /from 01/01/2025 1000 /to 01/01/2025 1200\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(1, taskList.size());
        assertFalse(taskList.get(0).isDone());
        assertTrue(taskList.get(0).toString().contains("[E]"));
        assertTrue(taskList.get(0).toString().contains("meeting"));
    }

    @Test
    public void loadTaskList_fileWithMultipleTasks_createsAllTasks() throws InvalidInputException, IOException {
        String fileContent = "0 todo read book\n" +
                           "1 deadline submit report /by 01/01/2025 1200\n" +
                           "0 event meeting /from 01/01/2025 1000 /to 01/01/2025 1200\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(3, taskList.size());
        assertFalse(taskList.get(0).isDone()); // todo
        assertTrue(taskList.get(1).isDone());  // deadline
        assertFalse(taskList.get(2).isDone()); // event
    }

    // Error handling tests
    @Test
    public void loadTaskList_invalidTaskFormat_throwsInvalidInputException() throws IOException {
        String fileContent = "0 invalidtask invalid format\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        assertThrows(InvalidInputException.class, () -> {
            storage.loadTaskList();
        });
    }

    @Test
    public void loadTaskList_invalidCompletionStatus_treatAsUnmarked() throws IOException, InvalidInputException {
        String fileContent = "invalid todo read book\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        // The current implementation only checks for "1", so "invalid" would be treated as "0"
        // But if the line has invalid format, it should throw InvalidInputException
        TaskList taskList = storage.loadTaskList();
        assertEquals(1, taskList.size());
        assertFalse(taskList.get(0).isDone()); // Should be treated as unmarked (not "1")
    }

    @Test
    public void loadTaskList_emptyLine_skipsLine() throws InvalidInputException, IOException {
        String fileContent = "0 todo read book\n\n1 todo write code\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(2, taskList.size());
        assertFalse(taskList.get(0).isDone());
        assertTrue(taskList.get(1).isDone());
    }

    @Test
    public void loadTaskList_whitespaceLine_skipsLine() throws InvalidInputException, IOException {
        String fileContent = "0 todo read book\n   \n1 todo write code\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(2, taskList.size());
    }

    // Directory handling tests
    @Test
    public void storage_nonExistentDirectory_createsDirectoryAndFile() throws InvalidInputException, IOException {
        Path newDirPath = tempDir.resolve("newdir");
        String newFilePath = newDirPath.resolve("tasks.txt").toString();
        Storage newStorage = new Storage(newFilePath);
        
        assertTrue(Files.exists(newDirPath));
        assertTrue(Files.exists(Paths.get(newFilePath)));
        
        TaskList taskList = newStorage.loadTaskList();
        assertTrue(taskList.isEmpty());
    }

    // Round-trip tests (save then load)
    @Test
    public void saveAndLoadTaskList_taskList_preservesAllData() throws InvalidInputException, IOException {
        TaskList originalTaskList = new TaskList(storage);
        originalTaskList.add(Task.createFromString("todo read book"));
        originalTaskList.add(Task.createFromString("deadline submit report /by 01/01/2025 1200"));
        originalTaskList.add(Task.createFromString("event meeting /from 01/01/2025 1000 /to 01/01/2025 1200"));
        originalTaskList.markDone(1); // Mark deadline as done
        
        storage.saveTaskList(originalTaskList);
        TaskList loadedTaskList = storage.loadTaskList();
        
        assertEquals(originalTaskList.size(), loadedTaskList.size());
        assertEquals(originalTaskList.get(0).toString(), loadedTaskList.get(0).toString());
        assertEquals(originalTaskList.get(1).toString(), loadedTaskList.get(1).toString());
        assertEquals(originalTaskList.get(2).toString(), loadedTaskList.get(2).toString());
        assertTrue(loadedTaskList.get(1).isDone()); // Deadline should still be marked
    }

    // Edge case tests
    @Test
    public void loadTaskList_fileWithTrailingNewlines_handlesCorrectly() throws InvalidInputException, IOException {
        String fileContent = "0 todo read book\n\n\n";
        Files.writeString(Paths.get(testFilePath), fileContent);
        
        TaskList taskList = storage.loadTaskList();
        
        assertEquals(1, taskList.size());
        assertEquals("[T][ ] read book", taskList.get(0).toString());
    }

    @Test
    public void saveTaskList_overwriteExistingFile_replacesContent() throws InvalidInputException, IOException {
        // First save
        TaskList taskList1 = new TaskList(storage);
        taskList1.add(Task.createFromString("todo read book"));
        storage.saveTaskList(taskList1);
        
        // Second save with different content
        TaskList taskList2 = new TaskList(storage);
        taskList2.add(Task.createFromString("todo write code"));
        storage.saveTaskList(taskList2);
        
        // Load and verify only second content exists
        TaskList loadedTaskList = storage.loadTaskList();
        assertEquals(1, loadedTaskList.size());
        assertTrue(loadedTaskList.get(0).toString().contains("write code"));
        assertFalse(loadedTaskList.get(0).toString().contains("read book"));
    }

    // Constructor tests
    @Test
    public void constructor_validFilePath_createsFileSuccessfully() throws IOException {
        String newFilePath = tempDir.resolve("new_storage.txt").toString();
        Storage newStorage = new Storage(newFilePath);
        
        assertTrue(Files.exists(Paths.get(newFilePath)));
    }

    @Test
    public void constructor_invalidFilePath_throwsRuntimeException() {
        // Try to create a file in a path that contains an invalid character (on Windows)
        String invalidPath = "\0invalid";
        assertThrows(RuntimeException.class, () -> new Storage(invalidPath));
    }
}
