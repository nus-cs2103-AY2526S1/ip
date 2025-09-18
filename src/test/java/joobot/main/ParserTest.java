package joobot.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import joobot.command.AddCommand;
import joobot.command.Command;



public class ParserTest {

    @Test
    public void parse_validTodoCommand_success() throws JooException {
        String input = "todo read book";
        Command c = Parser.parse(input);
        assertInstanceOf(AddCommand.class, c);
    }

    @Test
    public void parse_missingDescription_throwsJooException() {
        String input = "todo";
        JooException e = assertThrows(JooException.class, () -> Parser.parse(input));
        assertEquals(JooException.ErrorType.MISSING_DESC, e.getType());
    }

    @Test
    public void parse_invalidIndex_throwsJooException() {
        String input = "delete abc";
        JooException e = assertThrows(JooException.class, () -> Parser.parse(input));
        assertEquals(JooException.ErrorType.NO_INDEX, e.getType());
    }

    @Test
    public void parse_missingDeadlineDate_throwsJooException() {
        String input = "deadline test";
        JooException e = assertThrows(JooException.class, () -> Parser.parse(input));
        assertEquals(JooException.ErrorType.MISSING_BY_DATE, e.getType());
    }

    @Test
    public void parse_unknownCommand_throwsJooException() {
        String input = "test";
        JooException e = assertThrows(JooException.class, () -> Parser.parse(input));
        assertEquals(JooException.ErrorType.DEFAULT, e.getType());
    }
}
