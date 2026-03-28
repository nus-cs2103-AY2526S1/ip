package parser;

import lenny.logic.parser.Parser;

import lenny.logic.exception.LennyExceptions;
import lenny.logic.task.Todo;
import lenny.logic.task.Event;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;


class ParserTest {
    @Test
    void parseTodo_returnsTodo() throws LennyExceptions {
        Todo todo = Parser.parseTodo("todo finish homework");
        assertEquals("finish homework", todo.getTaskName());
    }

    @Test
    void parseDeadline_missingBy_throwsException() {
        try {
            Parser.parseDeadline("deadline return book");
        } catch (LennyExceptions e) {
            assertEquals("OOPS!!! The description of a deadline must include /by.", e.getMessage());

        }
    }

    @Test
    void parseEvent_validInput_returnsEvent() throws LennyExceptions {
        Event e = Parser.parseEvent("event project /from today /to tomorrow");
        assertEquals("today - tomorrow", e.getDuration());
    }
}
