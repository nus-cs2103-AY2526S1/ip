package poopiemeow.task;

import poopiemeow.exception.EmptyDescriptionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

class DeadlineTest {

    @Test
    void testDeadlineCreation() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        assertEquals("Test deadline", deadline.description);
        assertEquals(LocalDateTime.of(2023, 12, 25, 14, 30), deadline.getDeadline());
    }

    @Test
    void testDeadlineWithEmptyDescription() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Deadline("", "2023-12-25 1430");
        });
    }

    @Test
    void testDeadlineWithWhitespaceOnly() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Deadline("   ", "2023-12-25 1430");
        });
    }

    @Test
    void testDeadlineWithInvalidDateTime() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Test deadline", "invalid-date");
        });
    }

    @Test
    void testDeadlineWithInvalidTimeFormat() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Test deadline", "2023-12-25 25:30");
        });
    }

    @Test
    void testDeadlineToFileString() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        assertEquals("D|0|Test deadline|2023-12-25 1430", deadline.toFileString());

        deadline.markAsDone();
        assertEquals("D|1|Test deadline|2023-12-25 1430", deadline.toFileString());
    }

    @Test
    void testDeadlineToString() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        String expected = "[D][ ] Test deadline (by: Dec 25 2023, 2:30 PM)";
        String actual = deadline.toString();
        // Use case-insensitive comparison for AM/PM
        assertEquals(expected.toLowerCase(), actual.toLowerCase());

        deadline.markAsDone();
        String expectedDone = "[D][X] Test deadline (by: Dec 25 2023, 2:30 PM)";
        String actualDone = deadline.toString();
        assertEquals(expectedDone.toLowerCase(), actualDone.toLowerCase());
    }

    @Test
    void testDeadlineGetDeadline() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        LocalDateTime expected = LocalDateTime.of(2023, 12, 25, 14, 30);
        assertEquals(expected, deadline.getDeadline());
    }

    @Test
    void testDeadlineMarkAsDone() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        assertFalse(deadline.isDone);
        assertEquals(" ", deadline.getStatusIcon());

        deadline.markAsDone();
        assertTrue(deadline.isDone);
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    void testDeadlineMarkAsUndone() throws EmptyDescriptionException {
        Deadline deadline = new Deadline("Test deadline", "2023-12-25 1430");
        deadline.markAsDone();
        assertTrue(deadline.isDone);

        deadline.markAsUndone();
        assertFalse(deadline.isDone);
        assertEquals(" ", deadline.getStatusIcon());
    }

    @Test
    void testDeadlineWithDifferentTimeFormats() throws EmptyDescriptionException {
        // Test midnight
        Deadline midnight = new Deadline("Midnight task", "2023-12-25 0000");
        assertEquals(LocalDateTime.of(2023, 12, 25, 0, 0), midnight.getDeadline());

        // Test noon
        Deadline noon = new Deadline("Noon task", "2023-12-25 1200");
        assertEquals(LocalDateTime.of(2023, 12, 25, 12, 0), noon.getDeadline());

        // Test end of day
        Deadline endOfDay = new Deadline("End of day task", "2023-12-25 2359");
        assertEquals(LocalDateTime.of(2023, 12, 25, 23, 59), endOfDay.getDeadline());
    }
}
