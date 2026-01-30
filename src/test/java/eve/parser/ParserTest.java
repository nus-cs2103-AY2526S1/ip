package eve.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {

    @Test
    void parseCommandVariousTokensOk() {
        assertEquals(Parser.Command.TODO, Parser.parseCommand("todo read"));
        assertEquals(Parser.Command.DEADLINE, Parser.parseCommand("deadline x /by 2019-12-02"));
        assertEquals(Parser.Command.EVENT, Parser.parseCommand("event m /from a /to b"));
        assertNull(Parser.parseCommand("unknownStuff"));
    }

    @Test
    void parseDeadlineGoodAndBadInputs() throws EveException {
        Parser.DeadlineParts p = Parser.parseDeadline("return book /by 2019-12-02");
        assertEquals("return book", p.desc);
        assertEquals("2019-12-02", p.when);

        EveException ex1 = assertThrows(EveException.class,
                () -> Parser.parseDeadline("noByHere"));
        assertTrue(ex1.getMessage().startsWith("Oops, I need more info."));

        EveException ex2 = assertThrows(EveException.class,
                () -> Parser.parseDeadline("  /by   "));
        assertTrue(ex2.getMessage().startsWith("Oops, I need more info."));
    }

    @Test
    void parseEventChecksOrderIfParsableAllowsRawOtherwise() throws EveException {
        // valid range
        Parser.EventParts ok = Parser.parseEvent("mtg /from 2019-12-02 1400 /to 2019-12-02 1600");
        assertEquals("mtg", ok.desc);

        // invalid range (both parse → reject)
        EveException badRange = assertThrows(EveException.class,
                () -> Parser.parseEvent("oops /from 12 2 2019 12:00 /to 12 2 2018 12:00"));
        assertTrue(badRange.getMessage().contains("start is after end"));

        // one side not parseable → allowed (we can’t compare)
        Parser.EventParts raw = Parser.parseEvent("orient /from next Mon 2pm /to 4pm");
        assertEquals("next Mon 2pm", raw.from);
        assertEquals("4pm", raw.to);
    }

    @Test
    void parseEventMissingPiecesErrorsMatch() {
        EveException noFrom = assertThrows(EveException.class,
                () -> Parser.parseEvent("x /to 2019-12-02 10:00"));
        assertTrue(noFrom.getMessage().startsWith("Oops, I need more info."));

        EveException noTo = assertThrows(EveException.class,
                () -> Parser.parseEvent("x /from 2019-12-02 10:00"));
        assertTrue(noTo.getMessage().startsWith("Oops, I need more info."));
    }
}
