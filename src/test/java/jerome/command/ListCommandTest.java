package jerome.command;

import jerome.TaskList;
import jerome.task.Todo;
import jerome.ui.Ui;
import jerome.storage.Storage;
import org.junit.jupiter.api.Test;

public class ListCommandTest {

    @Test
    void testExecute() {
        TaskList tasks = new TaskList();
        tasks.add(new Todo("Task 1"));
        tasks.add(new Todo("Task 2"));

        Ui ui = new Ui();
        Storage storage = new Storage();

        ListCommand command = new ListCommand();

        command.execute(tasks, ui, storage);
    }
}
