package parser;

import commands.*;
import exception.RainyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    public void parse_validTodoCommand_returnsAddTodoCommand() throws RainyException {
        assertInstanceOf(AddTodoCommand.class, Parser.parse("todo read book"));
    }

    @Test
    public void parse_todoWithoutDescription_throwsException() {
        assertThrows(RainyException.class, () -> Parser.parse("todo"));
    }

    @Test
    public void parse_validDeadlineCommand_returnsAddDeadlineCommand() throws RainyException {
        assertInstanceOf(AddDeadlineCommand.class, Parser.parse("deadline submit report /by 2025-08-30T23:59"));
    }

    @Test
    public void parse_invalidDeadlineCommand_throwsException() {
        assertThrows(RainyException.class, () -> Parser.parse("deadline submit report"));
    }

    @Test
    public void parse_unknownCommand_throwsException() {
        assertThrows(RainyException.class, () -> Parser.parse("blahblah"));
    }
}
