import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import floydai.command.AddTodoCommand;
import floydai.storage.Storage;
import floydai.task.TaskList;
import floydai.ui.UI;

class AddTodoCommandTest {

    @Test
    void testExecute() throws Exception {
        TaskList tasks = new TaskList();
        UI ui = new UI();
        Storage storage = new Storage("test.txt");
        AddTodoCommand cmd = new AddTodoCommand("todo read book");

        cmd.execute(tasks, ui, storage);

        assertEquals(1, tasks.size());
        assertEquals("read book", tasks.get(0).getDescription());
    }
}
