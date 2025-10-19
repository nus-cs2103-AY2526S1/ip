package reboot.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import reboot.command.AddCommand;
import reboot.command.ClearCommand;
import reboot.command.Command;
import reboot.command.ErrorCommand;
import reboot.command.FindCommand;
import reboot.command.ListCommand;

public class ParserTest {

    @Test
    public void parse_validListCommand_returnsListCommand() throws Exception {
        Command c = Parser.parse("list");
        assertTrue(c instanceof ListCommand);
    }

    @Test
    public void parse_invalidCommand_throwsException() {
        assertTrue(Parser.parse("goodbye") instanceof ErrorCommand);
    }

    @Test
    public void parse_validTodo_returnsAddCommand() throws Exception {
        Command c = Parser.parse("todo Read book");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_todoCommandWithoutDescription_returnsErrorCommand() {
        assertTrue(Parser.parse("todo") instanceof ErrorCommand);
    }

    @Test
    public void parse_validDeadline_returnsAddCommand() throws Exception {
        Command c = Parser.parse("deadline Read book /by 20 09 2026");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_deadlineCommandWithoutDueDate_returnsErrorCommand() {
        assertTrue(Parser.parse("deadline Read book") instanceof ErrorCommand);
    }

    @Test
    public void parse_eventCommand_returnsAddCommand() throws Exception {
        Command c = Parser.parse("event Read book /from 02 09 2025 /to 03 09 2025");
        assertTrue(c instanceof AddCommand);
    }

    @Test
    public void parse_eventCommandWithoutEndDate_returnsErrorCommand() {
        assertTrue(Parser.parse("event Read book /from 02 09 2025") instanceof ErrorCommand);
    }

    @Test
    public void parse_clearCommand_returnsClearCommand() throws Exception {
        Command c = Parser.parse("clear");
        assertTrue(c instanceof ClearCommand);
    }

    @Test
    public void parse_findCommand_returnsFindCommand() throws Exception {
        Command c = Parser.parse("find read");
        assertTrue(c instanceof FindCommand);
    }
}
