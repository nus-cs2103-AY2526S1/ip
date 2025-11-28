package nailongbot.utils;

import nailongbot.command.*;
import nailongbot.exception.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tester for Parser Class
class ParserTest {

    @Test
    void parseExitCommand() throws JkBotException {
        assertTrue(Parser.parse("bye") instanceof ExitCommand);
    }

    @Test
    void parseListCommand() throws JkBotException {
        assertTrue(Parser.parse("list") instanceof ListCommand);
    }

    @Test
    void parseMarkCommand() throws JkBotException {
        MarkCommand cmd = (MarkCommand) Parser.parse("mark 2");
        assertEquals(1, cmd.getIndex());
    }

    @Test
    void parseUnmarkCommand() throws JkBotException {
        UnmarkCommand cmd = (UnmarkCommand) Parser.parse("unmark 3");
        assertEquals(2, cmd.getIndex());
    }

    @Test
    void parseAddTodoCommand() throws JkBotException {
        AddTodoCommand cmd = (AddTodoCommand) Parser.parse("todo Read book");
        assertEquals("Read book", cmd.getDescription());
    }

    @Test
    void parseAddDeadlineCommand() throws JkBotException {
        AddDeadlineCommand cmd = (AddDeadlineCommand) Parser.parse("deadline Submit report /by 28/8/2025");
        assertEquals("Submit report /by 28/8/2025", cmd.getInput());
    }

    @Test
    void parseAddEventCommand() throws JkBotException {
        AddEventCommand cmd = (AddEventCommand) Parser.parse("event Conference /from 28/8/2025 10:00 /to 28/8/2025 18:00");
        assertEquals("Conference /from 28/8/2025 10:00 /to 28/8/2025 18:00", cmd.getArguments());
    }

    @Test
    void parseDeleteCommand() throws JkBotException {
        DeleteCommand cmd = (DeleteCommand) Parser.parse("delete 4");
        assertEquals(3, cmd.getIndex());
    }

    @Test
    void parseShowCommand() throws JkBotException {
        ShowCommand cmd = (ShowCommand) Parser.parse("show 28/8/2025");
        assertEquals("28/8/2025", cmd.getDateString());
    }


    @Test
    void parseUnknownCommand() throws JkBotException {
        assertTrue(Parser.parse("foobar") instanceof UnknownCommand);
    }

    @Test
    void parseEmptyInputThrowsException() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse(""));
    }

    @Test
    void parseMissingArgumentsThrowsException() {
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("todo"));
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("deadline"));
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("event"));
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("mark"));
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("unmark"));
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("delete"));
        assertThrows(EmptyDescriptionException.class, () -> Parser.parse("show"));
    }
}
