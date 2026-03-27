package pepe.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

class TodoCommandTest {
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
    void execute_addSingleTodoTask_addsCorrectly() throws PepeExceptions {
        Task task = new ToDos("Read book");
        TodoCommand command = new TodoCommand(task);

        command.execute(taskList, ui, storage);

        assertEquals(1, taskList.size(), "Task list should contain 1 task");
        assertEquals(task, taskList.get(0), "Task in list should match the added task");

        String response = command.getString();
        assertTrue(response.contains("Read book"), "Response should include task name");
        assertTrue(response.contains("Now you have 1 tasks in the list"), "Response should show updated size");
    }

    @Test
    void execute_addMultipleTasks_addsAllCorrectly() throws PepeExceptions {
        Task task1 = new ToDos("Task A");
        Task task2 = new ToDos("Task B");

        TodoCommand command1 = new TodoCommand(task1);
        TodoCommand command2 = new TodoCommand(task2);

        command1.execute(taskList, ui, storage);
        command2.execute(taskList, ui, storage);

        assertEquals(2, taskList.size(), "Task list should contain 2 tasks");
        assertEquals(task1, taskList.get(0), "First task should be Task A");
        assertEquals(task2, taskList.get(1), "Second task should be Task B");
    }
}
