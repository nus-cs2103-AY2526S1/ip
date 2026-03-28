package parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import jerry.command.ListCommand;
import jerry.exceptions.JerryException;
import jerry.parser.Parser;

public class ParserTest {
    @Test
    public void wrongInput_exceptionThrown() {
        try {
            Parser.parse("test");
        } catch (JerryException error) {
            assertEquals("I don't understand what you mean by: test\n"
                            + "Use these commands at the start of your sentence instead: "
                            + "bye/list/todo/deadline/event/mark/unmark/delete",
                    error.getMessage());
        }
    }

    @Test
    void parseListCommand_withSpaces() throws JerryException {
        assertEquals(ListCommand.class, Parser.parse("  list  ").getClass());
    }

    @Test
    public void parseToDoCommand() throws JerryException {
        assertEquals("TodoCommand", Parser.parse("todo test").getClass().getSimpleName());
    }

    @Test
    public void parseEventCommand() throws JerryException {
        assertEquals("EventCommand",
                Parser.parse("event test /from 2022-10-10 10:30 to 2022-10-10 12:30").getClass().getSimpleName());
    }
}
