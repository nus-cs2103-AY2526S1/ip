package logic.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import commons.exceptions.InvalidCommandException;
import logic.commands.Command;
import logic.commands.DeadlineCommand;
import logic.commands.DeleteCommand;
import logic.commands.EventCommand;
import logic.commands.ExitCommand;
import logic.commands.FindCommand;
import logic.commands.ListCommand;
import logic.commands.MarkCommand;
import logic.commands.ToDoCommand;

class ParserTest {

    @Test
    void parseCommand_exitCommand_returnsExitCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("bye");
        assertInstanceOf(ExitCommand.class, command);
    }

    @Test
    void parseCommand_listCommand_returnsListCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    void parseCommand_invalidCommand_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("invalid command"));
    }

    @Test
    void parseDeleteCommand_validInput_returnsDeleteCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    void parseDeleteCommand_invalidFormat_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("delete"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("delete abc"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("delete -1"));
    }

    @Test
    void parseMarkCommand_validInput_returnsMarkCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("mark 2");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parseUnmarkCommand_validInput_returnsMarkCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("unmark 3");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    void parseMarkCommand_invalidFormat_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("mark"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("mark abc"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("mark -1"));
    }

    @Test
    void parseTodoCommand_validInput_returnsToDoCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("todo read book");
        assertInstanceOf(ToDoCommand.class, command);
    }

    @Test
    void parseTodoCommand_withTag_returnsToDoCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("todo read book /tag urgent");
        assertInstanceOf(ToDoCommand.class, command);
    }

    @Test
    void parseTodoCommand_emptyDescription_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("todo "));
    }

    @Test
    void parseDeadlineCommand_validInput_returnsDeadlineCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("deadline submit assignment /by 2024-12-31");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    void parseDeadlineCommand_withTag_returnsDeadlineCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("deadline submit assignment /by 2024-12-31 /tag school");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    void parseDeadlineCommand_missingDescription_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("deadline /by 2024-12-31"));
    }

    @Test
    void parseDeadlineCommand_missingBy_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("deadline submit assignment"));
    }

    @Test
    void parseDeadlineCommand_invalidDate_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class,
                () -> Parser.parseCommand("deadline submit assignment /by not-a-date"));
    }

    @Test
    void parseEventCommand_validInput_returnsEventCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("event project meeting /from 2024-12-01 /to 2024-12-02");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    void parseEventCommand_withTag_returnsEventCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("event project meeting /from 2024-12-01 /to 2024-12-02 /tag work");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    void parseEventCommand_missingDescription_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("event /from 2024-12-01 /to 2024-12-02"));
    }

    @Test
    void parseEventCommand_missingFromOrTo_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class,
                () -> Parser.parseCommand("event project meeting /from 2024-12-01"));
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("event project meeting /to 2024-12-02"));
    }

    @Test
    void parseEventCommand_invalidDate_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class,
                () -> Parser.parseCommand("event project meeting /from not-a-date /to 2024-12-02"));
        assertThrows(InvalidCommandException.class,
                () -> Parser.parseCommand("event project meeting /from 2024-12-01 /to not-a-date"));
    }

    @Test
    void parseFindCommand_validInput_returnsFindCommand() throws InvalidCommandException {
        Command command = Parser.parseCommand("find keyword");
        assertInstanceOf(FindCommand.class, command);
    }

    @Test
    void parseFindCommand_emptyKeyword_throwsInvalidCommandException() {
        assertThrows(InvalidCommandException.class, () -> Parser.parseCommand("find "));
    }
}
