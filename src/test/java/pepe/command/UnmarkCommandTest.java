package pepe.command;

import static org.junit.jupiter.api.Assertions.assertFalse;
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

class UnmarkCommandTest {
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
    void execute_unmarkSingleTask_marksCorrectly() throws PepeExceptions {
        Task task = new ToDos("Test Task");
        task.markTask();
        taskList.addTask(task);

        assertTrue(taskList.get(0).checkMarked() == 1, "Task should initially be marked");

        UnmarkCommand command = new UnmarkCommand(new int[]{0});
        command.execute(taskList, ui, storage);

        assertFalse(taskList.get(0).checkMarked() == 1, "Task should be unmarked");
        String response = command.getString();
        assertTrue(response.contains("Test Task"), "Response should include task name");
        assertTrue(response.contains("I knew it!"), "Response should include unmark message");
    }

    @Test
    void execute_unmarkMultipleTasks_marksAllCorrectly() throws PepeExceptions {
        Task task1 = new ToDos("Task A");
        Task task2 = new ToDos("Task B");
        task1.markTask();
        task2.markTask();
        taskList.addTask(task1);
        taskList.addTask(task2);

        UnmarkCommand command = new UnmarkCommand(new int[]{0, 1});
        command.execute(taskList, ui, storage);

        assertFalse(taskList.get(0).checkMarked() == 1, "Task A should be unmarked");
        assertFalse(taskList.get(1).checkMarked() == 1, "Task B should be unmarked");
        String response = command.getString();
        assertTrue(response.contains("Task A"));
        assertTrue(response.contains("Task B"));
    }

    @Test
    void execute_invalidIndex_throwsException() {
        Task task = new ToDos("Task X");
        taskList.addTask(task);

        UnmarkCommand command = new UnmarkCommand(new int[]{1}); // invalid index
        PepeExceptions exception = assertThrows(PepeExceptions.class, () -> command.execute(taskList, ui, storage));

        assertTrue(exception.getMessage().contains("There is no task at index"));
    }
}
