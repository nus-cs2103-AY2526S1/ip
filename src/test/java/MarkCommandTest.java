import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import command.MarkCommand;
import exception.GenieweenieException;
import storage.Storage;
import task.TaskList;
import task.Todo;
import ui.Ui;

/**
 * Tests the MarkCommand class which marks a task as done.
 */
public class MarkCommandTest {

    /**
     * Ensures that marking with an invalid index throws a GenieweenieException.
     */
    @Test
    public void execute_invalidIndex_throwsGenieweenieException() {
        TaskList tasks = new TaskList();
        MarkCommand command = new MarkCommand(1); // invalid since list is empty
        assertThrows(GenieweenieException.class, () -> command.execute(tasks, new Ui(),
                new Storage("test.txt")));
    }

    /**
     * Ensures that a task is marked as done when a valid index is provided.
     */
    @Test
    public void execute_validIndex_marksTaskSuccessfully() throws GenieweenieException {
        TaskList tasks = new TaskList();
        Todo todo = new Todo("Write tests");
        tasks.addTask(todo);
        MarkCommand command = new MarkCommand(1);
        command.execute(tasks, new Ui(), new Storage("test.txt"));
        assertTrue(todo.isDone());
    }

    /** Stub Storage for testing without writing files. */
    static class StorageStub extends Storage {
        public StorageStub() {
            super("stub.txt"); // or any dummy file path
        }
    }
}
