package david;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import david.command.DeleteCommand;
import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;
import david.task.Todo;
import org.junit.jupiter.api.Test;

/*
Used ChatGPT to generate the testcases
 */
class DeleteCommandTest {

    @Test
    void execute_deletesCorrectTask() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("task 1"));
        taskList.addTask(new Todo("task 2"));

        new DeleteCommand("1").execute(taskList, storage);

        assertEquals(1, taskList.size());
        assertEquals("task 2", taskList.getTask(1).getText());
    }

    @Test
    void deleteCommand_nonNumericInput_throwsException() {
        assertThrows(DavidException.class, () -> new DeleteCommand("abc"));
    }

    @Test
    void deleteCommand_outOfBoundsIndex_throwsException() throws DavidException {
        TaskList taskList = new TaskList();
        Storage storage = new Storage("data/tasks.txt");
        taskList.addTask(new Todo("only task"));

        DeleteCommand cmd = new DeleteCommand("5");
        assertThrows(Exception.class, () -> cmd.execute(taskList, storage));
    }
}
