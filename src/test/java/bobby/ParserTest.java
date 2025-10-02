package bobby;

import bobby.command.ListCommand;
import bobby.bobbyexception.BobbyException;
import bobby.parser.Parser;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    public void testParseValidCommand() throws BobbyException {
        Parser parser = new Parser();
        assertInstanceOf(ListCommand.class, parser.parse("list"));
    }

    @Test
    public void testParseInvalidCommand() {
        Parser parser = new Parser();
        assertThrows(BobbyException.class, () -> parser.parse("unknown"));
    }

    @Test
    public void testTodoMissingDescription() {
        BobbyException ex = assertThrows(BobbyException.class, () -> Parser.parse("todo "));
        assertTrue(ex.getMessage().contains("the description of a todo cannot be empty"));
    }

    @Test
    public void testDeadlineMissingBy() {
        BobbyException ex = assertThrows(BobbyException.class, () -> Parser.parse("deadline finish homework"));
        assertTrue(ex.getMessage().contains("Deadline must have a /by date famalam"));
    }

    @Test
    public void testEventMissingFromOrTo() {
        BobbyException ex = assertThrows(BobbyException.class, () -> Parser.parse("event party /from 1/1/2025 1200"));
        assertTrue(ex.getMessage().contains("Event must have /from and /to dates bro try again!"));
    }

    @Test
    public void testFindMissingKeyword() {
        BobbyException ex = assertThrows(BobbyException.class, () -> Parser.parse("find "));
        assertTrue(ex.getMessage().contains("the search keyword cant be empty fam"));
    }

    @Test
    public void testViewScheduleMissingDate() {
        BobbyException ex = assertThrows(BobbyException.class, () -> Parser.parse("viewschedule "));
        assertTrue(ex.getMessage().contains("Please specify a date in d/M/yyyy format"));
    }

    @Test
    public void testUnknownCommandShowsHelp() {
        BobbyException ex = assertThrows(BobbyException.class, () -> Parser.parse("foobar"));
        assertTrue(ex.getMessage().contains("Here are the commands I understand"));
        assertTrue(ex.getMessage().contains("date time"));
    }
}
