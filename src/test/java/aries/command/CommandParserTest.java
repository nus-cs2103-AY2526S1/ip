package aries.command;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import aries.AriesException;

public class CommandParserTest {
    @Test
    public void testParseTodo() throws AriesException {
        Command command = CommandParser.parse("todo read book");
        assertEquals(ToDoCommand.class, command.getClass());
    }

    @Test
    public void testParseDeadline() throws AriesException {
        Command command = CommandParser.parse("deadline submit report /by 2023-10-01");
        assertEquals(DeadlineCommand.class, command.getClass());
    }

    @Test
    public void testParseEvent() throws AriesException {
        Command command = CommandParser.parse("event team meeting /at 2023-09-15 14:00");
        assertEquals(EventCommand.class, command.getClass());
    }

    @Test
    public void testParseMark() throws AriesException {
        Command command = CommandParser.parse("mark 2");
        assertEquals(MarkCommand.class, command.getClass());
    }

    @Test
    public void testParseUnmark() throws AriesException {
        Command command = CommandParser.parse("unmark 2");
        assertEquals(UnmarkCommand.class, command.getClass());
    }

    @Test
    public void testParseDelete() throws AriesException {
        Command command = CommandParser.parse("delete 3");
        assertEquals(DeleteCommand.class, command.getClass());
    }

    @Test
    public void testParseList() throws AriesException {
        Command command = CommandParser.parse("list");
        assertEquals(ListCommand.class, command.getClass());
    }

    @Test
    public void testParseBye() throws AriesException {
        Command command = CommandParser.parse("bye");
        assertEquals(ByeCommand.class, command.getClass());
    }

    @Test
    public void testParseUnknown() throws AriesException {
        Command command = CommandParser.parse("what can you do?");
        assertEquals(UnknownCommand.class, command.getClass());
    }
}
