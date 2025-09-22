package jerome.command;

import jerome.TaskList;
import jerome.ui.Ui;
import jerome.storage.Storage;
import jerome.task.Task;
import jerome.task.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MarkCommandTest {

    @Test
    void testExecuteMark() {
        Task task = new Todo("Test Task");
        TaskList tasks = new TaskList();
        tasks.add(task);
        Ui ui = new Ui();
        Storage storage = new Storage();

        MarkCommand command = new MarkCommand(0, false); // Marking the task

        command.execute(tasks, ui, storage);

        assertTrue(task.isDone());
    }

    @Test
    void testExecuteUnmark() {
        Task task = new Todo("Test Task");
        task.mark();  // Initially mark the task
        TaskList tasks = new TaskList();
        tasks.add(task);
        Ui ui = new Ui();
        Storage storage = new Storage();

        MarkCommand command = new MarkCommand(0, true); // Unmarking the task

        command.execute(tasks, ui, storage);

        assertFalse(task.isDone());
    }
}
