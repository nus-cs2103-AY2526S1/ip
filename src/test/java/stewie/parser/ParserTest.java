package stewie.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import stewie.command.ByeCommand;
import stewie.command.Command;
import stewie.command.DeadlineCommand;
import stewie.command.DeleteCommand;
import stewie.command.EventCommand;
import stewie.command.FindCommand;
import stewie.command.ListCommand;
import stewie.command.MarkCommand;
import stewie.command.ToDoCommand;
import stewie.command.UnmarkCommand;
import stewie.command.UpdateCommand;
import stewie.exceptions.CommandException;
import stewie.exceptions.InvalidCommandException;
import stewie.exceptions.UnknownCommandException;



/**
 * Tests for {@link Parser}.
 */
class ParserTest {

    @Test
    void parseCommand_list_returnsListCommand() throws CommandException {
        Command command = Parser.parseCommand("list");
        assertTrue(command instanceof ListCommand);
    }

    @Test
    void parseCommand_mark_returnsMarkCommand() throws CommandException {
        Command command = Parser.parseCommand("mark 1");
        assertTrue(command instanceof MarkCommand);
    }

    @Test
    void parseCommand_unmark_returnsUnmarkCommand() throws CommandException {
        Command command = Parser.parseCommand("unmark 2");
        assertTrue(command instanceof UnmarkCommand);
    }

    @Test
    void parseCommand_todo_returnsToDoCommand() throws CommandException {
        Command command = Parser.parseCommand("todo Buy milk");
        assertTrue(command instanceof ToDoCommand);
    }

    @Test
    void parseCommand_deadline_returnsDeadlineCommand() throws CommandException {
        Command command = Parser.parseCommand("deadline Submit report /by 20/09/2025 18:00");
        assertTrue(command instanceof DeadlineCommand);
    }

    @Test
    void parseCommand_event_returnsEventCommand() throws CommandException {
        Command command = Parser.parseCommand("event Meeting /from 20/09/2025 10:00 /to 20/09/2025 12:00");
        assertTrue(command instanceof EventCommand);
    }

    @Test
    void parseCommand_delete_returnsDeleteCommand() throws CommandException {
        Command command = Parser.parseCommand("delete 3");
        assertTrue(command instanceof DeleteCommand);
    }

    @Test
    void parseCommand_find_returnsFindCommand() throws CommandException {
        Command command = Parser.parseCommand("find homework");
        assertTrue(command instanceof FindCommand);
    }

    @Test
    void parseCommand_update_returnsUpdateCommand() throws CommandException {
        Command command = Parser.parseCommand("update 1 todo Buy milk");
        assertTrue(command instanceof UpdateCommand);
    }

    @Test
    void parseCommand_bye_returnsByeCommand() throws CommandException {
        Command command = Parser.parseCommand("bye");
        assertTrue(command instanceof ByeCommand);
    }

    @Test
    void parseCommand_blankInput_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("   "));
    }

    @Test
    void parseCommand_nullInput_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand(null));
    }

    @Test
    void parseCommand_unknownCommand_throwsUnknownCommandException() {
        assertThrows(UnknownCommandException.class, () -> Parser.parseCommand("foobar something"));
    }
}
