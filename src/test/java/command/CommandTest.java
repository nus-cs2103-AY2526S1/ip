package command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import jerry.command.DeadlineCommand;
import jerry.command.DeleteCommand;
import jerry.command.EventCommand;
import jerry.command.MarkCommand;
import jerry.command.TodoCommand;
import jerry.command.UnmarkCommand;
import jerry.exceptions.InvalidCommandFormatException;
import jerry.exceptions.JerryException;
import jerry.storage.Storage;
import jerry.tasklist.TaskList;
import jerry.ui.Ui;

public class CommandTest {
    private final TaskList taskList = new TaskList();
    private final Ui userInterface = new Ui();
    private final Storage storage = new Storage("");

    @Test
    public void todo_wrongInput_exceptionThrown() {
        try {
            new TodoCommand("todo ");
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("You forgot to describe what your todo is...", error.getMessage());
        }
    }

    @Test
    public void deadline_wrongInput_exceptionThrown() {
        try {
            DeadlineCommand deadlineCommand = new DeadlineCommand("deadline test");
            deadlineCommand.execute(taskList, userInterface, storage);
        } catch (JerryException error) {
            assertEquals("Deadline must have '/by' keyword followed by the due date", error.getMessage());
        }
    }

    @Test
    public void event_wrongInput_exceptionThrown() {
        try {
            EventCommand eventCommand = new EventCommand("event test from test");
            eventCommand.execute(taskList, userInterface, storage);
        } catch (JerryException error) {
            assertEquals("Event must have '/from' and 'to' keywords.", error.getMessage());
        }
    }

    @Test
    public void delete_wrongInput_exceptionThrown() {
        try {
            new DeleteCommand("delete test");
        } catch (JerryException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }

    @Test
    public void mark_wrongInput_exceptionThrown() {
        try {
            new MarkCommand("mark test");
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }

    @Test
    public void mark_wrongInput2_exceptionThrown() {
        try {
            new MarkCommand("mark");
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }
    @Test
    public void unmark_wrongInput_exceptionThrown() {
        try {
            new UnmarkCommand("mark test");
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }

    @Test
    public void unmark_wrongInput2_exceptionThrown() {
        try {
            new UnmarkCommand("mark");
            fail();
        } catch (InvalidCommandFormatException error) {
            assertEquals("Task number must be positive!", error.getMessage());
        }
    }
}
