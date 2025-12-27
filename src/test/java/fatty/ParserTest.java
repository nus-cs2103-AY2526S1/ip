package fatty;

import fatty.command.ByeCommand;
import fatty.command.Command;
import fatty.command.DeadlineCommand;
import fatty.command.DeleteCommand;
import fatty.command.EventCommand;
import fatty.command.ListCommand;
import fatty.command.MarkCommand;
import fatty.command.ToDoCommand;

import fatty.command.UnmarkCommand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParserTest {

    @Test
    public void testParse_list() throws Exception {
        Command command = Parser.parse("list");
        assertInstanceOf(ListCommand.class, command);
    }

    @Test
    public void testParse_todo() throws Exception {
        Command command = Parser.parse("todo read book");
        assertInstanceOf(ToDoCommand.class, command);
    }

    @Test
    public void testParse_todoEmpty_throwsException() {
        assertThrows(FattyException.class, () -> Parser.parse("todo   "));
    }

    @Test
    public void testParse_deadline() throws Exception {
        Command command = Parser.parse("deadline project /by 21/12/2025 1800");
        assertInstanceOf(DeadlineCommand.class, command);
    }

    @Test
    public void testParse_deadlineInvalidDate_throwsException() {
        assertThrows(FattyException.class, () -> Parser.parse("deadline project /by notadate"));
    }

    @Test
    public void testParse_event() throws Exception {
        Command command = Parser.parse("event party /from 01/01/2026 1200 /to 01/01/2026 1400");
        assertInstanceOf(EventCommand.class, command);
    }

    @Test
    public void testParse_eventMissingFrom_throwsException() {
        assertThrows(FattyException.class, () -> Parser.parse("event party /to 01/01/2026 1400"));
    }

    @Test
    public void testParse_mark() throws Exception {
        Command command = Parser.parse("mark 2");
        assertInstanceOf(MarkCommand.class, command);
    }

    @Test
    public void testParse_markNoNumber_throwsException() {
        assertThrows(FattyException.class, () -> Parser.parse("mark"));
    }

    @Test
    public void testParse_unmark() throws Exception {
        Command command = Parser.parse("unmark 3");
        assertInstanceOf(UnmarkCommand.class, command);
    }

    @Test
    public void testParse_delete() throws Exception {
        Command command = Parser.parse("delete 1");
        assertInstanceOf(DeleteCommand.class, command);
    }

    @Test
    public void testParse_bye() throws Exception {
        Command command = Parser.parse("bye");
        assertInstanceOf(ByeCommand.class, command);
    }

    @Test
    public void testParse_unknownCommand_throwsException() {
        assertThrows(FattyException.class, () -> Parser.parse("randomstuff"));
    }
}
