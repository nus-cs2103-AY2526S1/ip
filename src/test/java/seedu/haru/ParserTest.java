package seedu.haru;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {
    @Test
    void testGetCommand() {
        assertEquals("todo", Parser.getCommand("todo read book"));
        assertEquals("deadline", Parser.getCommand("deadline submit /by 2025-10-30"));
        assertEquals("bye", Parser.getCommand("bye"));
    }

    @Test
    void testGetArguments() {
        assertEquals("read book", Parser.getArguments("todo read book"));
        assertEquals("submit /by 2025-10-30", Parser.getArguments("deadline submit /by 2025-10-30"));
        assertEquals("", Parser.getArguments("bye"));
    }

    @Test
    void testParseDeadlineValid() throws HaruException {
        String[] result = Parser.parseDeadline("submit assignment /by 2025-10-30");
        assertEquals("submit assignment", result[0]);
        assertEquals("2025-10-30", result[1]);
    }

    @Test
    void testParseDeadlineInvalid() {
        assertThrows(HaruException.class, () -> Parser.parseDeadline("submit assignment"));
        assertThrows(HaruException.class, () -> Parser.parseDeadline("submit assignment /by "));
    }

    @Test
    void testParseEventValid() throws HaruException {
        String[] result = Parser.parseEvent("meeting /from 10:00 /to 12:00");
        assertEquals("meeting", result[0]);
        assertEquals("12:00", result[1]);
        assertEquals("10:00", result[2]);
    }

    @Test
    void testParseEventInvalid() {
        assertThrows(HaruException.class, () -> Parser.parseEvent("meeting /from 10:00"));
        assertThrows(HaruException.class, () -> Parser.parseEvent("meeting /from  /to 12:00"));
        assertThrows(HaruException.class, () -> Parser.parseEvent("meeting /from 10:00 /to "));
    }


}
