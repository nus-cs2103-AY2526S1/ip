package hachiware;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;


public class DeadlineTest {

    @Test
    void markAsDoneAndUnmark() {
        Deadline deadline = new Deadline("Submit report", "2025-09-01");
        assertFalse(deadline.isDone, "Deadline should not be done by default");

        deadline.markAsDone();
        assertTrue(deadline.isDone, "Deadline should be marked done");

        deadline.markAsNotDone();
        assertFalse(deadline.isDone, "Deadline should be marked not done");
    }

    @Test
    void invalidDateThrowsException() {
        assertThrows(DateTimeParseException.class, () -> new Deadline("Test", "invalid-date"),
                "Invalid date expected to throw DateTimeParseException");
    }

    @Test
    void getByReturnsCorrectLocalDate() {
        Deadline deadline = new Deadline("Finish project", "2025-12-01");
        LocalDate expectedDate = LocalDate.parse("2025-12-01");
        assertEquals(expectedDate, deadline.getBy(), "Deadline date should match input date");
    }

    @Test
    void toStringContainsStatusAndDate() {
        Deadline deadline = new Deadline("Write essay", "2025-10-10");
        String str = deadline.toString();
        assertTrue(str.contains("Write essay"), "toString should contain task description");
        assertTrue(str.contains("2025-10-10"), "toString should contain deadline date");
        assertTrue(str.contains("[ ]") || str.contains("[X]"), "toString should show done status");
    }

    @Test
    void markDoneReflectsInToString() {
        Deadline deadline = new Deadline("Prepare slides", "2025-11-15");
        deadline.markAsDone();
        String str = deadline.toString();
        assertTrue(str.contains("[X]"), "toString should reflect task marked as done");
    }

    @Test
    void multipleDeadlinesIndependentStatus() {
        Deadline d1 = new Deadline("Task 1", "2025-01-01");
        Deadline d2 = new Deadline("Task 2", "2025-02-01");
        d1.markAsDone();
        assertTrue(d1.isDone, "First deadline should be done");
        assertFalse(d2.isDone, "Second deadline should still be not done");
    }
}
