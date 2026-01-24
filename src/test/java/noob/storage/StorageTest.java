package noob.storage;

import noob.exception.NoobException;
import noob.task.DeadlineTask;
import noob.task.EventTask;
import noob.task.Task;
import noob.task.TaskList;
import noob.task.TaskType;
import noob.task.TodoTask;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {

    @TempDir
    Path tempDir;

    private Path storageFile;
    private Storage storage;

    @BeforeEach
    public void setup() throws Exception {
        storageFile = tempDir.resolve("tasks.txt");
        storage = new Storage(storageFile.toString());
    }

    @Test
    public void constructor_createsFileIfMissing() {
        File file = storageFile.toFile();
        assertTrue(file.exists());
        assertTrue(file.isFile());
    }

    @Test
    public void writeAndRead_todoTask_roundTripSuccess() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("Buy milk"));

        storage.writeTasksToFile(taskList);
        ArrayList<Task> tasks = storage.getListOfTasks();

        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals(TaskType.TODO, task.getType());
        assertEquals("Buy milk", task.getDesc());
        assertFalse(task.isDone());
    }

    @Test
    public void writeAndRead_deadlineTask_roundTripSuccess() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new DeadlineTask("Submit report", "2025-09-20"));

        storage.writeTasksToFile(taskList);
        ArrayList<Task> tasks = storage.getListOfTasks();

        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals(TaskType.DEADLINE, task.getType());
        assertEquals("Submit report", task.getDesc());
        assertEquals("2025-09-20", ((DeadlineTask) task).getIsoDeadline());
    }

    @Test
    public void writeAndRead_eventTask_roundTripSuccess() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new EventTask("Concert", "2025-09-20", "2025-09-21"));

        storage.writeTasksToFile(taskList);
        ArrayList<Task> tasks = storage.getListOfTasks();

        assertEquals(1, tasks.size());
        Task task = tasks.get(0);
        assertEquals(TaskType.EVENT, task.getType());
        assertEquals("Concert", task.getDesc());
        assertEquals("2025-09-20", ((EventTask) task).getIsoFrom());
        assertEquals("2025-09-21", ((EventTask) task).getIsoTo());
    }

    @Test
    public void getListOfTasks_emptyFile_returnsEmptyList() throws Exception {
        ArrayList<Task> tasks = storage.getListOfTasks();
        assertNotNull(tasks);
        assertTrue(tasks.isEmpty());
    }

    @Test
    public void getListOfTasks_invalidDoneStatus_throwsException() throws Exception {
        Files.writeString(storageFile, "TODO | X | Invalid");
        assertThrows(NoobException.class, () -> storage.getListOfTasks());
    }

    @Test
    public void getListOfTasks_invalidTaskType_throwsException() throws Exception {
        Files.writeString(storageFile, "UNKNOWN | 1 | Something");
        assertThrows(NoobException.class, () -> storage.getListOfTasks());
    }

    @Test
    public void getListOfTasks_mismatchedFields_throwsException() throws Exception {
        // DEADLINE but missing deadline date
        Files.writeString(storageFile, "DEADLINE | 1 | Missing date");
        assertThrows(NoobException.class, () -> storage.getListOfTasks());
    }

    @Test
    public void writeTasksToFile_withMultipleTasks_writesAllCorrectly() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("Task 1"));
        taskList.addTask(new DeadlineTask("Task 2", "2025-09-25"));
        taskList.addTask(new EventTask("Task 3", "2025-09-26", "2025-09-27"));

        storage.writeTasksToFile(taskList);
        String fileContent = Files.readString(storageFile);

        assertTrue(fileContent.contains("TODO | 0 | Task 1"));
        assertTrue(fileContent.contains("DEADLINE | 0 | Task 2 | 2025-09-25"));
        assertTrue(fileContent.contains("EVENT | 0 | Task 3 | 2025-09-26 | 2025-09-27"));
    }

    @Test
    public void roundTrip_multipleTasks_consistent() throws Exception {
        TaskList taskList = new TaskList();
        taskList.addTask(new TodoTask("Alpha"));
        taskList.addTask(new DeadlineTask("Beta", "2025-09-30"));
        taskList.addTask(new EventTask("Gamma", "2025-10-01", "2025-10-02"));

        storage.writeTasksToFile(taskList);
        ArrayList<Task> tasks = storage.getListOfTasks();

        TaskList loaded = new TaskList(tasks);
        assertEquals(taskList.getNumTasks(), loaded.getNumTasks());
        assertEquals(taskList.toString(), loaded.toString());
    }
}

