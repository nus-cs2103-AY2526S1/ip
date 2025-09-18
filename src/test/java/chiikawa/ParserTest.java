package chiikawa;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import chiikawa.command.AddDeadlineCommand;
import chiikawa.command.AddEventCommand;
import chiikawa.command.AddToDoCommand;
import chiikawa.command.Command;
import chiikawa.command.DeleteCommand;
import chiikawa.command.ExitCommand;
import chiikawa.command.InvalidCommand;
import chiikawa.command.ListCommand;
import chiikawa.command.MarkCommand;
import chiikawa.command.UnmarkCommand;

public class ParserTest {
    @Test
    public void parse_exit_returnExitCommand() {
        Command cmd = Parser.parse("bye");
        assertTrue(cmd instanceof ExitCommand);
    }

    @Test
    public void parse_list_returnsListCommand() {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand);
    }

    @Test
    public void parse_invalid_returnsInvalidCommand() {
        Command cmd = Parser.parse("jiby jaber");
        assertTrue(cmd instanceof InvalidCommand);
    }

    @Test
    public void parse_mark_returnsMarkCommand() {
        Command cmd = Parser.parse("mark 3");
        assertTrue(cmd instanceof MarkCommand);
    }

    @Test
    public void parse_unmark_returnsUnmarkCommand() {
        Command cmd = Parser.parse("unmark 3");
        assertTrue(cmd instanceof UnmarkCommand);
    }

    @Test
    public void parse_delete_returnsDeleteCommand() {
        Command cmd = Parser.parse("delete 3");
        assertTrue(cmd instanceof DeleteCommand);
    }

    @Test
    public void parse_todo_returnsAddToDoCommand() {
        Command cmd = Parser.parse("todo cs2100");
        assertTrue(cmd instanceof AddToDoCommand);
    }

    @Test
    public void parse_todo_returnsAddDeadlineCommand() {
        Command cmd = Parser.parse("deadline cs2100 /tonight");
        assertTrue(cmd instanceof AddDeadlineCommand);
    }

    @Test
    public void parse_todo_returnsAddEventCommand() {
        Command cmd = Parser.parse("event dinner /2020-10-20 /2020-10-21");
        assertTrue(cmd instanceof AddEventCommand);
    }

    @Test
    public void parseTaskInfo_basicSplit_returnsArray() {
        String[] result = Parser.parseTaskInfo("todo work", " ", 2);
        assertArrayEquals(new String[]{"todo", "work"}, result);
    }

    @Test
    public void parseTaskInfo_trimsWhitespace() {
        String[] result = Parser.parseTaskInfo("  todo   work  ", " ", 2);
        assertArrayEquals(new String[]{"todo", "work"}, result);
    }

    @Test
    public void parseTaskInfo_splitWithForwardSlash_returnsArray() {
        String[] result = Parser.parseTaskInfo("work /tomorrow", "/", 2);
        assertArrayEquals(new String[]{"work", "tomorrow"}, result);
    }
}
