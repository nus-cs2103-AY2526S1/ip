package karl.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class EventTest {

    @Test
    public void constructor_validDateStrings_parsesDatesCorrectly() {
        Event event = new Event("project meeting", "2025-11-10", "2025-11-12");
        assertEquals(LocalDate.of(2025, 11, 10), event.getFrom());
        assertEquals(LocalDate.of(2025, 11, 12), event.getTo());
        assertEquals("project meeting", event.getDescription());
    }

    @Test
    public void markAsDone_marksTaskAsDone() {
        Event event = new Event("project meeting", "2025-11-10", "2025-11-12");
        assertFalse(event.isDone());
        event.markAsDone();
        assertTrue(event.isDone());
    }

    @Test
    public void markAsNotDone_marksTaskAsNotDone() {
        Event event = new Event("project meeting", "2025-11-10", "2025-11-12");
        event.markAsDone();
        event.markAsNotDone();
        assertFalse(event.isDone());
    }

    @Test
    public void toString_formatsCorrectly() {
        Event event = new Event("project meeting", "2025-11-10", "2025-11-12");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String expected = String.format("[E][ ] project meeting (from: %s to: %s)",
                LocalDate.of(2025,11,10).format(outputFormat),
                LocalDate.of(2025,11,12).format(outputFormat));
        assertEquals(expected, event.toString());

        event.markAsDone();
        expected = String.format("[E][X] project meeting (from: %s to: %s)",
                LocalDate.of(2025,11,10).format(outputFormat),
                LocalDate.of(2025,11,12).format(outputFormat));
        assertEquals(expected, event.toString());
    }
}

// Used AI to help clean up code and naming conventions etc, mainly small issues brought up through code reviews