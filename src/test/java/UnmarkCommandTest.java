import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import command.UnmarkCommand;
import exception.GenieweenieException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Tests the UnmarkCommand class which marks a task as not done.
 */
public class UnmarkCommandTest {

    /**
     * Ensures that unmarking with an invalid index throws a GenieweenieException.
     */
    @Test
    public void executeInvalidIndexThrowsGenieweenieException() {
        TaskList tasks = new TaskList();
        UnmarkCommand command = new UnmarkCommand(1); // invalid since list is empty
        assertThrows(GenieweenieException.class, () -> command.execute(tasks, new Ui(),
                new Storage("test.txt")));
    }

    /**
     * Ensures that a task is unmarked when a valid index is provided.
     */
    @Test
    public void executeValidIndexUnmarksTaskSuccessfully() throws GenieweenieException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("Finish homework");
        todo.markAsDone();
        tasks.addTask(todo);
        UnmarkCommand command = new UnmarkCommand(1);
        command.execute(tasks, new Ui(), new Storage("test.txt"));
        assertFalse(todo.isDone());
    }
}

