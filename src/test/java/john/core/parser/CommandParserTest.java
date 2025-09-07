package john.core.parser;

import john.core.command.*;
import john.core.exception.ParseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommandParserTest {

    @Test
    void parse_mark_valid() throws Exception {
        Command cmd = CommandParser.parse("mark 3");
        assertInstanceOf(MarkCommand.class, cmd);
    }

    @Test
    void parse_deadline_valid() throws Exception {
        Command cmd = CommandParser.parse("deadline Submit report /by 05/09/2025 16:30:45");
        assertInstanceOf(AddDeadlineCommand.class, cmd);
    }

    @Test
    void parse_deadline_missing_by_throws() {
        ParseException ex = assertThrows(ParseException.class,
                () -> CommandParser.parse("deadline Submit report"));
        assertTrue(ex.getMessage().toLowerCase().contains("usage"));
    }

    @Test
    void parse_unknown_command_throws() {
        assertThrows(ParseException.class, () -> CommandParser.parse("abracadabra"));
    }
}