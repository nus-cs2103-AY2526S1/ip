package piper.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import piper.PiperException;

class ParserTest {

    @Test
    void parse_splitsCommandAndArg() throws PiperException {
        Parser.ParsedString ps = Parser.parse("deadline return book /by 2025-09-01");
        assertEquals("return book /by 2025-09-01", ps.arg());
    }

    @Test
    void parse_emptyInput_exceptionThrown() {
        PiperException e = assertThrows(PiperException.class, () -> Parser.parse(""));
        assertEquals("CHIRP CHIRP! Don't think you said anything there. Try tweeting a command!", e.getMessage());
    }

    @Test
    void parseIndex_acceptsNumeric() throws PiperException {
        assertEquals(12, Parser.parseIndex("12"));
    }

    @Test
    void parseIndex_trimsAndRejectsNonNumeric() {
        PiperException e = assertThrows(PiperException.class, () -> Parser.parseIndex(" two "));
        assertEquals("PEEP! Please give me a numeric task index!", e.getMessage());
    }

    @Test
    void parseDeadlineArgs_success() throws PiperException {
        Parser.DeadlineArgs da = Parser.parseDeadlineArgs("return book /by 2025-09-01");
        assertEquals("return book", da.description());
        assertEquals("2025-09-01", da.by());
    }

    @Test
    void parseDeadlineArgs_missingByField_exceptionThrown() {
        assertThrows(PiperException.class, () -> Parser.parseDeadlineArgs("return book /by "));
    }

    @Test
    void parseEventArgs_success() throws PiperException {
        Parser.EventArgs ea = Parser.parseEventArgs("trip /from 2025-09-05 /to 2025-09-10");
        assertEquals("trip", ea.description());
        assertEquals("2025-09-05", ea.from());
        assertEquals("2025-09-10", ea.to());
    }

    @Test
    void parseEventArgs_missingToField_exceptionThrown() {
        assertThrows(PiperException.class, () -> Parser.parseEventArgs("trip /from 2025-09-05 /to "));
    }
}
