package ui;

import command.*;
import exception.BaymaxException;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testGetCommand() {
        assertEquals("todo", Parser.getCommand("todo Buy milk"));
        assertEquals("list", Parser.getCommand("list"));
        assertEquals("bye", Parser.getCommand("bye   "));
    }

    @Test
    void testGetArgs() {
        assertEquals("Buy milk", Parser.getArgs("todo Buy milk"));
        assertEquals("", Parser.getArgs("list"));
        assertEquals("", Parser.getArgs("bye"));
    }

    @Test
    void testParseTodoValid() throws BaymaxException {
        assertEquals("Read book", Parser.parseTodo("Read book"));
    }

    @Test
    void testParseTodoEmpty() {
        BaymaxException ex = assertThrows(BaymaxException.class, () -> Parser.parseTodo(""));
        assertTrue(ex.getMessage().contains("task description is currently 0"));
    }

    @Test
    void testParseDeadlineValid() throws BaymaxException {
        String[] result = Parser.parseDeadline("Submit report /by 2025-09-30");
        assertEquals("Submit report", result[0]);
        assertEquals("2025-09-30", result[1]);
    }

    @Test
    void testParseDeadlineInvalid() {
        assertThrows(BaymaxException.class, () -> Parser.parseDeadline("Submit report"));
        assertThrows(BaymaxException.class, () -> Parser.parseDeadline("/by 2025-09-30"));
        assertThrows(BaymaxException.class, () -> Parser.parseDeadline("Submit report /by "));
    }

    @Test
    void testParseEventValid() throws BaymaxException {
        String[] result = Parser.parseEvent("Team meeting /from 2025-08-30 /to 2025-08-31");
        assertEquals("Team meeting", result[0]);
        assertEquals("2025-08-30", result[1]);
        assertEquals("2025-08-31", result[2]);
    }

    @Test
    void testParseEventInvalid() {
        assertThrows(BaymaxException.class, () -> Parser.parseEvent("Team meeting /from 2025-08-30"));
        assertThrows(BaymaxException.class, () -> Parser.parseEvent("Team meeting /to 2025-08-31"));
        assertThrows(BaymaxException.class, () -> Parser.parseEvent("/from 2025-08-30 /to 2025-08-31"));
        assertThrows(BaymaxException.class, () -> Parser.parseEvent("Team meeting /from /to 2025-08-31"));
    }

    @Test
    void testParseIndexValid() throws BaymaxException {
        assertEquals(0, Parser.parseIndex("1"));
        assertEquals(4, Parser.parseIndex("5"));
        assertEquals(2, Parser.parseIndex(" 3 "));
    }

    @Test
    void testParseFullCommands() throws BaymaxException {
        // Bye command
        assertTrue(Parser.parse("bye") instanceof ByeCommand);

        // List command
        assertTrue(Parser.parse("list") instanceof ListCommand);

        // Todo command
        Command todo = Parser.parse("todo Buy milk");
        assertTrue(todo instanceof TodoCommand);

        // Deadline command
        Command deadline = Parser.parse("deadline Submit /by 2025-09-30");
        assertTrue(deadline instanceof DeadlineCommand);

        // Event command
        Command event = Parser.parse("event Party /from 2025-12-01 /to 2025-12-02");
        assertTrue(event instanceof EventCommand);

        // Find command
        Command find = Parser.parse("find milk");
        assertTrue(find instanceof FindCommand);

        // Mark/Unmark/Delete commands
        assertTrue(Parser.parse("mark 1") instanceof MarkCommand);
        assertTrue(Parser.parse("unmark 1") instanceof UnmarkCommand);
        assertTrue(Parser.parse("delete 1") instanceof DeleteCommand);
    }

    @Test
    void testParseUnknownCommand() {
        BaymaxException ex = assertThrows(BaymaxException.class, () -> Parser.parse("unknown cmd"));
        assertTrue(ex.getMessage().contains("I'm confused"));
    }
}
