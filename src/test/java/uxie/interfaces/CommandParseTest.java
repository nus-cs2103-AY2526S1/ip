package uxie.interfaces;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import uxie.commands.MarkCommand;
import uxie.commands.TodoCommand;
import uxie.exceptions.UxieException;

public class CommandParseTest {

    @Test
    public void parse_markValidInput_commandReturn() {
        try {
            assertEquals(
                    new MarkCommand(3),
                    CommandParse.parse("mark 4"));
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void parse_markNonIntInput_exceptionThrown() {
        assertThrows(UxieException.class, () ->
                CommandParse.parse("mark 4.5"));
    }

    @Test
    public void parse_todoValidInput_commandReturn() {
        try {
            assertEquals(
                    new TodoCommand("eat breakfast"),
                    CommandParse.parse("todo eat breakfast")
            );
        } catch (Exception e) {
            Assertions.fail();
        }
    }

    @Test
    public void parse_todoBlankDesc_exceptionThrown() {
        assertThrows(UxieException.class, () ->
                CommandParse.parse("todo "));
    }

}
