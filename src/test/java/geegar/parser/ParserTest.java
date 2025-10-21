package geegar.parser;

import geegar.command.*;
import geegar.exception.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    // Valid Inputs

    @Test
    public void parse_validTodoCommand_addCommandReturned() throws GeegarException {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parse_validDeadlineCommand_addCommandReturned() throws GeegarException {
        Command command = Parser.parse("deadline read book /by 28/08/2025 1800");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parse_validEventCommand_addCommandReturned() throws GeegarException {
        Command command = Parser.parse("event project meeting /from 28/08/2025 1800 /to 28/08/2025 1900");
        assertInstanceOf(AddCommand.class, command);
    }

    @Test
    public void parse_validMarkCommand_markCommandReturned() throws GeegarException {
        Command command = Parser.parse("mark 1");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void parse_validUnmarkCommand_unMarkCommandReturned() throws GeegarException {
        Command command = Parser.parse("unmark 1");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void parse_validByeCommand_exitCommandReturned() throws GeegarException {
        Command comand = Parser.parse("bye");
        assertInstanceOf(ExitCommand.class, comand);
    }

    // invalid inputs

    @Test
    public void parse_emptyString_unknownCommandExceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> {
            Parser.parse("");
        });
    }

    @Test
    public void parse_unknownCommand_unknownCommandExceptionThrown() {
        assertThrows(UnknownCommandException.class, () -> {
            Parser.parse("unknown command");
        });
    }

    @Test
    public void parse_todoWithoutDescription_emptyDescriptionExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parse("todo");
        });
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parse("todo   ");
        });
    }

    @Test
    public void parse_deadlineWithoutBy_invalidFormatDeadlineExceptionThrown() {
        assertThrows(InvalidFormatDeadlineException.class, () -> {
            Parser.parse("deadline return book");
        });
    }

    @Test
    public void parse_deadlineWithoutDescription_emptyDescriptionExceptionThrown() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parse("deadline");
        });
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parse("deadline  /by 28/08/2025 1800");
        });
    }

    @Test
    public void parse_deadlineWithInvalidDate_invalidFormatDeadlineExceptionThrown() {
        assertThrows(InvalidFormatDeadlineException.class, () -> {
            Parser.parse("deadline return book /by invalid-date");
        });
    }

    @Test
    public void parse_eventWithoutFrom_invalidFormatEventExceptionThrown() {
        assertThrows(InvalidFormatEventException.class, () -> {
            Parser.parse("event meeting");
        });
    }


    @Test
    public void parse_eventWithoutTo_invalidFormatEventExceptionThrown() {
        assertThrows(InvalidFormatEventException.class, () -> {
            Parser.parse("event meeting /from 28/08/2025 1800");
        });
    }

    @Test
    public void parse_markWithoutNumber_invalidTaskNumberExceptionThrown() {
        assertThrows(InvalidTaskNumberException.class, () -> {
            Parser.parse("mark");
        });
    }

    @Test
    public void parse_markWithInvalidNumber_invalidTaskNumberExceptionThrown() {
        assertThrows(InvalidTaskNumberException.class, () -> {
            Parser.parse("mark invalid_number");
        });
    }
}
