package meeseeks.parser;

import meeseeks.command.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseTodo() {
        Command c = Parser.parse("todo read book");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void testParseDeadline() {
        Command c = Parser.parse("deadline return book /by 2/12/2019 1800");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    void testParseUnknown() {
        Command c = Parser.parse("nonsense command");
        assertTrue(c instanceof UnknownCommand);
    }
}
