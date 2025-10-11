package abang.parser;

import abang.command.*;
import abang.exception.AbangException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_clearCommand_returnsClearCommand() throws AbangException {
        Command cmd = Parser.parse("clear");
        assertTrue(cmd instanceof ClearCommand);
    }

    @Test
    public void parse_byeCommand_returnsExitCommand() throws AbangException {
        Command cmd = Parser.parse("bye");
        assertTrue(cmd instanceof ExitCommand);
    }

    @Test
    public void parse_listCommand_returnsListCommand() throws AbangException {
        Command cmd = Parser.parse("list");
        assertTrue(cmd instanceof ListCommand);
    }

    @Test
    public void parse_todoCommand_returnsAddCommand() throws AbangException {
        Command cmd = Parser.parse("todo read book");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    public void parse_deadlineCommand_returnsAddCommand() throws AbangException {
        Command cmd = Parser.parse("deadline submit report /by tonight");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    public void parse_eventCommand_returnsAddCommand() throws AbangException {
        Command cmd = Parser.parse("event project meeting /from Monday /to Tuesday");
        assertTrue(cmd instanceof AddCommand);
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(AbangException.class, () -> Parser.parse("nonsense"));
    }

    @Test
    public void parse_emptyInput_throwsException() {
        assertThrows(AbangException.class, () -> Parser.parse(""));
    }
}
