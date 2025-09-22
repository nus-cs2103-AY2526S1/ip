package jerome.command;

import jerome.TaskList;
import jerome.ui.Ui;
import jerome.storage.Storage;
import jerome.task.Task;
import jerome.task.Todo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddCommandTest {

    @Test
    void testExecuteAddsTask() {
        Task task = new Todo("Test Task");
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage();

        AddCommand command = new AddCommand(task);

        command.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getDescription());
    }
}
