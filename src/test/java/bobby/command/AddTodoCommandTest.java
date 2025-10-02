package bobby.command;

import bobby.storage.Storage;
import bobby.task.TaskList;
import bobby.task.ToDo;
import bobby.ui.Ui;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AddTodoCommandTest {
    @Test
    void execute_addsTodoToTaskList() throws Exception {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("data/test_tasks.txt");
        AddTodoCommand cmd = new AddTodoCommand("read book");
        String response = cmd.execute(tasks, ui, storage);
        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDo);
        assertTrue(response.contains("Added:"));
        assertTrue(response.contains("read book"));
    }

    @Test
    void execute_nullDescription_throwsAssertionError() {
        assertThrows(AssertionError.class, () -> new AddTodoCommand(null));
    }
}

