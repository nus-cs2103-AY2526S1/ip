package scribbles.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import scribbles.command.AddEventCommand;
import scribbles.command.Command;
import scribbles.command.ExitCommand;
import scribbles.command.ListCommand;
import scribbles.command.UnknownCommand;
import scribbles.exception.InvalidDateTimeException;
import scribbles.exception.InvalidParamException;
import scribbles.exception.MissingDescriptionException;
import scribbles.exception.ScribblesException;
import scribbles.exception.WrongParamOrderException;

public class ParserTest {
    @Test
    public void parseCommand_unknownCommand_returnsUnknownCommand() throws ScribblesException {
        Command c = Parser.parseCommand("dance");
        assertInstanceOf(UnknownCommand.class, c);
    }

    @Test
    public void parseCommand_byeCommand_returnsExitCommand() throws ScribblesException {
        Command c = Parser.parseCommand("bye");
        assertInstanceOf(ExitCommand.class, c);
    }

    @Test
    public void parseCommand_listCommand_returnsListCommand() throws ScribblesException {
        Command c = Parser.parseCommand("list");
        assertInstanceOf(ListCommand.class, c);
    }

    @Test
    public void parseEvent_validArgs_returnsAddEventCommand() throws ScribblesException {
        Command c = Parser.parseCommand("event project meeting /from 2/12/2025 1400 /to 2/12/2025 1600");
        assertInstanceOf(AddEventCommand.class, c);
    }

    @Test
    public void parseEvent_missingFromOrTo_throwsInvalidParamException() {
        assertThrows(InvalidParamException.class, () -> Parser.parseCommand(
                "event project meeting /from 2/12/2025 1400"));
    }

    @Test
    public void parseEvent_wrongParamOrder_throwsWrongParamOrderException() {
        assertThrows(WrongParamOrderException.class, () -> Parser.parseCommand(
                "event project meeting /to 2/12/2025 1600 /from 2/12/2025 1400"));
    }

    @Test
    public void parseEvent_missingDescription_throwsMissingDescriptionException() {
        assertThrows(MissingDescriptionException.class, () -> Parser.parseCommand(
                "event /from 2/12/2025 1400 /to 2/12/2025 1600"));
    }

    @Test
    public void parseEvent_invalidDateTime_throwsInvalidDateTimeException() {
        assertThrows(InvalidDateTimeException.class, () -> Parser.parseCommand(
                "event meeting /from 2/12/2025 2pm /to 2/12/2025 4pm"));
    }

    @Test
    public void parseDateTime_validFormat_returnsLocalDateTime() throws ScribblesException {
        LocalDateTime dateTime = Parser.parseDateTime("8/10/2025 1800");
        assertEquals(2025, dateTime.getYear());
        assertEquals(10, dateTime.getMonthValue());
        assertEquals(8, dateTime.getDayOfMonth());
        assertEquals(18, dateTime.getHour());
        assertEquals(0, dateTime.getMinute());
    }

    @Test
    public void parseDateTime_invalidFormat_throwsInvalidDateTimeException() {
        assertThrows(InvalidDateTimeException.class, () -> Parser.parseDateTime("November 3rd 2am"));
    }
}
