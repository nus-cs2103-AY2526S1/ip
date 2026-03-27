package marvin;

import static marvin.Parser.parse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import marvin.command.AddTaskCommand;
import marvin.command.Command;
import marvin.command.DeleteTaskCommand;
import marvin.command.DoAfterCommand;
import marvin.command.ExitCommand;
import marvin.command.FindCommand;
import marvin.command.ListTaskCommand;
import marvin.command.MarkTaskCommand;

public class ParserTest {
    @Test
    public void parse_returnsMarkCommand_ifValidMarkInput() {
        Command c = parse("mark 1");
        assertEquals(MarkTaskCommand.class, c.getClass());
    }

    @Test
    public void parse_throwsException_ifIncompleteMarkInput() {
        try {
            Command c = parse("mark");
            fail();
        } catch (Exception e) {
            assertEquals(MarvinException.class, e.getClass());
        }
    }

    @Test
    public void parse_returnsAddTaskCommand_ifValidTodoInput() {
        Command c = parse("todo this");
        assertEquals(AddTaskCommand.class, c.getClass());
    }

    @Test
    public void parse_returnsAddTaskCommand_ifValidDeadlineInput() {
        Command c = parse("deadline test /by 28/08/2025 1800");
        assertEquals(AddTaskCommand.class, c.getClass());
    }

    @Test
    public void parse_throwsException_ifInvalidDeadlineInput() {
        try {
            parse("deadline test /by 28/8/2025 1800");
            fail();
        } catch (Exception e) {
            assertEquals(MarvinException.class, e.getClass());
        }
    }

    @Test
    public void parse_returnsAddTaskCommand_ifValidEventInput() {
        Command c = parse("event test /from 28/08/2025 1800 /to 29/08/2025 1800");
        assertEquals(AddTaskCommand.class, c.getClass());
    }

    @Test
    public void parse_throwsException_ifInvalidEventInput() {
        try {
            parse("event test /frlm 28/08/2025 1800 /tn 29/08/2025 1800");
            fail();
        } catch (Exception e) {
            assertEquals(MarvinException.class, e.getClass());
        }
    }

    @Test
    public void parse_returnsDeleteTaskCommand_ifValidDeleteInput() {
        Command c = parse("delete 12");
        assertEquals(DeleteTaskCommand.class, c.getClass());
    }

    @Test
    public void parse_throwsException_ifInvalidDeleteInput() {
        try {
            parse("delete");
            fail();
        } catch (Exception e) {
            assertEquals(MarvinException.class, e.getClass());
        }
    }

    @Test
    public void parse_returnsFindCommand_ifValidFindQuery() {
        Command c = parse("find dingus");
        assertEquals(FindCommand.class, c.getClass());
    }

    @Test
    public void parse_throwsException_ifInvalidFindInput() {
        try {
            parse("find");
            fail();
        } catch (Exception e) {
            assertEquals(MarvinException.class, e.getClass());
        }
    }

    @Test
    public void parse_returnsDoAfterCommand_ifValidFindQuery() {
        Command c = parse("do 12 /after 32");
        assertEquals(DoAfterCommand.class, c.getClass());
    }

    @Test
    public void parse_throwsException_ifInvalidDoAfterInput() {
        try {
            parse("do");
            fail();
        } catch (Exception e) {
            assertEquals(MarvinException.class, e.getClass());
        }
    }

    @Test
    public void parse_returnsExitCommand_ifBye() {
        Command c = parse("bye");
        assertEquals(ExitCommand.class, c.getClass());
    }

    @Test
    public void parse_returnsExitCommand_ifList() {
        Command c = parse("list");
        assertEquals(ListTaskCommand.class, c.getClass());
    }

    @Test
    public void parse_returnsUnknownCommand_ifNonsenseInput() {
        Command c = parse("asdasdasd");
        assertEquals(marvin.command.UnknownCommand.class, c.getClass());
    }

}
