package dwight.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

/**
 * Unit tests for the {@link Event} class. Verifies behavior for string representation and
 * serialization with start and end dates.
 */
public class EventTest {

    /**
     * Tests the string representation of an {@code Event} task, ensuring that type, status,
     * description, and date range are formatted correctly.
     */
    @Test
    public void testToString() {
        LocalDate fromDate = LocalDate.of(2023, 10, 26);
        LocalDate toDate = LocalDate.of(2023, 10, 30);
        Event event = new Event("project meeting", fromDate, toDate);

        // Unmarked event
        assertEquals(
                "[E][ ] project meeting (from: 26 Oct to: 30 Oct)",
                event.toString(),
                "Unmarked event should display with [ ] and the correct date range");

        // Marked event
        event.mark();
        assertEquals(
                "[E][X] project meeting (from: 26 Oct to: 30 Oct)",
                event.toString(),
                "Marked event should display with [X] and the correct date range");
    }

    /**
     * Tests the serialization of an {@code Event} task, ensuring that type, status flag,
     * description, and dates are saved in the correct format.
     */
    @Test
    public void testSerialize() {
        LocalDate fromDate = LocalDate.of(2023, 10, 26);
        LocalDate toDate = LocalDate.of(2023, 10, 30);
        Event event = new Event("project meeting", fromDate, toDate);

        // Unmarked event
        assertEquals(
                "E | 0 | project meeting | 26 Oct 2023 | 30 Oct 2023",
                event.serialize(),
                "Unmarked event should serialize with 0 and correct dates");

        // Marked event
        event.mark();
        assertEquals(
                "E | 1 | project meeting | 26 Oct 2023 | 30 Oct 2023",
                event.serialize(),
                "Marked event should serialize with 1 and correct dates");
    }
}
