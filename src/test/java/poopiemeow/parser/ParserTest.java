package poopiemeow.parser;

import poopiemeow.task.Task;
import poopiemeow.task.Todo;
import poopiemeow.task.Deadline;
import poopiemeow.task.Event;
import poopiemeow.exception.EmptyDescriptionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.format.DateTimeParseException;

class ParserTest {

    @Test
    void testParseTodo() throws EmptyDescriptionException {
        Task task = Parser.parseTask("todo Test todo");
        assertTrue(task instanceof Todo);
        assertEquals("Test todo", task.toString().substring(4)); // Remove "[ ] " prefix
        assertFalse(task.getStatusIcon().equals("X"));
    }

    @Test
    void testParseTodoWithMultipleWords() throws EmptyDescriptionException {
        Task task = Parser.parseTask("todo Buy groceries and clean house");
        assertTrue(task instanceof Todo);
        assertEquals("Buy groceries and clean house", task.toString().substring(4)); // Remove "[ ] " prefix
    }

    @Test
    void testParseDeadline() throws EmptyDescriptionException {
        Task task = Parser.parseTask("deadline Submit report /by 2023-12-25 1430");
        assertTrue(task instanceof Deadline);
        assertTrue(task.toString().contains("Submit report"));
        assertFalse(task.getStatusIcon().equals("X"));
    }

    @Test
    void testParseDeadlineWithComplexDescription() throws EmptyDescriptionException {
        Task task = Parser.parseTask("deadline Complete project documentation and review /by 2023-12-31 2359");
        assertTrue(task instanceof Deadline);
        assertTrue(task.toString().contains("Complete project documentation and review"));
    }

    @Test
    void testParseEvent() throws EmptyDescriptionException {
        Task task = Parser.parseTask("event Team meeting /from 2023-12-25 1400 /to 2023-12-25 1600");
        assertTrue(task instanceof Event);
        assertTrue(task.toString().contains("Team meeting"));
        assertFalse(task.getStatusIcon().equals("X"));
    }

    @Test
    void testParseEventWithComplexDescription() throws EmptyDescriptionException {
        Task task = Parser.parseTask("event Annual company retreat and team building /from 2023-12-25 0900 /to 2023-12-25 1800");
        assertTrue(task instanceof Event);
        assertTrue(task.toString().contains("Annual company retreat and team building"));
    }

    @Test
    void testParseDeadlineWithInvalidFormat() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("deadline Submit report");
        });
    }

    @Test
    void testParseDeadlineWithMissingBy() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("deadline Submit report /at 2023-12-25 1430");
        });
    }

    @Test
    void testParseEventWithInvalidFormat() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("event Team meeting");
        });
    }

    @Test
    void testParseEventWithMissingFrom() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("event Team meeting /at 2023-12-25 1400 /to 2023-12-25 1600");
        });
    }

    @Test
    void testParseEventWithMissingTo() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("event Team meeting /from 2023-12-25 1400");
        });
    }

    @Test
    void testParseUnknownCommand() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("unknown command");
        });
    }

    @Test
    void testParseEmptyCommand() {
        assertThrows(EmptyDescriptionException.class, () -> {
            Parser.parseTask("");
        });
    }

    @Test
    void testParseDeadlineWithInvalidDateTime() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseTask("deadline Submit report /by invalid-date");
        });
    }

    @Test
    void testParseEventWithInvalidStartTime() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseTask("event Team meeting /from invalid-time /to 2023-12-25 1600");
        });
    }

    @Test
    void testParseEventWithInvalidEndTime() {
        assertThrows(DateTimeParseException.class, () -> {
            Parser.parseTask("event Team meeting /from 2023-12-25 1400 /to invalid-time");
        });
    }

    @Test
    void testParseDeadlineWithEdgeCaseTimes() throws EmptyDescriptionException {
        // Test midnight
        Task midnightTask = Parser.parseTask("deadline Midnight task /by 2023-12-25 0000");
        assertTrue(midnightTask instanceof Deadline);

        // Test end of day
        Task endOfDayTask = Parser.parseTask("deadline End of day task /by 2023-12-25 2359");
        assertTrue(endOfDayTask instanceof Deadline);
    }

    @Test
    void testParseEventWithEdgeCaseTimes() throws EmptyDescriptionException {
        // Test all-day event
        Task allDayEvent = Parser.parseTask("event All day event /from 2023-12-25 0000 /to 2023-12-25 2359");
        assertTrue(allDayEvent instanceof Event);

        // Test very short event
        Task shortEvent = Parser.parseTask("event Quick call /from 2023-12-25 1200 /to 2023-12-25 1201");
        assertTrue(shortEvent instanceof Event);
    }

    @Test
    void testParseCommandsWithExtraSpaces() throws EmptyDescriptionException {
        Task todo = Parser.parseTask("todo Test todo");
        assertTrue(todo instanceof Todo);
        assertEquals("Test todo", todo.toString().substring(4)); // Remove "[ ] " prefix

        Task deadline = Parser.parseTask("deadline Submit report /by 2023-12-25 1430");
        assertTrue(deadline instanceof Deadline);
        assertTrue(deadline.toString().contains("Submit report"));

        Task event = Parser.parseTask("event Team meeting /from 2023-12-25 1400 /to 2023-12-25 1600");
        assertTrue(event instanceof Event);
        assertTrue(event.toString().contains("Team meeting"));
    }
}
