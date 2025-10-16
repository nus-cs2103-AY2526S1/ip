package khat.command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import khat.exception.KhatException;
import khat.storage.Storage;
import khat.task.TaskList;
import khat.ui.Ui;

public class AddCommandTest {

    @Test
    void addTodo_addNewTodo_taskIsAdded() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test/test.txt");
        AddCommand cmd = new AddCommand("Read book", "todo");
        cmd.execute(tasks, ui, storage);
        assertEquals(1, tasks.getSize());
        assertEquals("Read book", tasks.getTask(0).getDescription());
    }

    @Test
    void addDeadline_addNewDeadline_taskIsAdded() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test/test.txt");
        AddCommand cmd = new AddCommand("Submit report", "deadline", "31-12-2024");
        cmd.execute(tasks, ui, storage);
        assertEquals(1, tasks.getSize());
        assertEquals("Submit report", tasks.getTask(0).getDescription());
    }

    @Test
    void addEvent_addNewEvent_taskIsAdded() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test/test.txt");
        AddCommand cmd = new AddCommand("Team meeting", "event", "01-06-2024", "02-06-2024");
        cmd.execute(tasks, ui, storage);
        assertEquals(1, tasks.getSize());
        assertEquals("Team meeting", tasks.getTask(0).getDescription());
    }

    @Test
    void addTodo_addDuplicateTodo_onlyOneIsAdded() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test/test.txt");
        AddCommand cmd1 = new AddCommand("Read book", "todo");
        AddCommand cmd2 = new AddCommand("Read book", "todo");
        cmd1.execute(tasks, ui, storage);
        cmd2.execute(tasks, ui, storage);
        assertEquals(1, tasks.getSize());
    }

    @Test
    void addTask_invalidType_exceptionIsThrown() {
        TaskList tasks = new TaskList();
        Ui ui = new Ui();
        Storage storage = new Storage("test/test.txt");
        AddCommand cmd = new AddCommand("Invalid", "unknown");
        assertThrows(KhatException.class, () -> cmd.execute(tasks, ui, storage));
    }
}
