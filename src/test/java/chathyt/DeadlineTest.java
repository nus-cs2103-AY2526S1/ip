package chathyt;

import chathyt.task.Deadline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDateTime;

/**
 * Unit tests for the {@link Deadline} class.
 * Ensures correct parsing of date-time strings and proper string formatting.
 */
public class DeadlineTest {

    /**
     * Tests that a {@link Deadline} created with a valid
     * "yyyy-MM-dd HH:mm" string correctly parses into a {@link LocalDateTime}.
     */
    @Test
    public void deadline_parseDateTimeCorrectly() {
        Deadline deadline1 = new Deadline("return book", "2025-08-30 18:00");
        Assertions.assertEquals(
                LocalDateTime.of(2025, 8, 30, 18, 0),
                deadline1.getBy()
        );
    }

    /**
     * Tests that the {@link Deadline#toString()} method
     * returns the properly formatted string with description,
     * status, and deadline date-time.
     */
    @Test
    public void deadline_toStringFormatsCorrectly() {
        Deadline deadline2 = new Deadline("get 2103 ip done", "2025-09-05 23:59");
        Assertions.assertEquals(
                "[D][ ] get 2103 ip done (by: Sept 5 2025 23:59)",
                deadline2.toString()
        );
    }
}