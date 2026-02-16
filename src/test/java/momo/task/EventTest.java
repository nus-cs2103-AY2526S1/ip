package momo.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@link Event} class.
 *
 * <p>These tests verify that the string representation and save format of
 * events are correctly generated for both completed and uncompleted tasks.</p>
 */
public class EventTest {

    /**
     * Tests the {@link Event#toString()} method when the event is marked as done.
     * Verifies that the output includes the "[X]" done marker and correctly formatted start and end date/time.
     */
    @Test
    public void toString_done() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = true;
        Event event = new Event(description, from, to, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        String expected = "[E][X] " + description + " (from: "
                + from.format(formatter) + " to: "
                + to.format(formatter) + ")";

        assertEquals(expected, event.toString());
    }

    /**
     * Tests the {@link Event#toString()} method when the event is not done.
     * Verifies that the output includes the "[ ]" marker and correctly formatted start and end date/time.
     */
    @Test
    public void toString_notDone() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = false;
        Event event = new Event(description, from, to, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy, h:mma");
        String expected = "[E][ ] " + description + " (from: "
                + from.format(formatter) + " to: "
                + to.format(formatter) + ")";

        assertEquals(expected, event.toString());
    }

    /**
     * Tests the {@link Event#convertToSaveFormat()} method for a completed event.
     * Ensures the returned string correctly encodes the task type, done status, description, and date/time range.
     */
    @Test
    public void convertToSaveFormat_done() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = true;
        Event event = new Event(description, from, to, isDone);
        assertEquals("E | 1 | project meeting | 2025-12-02 1200 | 2025-12-02 1400",
                event.convertToSaveFormat());
    }

    /**
     * Tests the {@link Event#convertToSaveFormat()} method for an uncompleted event.
     * Ensures the returned string correctly encodes the task type, not-done status, description, and date/time range.
     */
    @Test
    public void convertToSaveFormat_notDone() {
        String description = "project meeting";
        LocalDateTime from = LocalDateTime.of(2025, 12, 2, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 12, 2, 14, 0);
        boolean isDone = false;
        Event event = new Event(description, from, to, isDone);
        assertEquals("E | 0 | project meeting | 2025-12-02 1200 | 2025-12-02 1400",
                event.convertToSaveFormat());
    }
}
