package tinkerton.command;

import org.junit.jupiter.api.Test;
import tinkerton.core.TinkertonException;
import tinkerton.task.StubTaskList;
import tinkerton.task.ToDo;
import tinkerton.util.StubUi;
import tinkerton.storage.StubSave;

import static org.junit.jupiter.api.Assertions.*;

public class ToDoCommandTest {

    @Test
    public void execute_validToDoCommand_addsTaskAndSaves() throws TinkertonException {
        StubUi ui = new StubUi();
        StubTaskList tasks = new StubTaskList();
        StubSave stubSave = new StubSave();

        ToDoCommand command = new ToDoCommand("todo Buy groceries");
        command.execute(tasks, ui, stubSave);

        assertEquals(1, tasks.size());
        assertTrue(tasks.get(0) instanceof ToDo);
        assertEquals("Buy groceries", tasks.get(0).name());

        assertEquals(3, ui.getPrintedMessages().size());
        assertEquals("Got it, I've added this task:", ui.getPrintedMessages().get(0));
        assertTrue(ui.getPrintedMessages().get(1).contains("[T][ ] Buy groceries"));
        assertEquals("Now you have 1 tasks in the list.", ui.getPrintedMessages().get(2));

        assertEquals(1, stubSave.getSaveCallCount());
        assertEquals(tasks, stubSave.getLastSavedTaskList());
    }

    @Test
    public void execute_emptyDescription_throwsException() {
        StubUi ui = new StubUi();
        StubTaskList tasks = new StubTaskList();
        StubSave stubSave = new StubSave();

        ToDoCommand command = new ToDoCommand("todo");

        assertThrows(TinkertonException.class, () -> {
            command.execute(tasks, ui, stubSave);
        });

        // Verify no task was added
        assertEquals(0, tasks.size());
        assertEquals(0, stubSave.getSaveCallCount());
    }

    @Test
    public void execute_whitespaceOnlyDescription_throwsException() {
        StubUi ui = new StubUi();
        StubTaskList tasks = new StubTaskList();
        StubSave stubSave = new StubSave();

        ToDoCommand command = new ToDoCommand("todo   ");

        assertThrows(TinkertonException.class, () -> {
            command.execute(tasks, ui, stubSave);
        });

        // Verify no task was added
        assertEquals(0, tasks.size());
        assertEquals(0, stubSave.getSaveCallCount());
    }

    @Test
    public void isExit_returnsFalse() {
        ToDoCommand command = new ToDoCommand("todo test");
        assertFalse(command.isExit());
    }
}
