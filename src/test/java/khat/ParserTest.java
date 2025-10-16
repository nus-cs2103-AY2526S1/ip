package khat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import khat.exception.DeadlineTaskException;
import khat.exception.EmptyTaskException;
import khat.exception.EventTaskException;
import khat.exception.KhatException;

public class ParserTest {

    @Test
    void getType_returnsCorrectType() {
        assertEquals("list", Parser.getType("list"));
        assertEquals("mark", Parser.getType("mark 2"));
    }

    @Test
    void getCommandType_returnsEnum() {
        assertEquals(Parser.CommandType.LIST, Parser.getCommandType("list"));
        assertEquals(Parser.CommandType.TODO, Parser.getCommandType("todo read book"));
        assertEquals(Parser.CommandType.UNKNOWN, Parser.getCommandType("foobar"));
    }

    @Test
    void getDescription_extractsDescription() {
        assertEquals("read book", Parser.getDescription("todo read book"));
        assertThrows(EmptyTaskException.class, () -> Parser.getDescription("todo "));
    }

    @Test
    void getIndex_extractsIndex() {
        assertEquals(1, Parser.getIndex("mark 2"));
    }

    @Test
    void getDeadline_extractsDeadline() {
        assertEquals("tomorrow", Parser.getDeadline("deadline submit report /by tomorrow"));
        assertThrows(DeadlineTaskException.class, () -> Parser.getDeadline("deadline submit report"));
    }

    @Test
    void getFromAndTo_extractsEventTimes() {
        assertEquals("Mon 2pm", Parser.getFrom("event meeting /from Mon 2pm /to Mon 4pm"));
        assertEquals("Mon 4pm", Parser.getTo("event meeting /from Mon 2pm /to Mon 4pm"));
        assertThrows(EventTaskException.class, () -> Parser.getFrom("event meeting /from Mon 2pm"));
    }

    @Test
    void parseDate_validAndInvalid() {
        assertEquals(LocalDate.of(2024, 6, 1), Parser.parseDate("01-06-2024"));
        assertThrows(DateTimeParseException.class, () -> Parser.parseDate("2024-06-01"));
    }

    @Test
    void parseTask_parsesTodoDeadlineEvent() {
        assertTrue(Parser.parseTask("T | 1 | read book") instanceof khat.task.Todo);
        assertTrue(Parser.parseTask("D | 0 | submit report | 01-06-2024") instanceof khat.task.Deadline);
        assertTrue(Parser.parseTask("E | 1 | meeting | 01-06-2024 1500 | 01-06-2024 1600") instanceof khat.task.Event);
        assertThrows(IllegalArgumentException.class, () -> Parser.parseTask("X | 1 | unknown"));
    }

    @Test
    void parse_invalidCommandThrows() {
        assertThrows(KhatException.class, () -> Parser.parse("foobar something"));
    }
}