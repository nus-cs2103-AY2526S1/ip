package david;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import david.command.TodoCommand;
import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;
import david.task.Task;
import david.task.Todo;
import org.junit.jupiter.api.Test;

/*
Used ChatGPT to generate the testcases
 */
class TodoCommandTest {

    @Test
    void execute_addsTodo_correctly() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        TodoCommand cmd = new TodoCommand("read book");

        cmd.execute(taskList, storage);

        assertEquals(1, taskList.size());
        Task added = taskList.getTask(1);
        assertTrue(added instanceof Todo);
        assertEquals("read book", added.getText());
    }

    @Test
    void execute_addsMultipleTodos() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        new TodoCommand("task 1").execute(taskList, storage);
        new TodoCommand("task 2").execute(taskList, storage);

        assertEquals(2, taskList.size());
        assertEquals("task 1", taskList.getTask(1).getText());
        assertEquals("task 2", taskList.getTask(2).getText());
    }

    @Test
    void todoCommand_emptyDescription_throwsException() {
        assertThrows(Exception.class, () -> new TodoCommand(""));
    }
}

