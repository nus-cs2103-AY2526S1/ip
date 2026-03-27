package pepe.command;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import pepe.exception.PepeExceptions;
import pepe.storage.Storage;
import pepe.task.Task;
import pepe.task.ToDos;
import pepe.task.tasklist.TaskList;
import pepe.ui.Ui;

class MarkCommandTest {
    private static final String TEST_FILE_PATH = "data/test.txt";
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    @BeforeEach
    void setUp() throws IOException {
        // Reset test file
        File file = new File(TEST_FILE_PATH);
        if (file.exists()) {
            assertTrue(file.delete(), "Failed to delete old test file");
        }
        file.getParentFile().mkdirs();
        assertTrue(file.createNewFile(), "Failed to create test file");

        taskList = new TaskList();
        ui = new Ui();
        storage = new Storage(TEST_FILE_PATH);
    }

    @Test
    void execute_markSingleTask_marksCorrectly() throws PepeExceptions {
        Task task = new ToDos("Task A");
        taskList.addTask(task);

        MarkCommand command = new MarkCommand(new int[]{0});
        command.execute(taskList, ui, storage);

        String response = command.getString();
        assertTrue(response.contains("[X] Task A"), "Task should be marked as done");
    }

    @Test
    void execute_markMultipleTasks_marksAllCorrectly() throws PepeExceptions {
        Task task1 = new ToDos("Task A");
        Task task2 = new ToDos("Task B");
        taskList.addTask(task1);
        taskList.addTask(task2);

        MarkCommand command = new MarkCommand(new int[]{0, 1});
        command.execute(taskList, ui, storage);

        String response = command.getString();
        assertTrue(response.contains("[X] Task A"), "First task should be marked");
        assertTrue(response.contains("[X] Task B"), "Second task should be marked");
    }

    @Test
    void execute_invalidIndex_throwsException() {
        Task task = new ToDos("Task A");
        taskList.addTask(task);

        MarkCommand command = new MarkCommand(new int[]{1}); // invalid index
        PepeExceptions exception = assertThrows(PepeExceptions.class, () -> command.execute(taskList, ui, storage));

        assertTrue(exception.getMessage().contains("There is no task at index: 2"));
    }
}

