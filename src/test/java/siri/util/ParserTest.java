package siri.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {

    @Test
    void keywordAndArgument_areSplitCorrectly() {
        Parser p = new Parser("todo read book");
        assertEquals("todo", p.getKeyword());
        assertEquals("read book", p.getArgument());
    }

    @Test
    void parseDeadline_happyPath_returnsDescAndBy() throws Exception {
        Parser p = new Parser("deadline return book /by 2025-10-10");
        String[] parts = p.parseDeadline();
        assertArrayEquals(new String[] { "return book", "2025-10-10" }, parts);
    }
}


