package ryujiTest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ryuji.command.ExitCommand;
import ryuji.command.ListCommand;
import ryuji.command.MarkCommand;
import ryuji.command.UnmarkCommand;
import ryuji.ui.Parser;

public class ParserTest {

    @Test
    void parseListShouldGiveListCommandObject() {
        Parser parser = new Parser();

        assertEquals(parser.parse("list"), new ListCommand("list"));
    }

    @Test
    void parseByeShouldGiveExitCommandObject() {
        Parser parser = new Parser();

        assertEquals(parser.parse("bye"), new ExitCommand("bye"));
    }

    @Test
    void parseMarkShouldGiveMarkCommandObject() {
        Parser parser = new Parser();

        assertEquals(parser.parse("mark 2"), new MarkCommand("mark", 2));
    }

    @Test
    void parseUnmarkShouldGiveUnmarkCommandObject() {
        Parser parser = new Parser();

        assertEquals(parser.parse("unmark 2"), new UnmarkCommand("unmmark", 2));
    }

    @Test
    void parseInvalidShouldGiveNull() {
        Parser parser = new Parser();

        assertNull(parser.parse("bad input"));
    }
}
