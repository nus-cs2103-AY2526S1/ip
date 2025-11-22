import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import command.DeleteCommand;
import exception.GenieweenieException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Tests the DeleteCommand class which deletes a task from the task list.
 */
public class DeleteCommandTest {

    /**
     * Ensures that deleting with an invalid index throws a GenieweenieException.
     */
    @Test
    public void execute_invalidIndex_throwsGenieweenieException() {
        TaskList tasks = new TaskList();
        DeleteCommand command = new DeleteCommand(1); // invalid since list is empty
        assertThrows(GenieweenieException.class, () -> command.execute(tasks, new Ui(), new Storage("test.txt")));
    }

    /**
     * Ensures that a task is deleted when a valid index is provided.
     */
    @Test
    public void execute_validIndex_deletesTaskSuccessfully() throws GenieweenieException {
        TaskList tasks = new TaskList();
        tasks.addTask(new Todo("Read book"));
        DeleteCommand command = new DeleteCommand(1);
        command.execute(tasks, new Ui(), new Storage("test.txt"));
        assertEquals(0, tasks.size());
    }
}
