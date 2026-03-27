package sunday;

import org.junit.jupiter.api.Test;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    @Test
    public void parseToDoLine() {
        Task t = Parser.parseTaskLine("T | 1 | read book");
        assertInstanceOf(Todo.class, t);
        assertTrue(t.isDone());
        assertEquals("T | 1 | read book", t.convertor());
    }

    @Test
    public void parseDeadline() {
        Task t = Parser.parseTaskLine("D | 0 | return book | 2025-08-25");
        assertInstanceOf(Deadline.class, t);
        assertFalse(t.isDone());
        assertEquals("D | 0 | return book | 2025-08-25", t.convertor());
        assertTrue(t.toString().toLowerCase().contains("by"));
    }

    @Test
    public void parseEventline() {
        Task t = Parser.parseTaskLine("E | 0 | meeting | 2025-08-25 1800 | 2025-08-26 1700");
        assertInstanceOf(Event.class, t);
        assertFalse(t.isDone());
        assertEquals("E | 0 | meeting | 2025-08-25 1800 | 2025-08-26 1700", t.convertor());
        assertTrue(t.toString().toLowerCase().contains("from"));
        assertTrue(t.toString().toLowerCase().contains("to"));
    }
}
