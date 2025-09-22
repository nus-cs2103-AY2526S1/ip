package jerome.command;

import jerome.TaskList;
import jerome.task.Todo;
import jerome.ui.Ui;
import jerome.storage.Storage;
import jerome.task.Task;
import jerome.util.JeromeException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeleteCommandTest {

    @Test
    void testExecuteValidIndex() throws JeromeException {
        Task task = new Todo("Test Task");
        TaskList tasks = new TaskList();
        tasks.add(task);
        Ui ui = new Ui();
        Storage storage = new Storage();

        DeleteCommand command = new DeleteCommand(0);

        command.execute(tasks, ui, storage);

        assertEquals(0, tasks.size());
    }

    @Test
    void testExecuteInvalidIndex() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        DeleteCommand command = new DeleteCommand(0);

        assertThrows(JeromeException.class, () -> command.execute(tasks, ui, storage));
    }
}

