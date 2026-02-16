package shadow.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;



class EventTest {

    @Test
    void constructor_validInputs_initializesCorrectly() {
        LocalDateTime start = LocalDateTime.of(2025, 10, 1, 10, 0);
        LocalDateTime end = LocalDateTime.of(2025, 10, 1, 12, 0);
        Event event = new Event("Team meeting", start, end);

        String output = event.toString();
        assertTrue(output.contains("[E]"));
        assertTrue(output.contains("[ ] Team meeting"));
        assertTrue(output.contains("from:"));
        assertTrue(output.contains("to:"));
    }

    @Test
    void toString_futureEvent_displaysDaysLeft() {
        LocalDateTime start = LocalDateTime.now().plusDays(7);
        LocalDateTime end = start.plusHours(2);
        Event event = new Event("Conference", start, end);

        String output = event.toString();
        assertTrue(output.contains("days left"));
    }

    @Test
    void toString_pastEvent_displaysEventPassed() {
        LocalDateTime start = LocalDateTime.now().minusDays(3);
        LocalDateTime end = start.plusHours(1);
        Event event = new Event("Past meeting", start, end);

        String output = event.toString();
        assertTrue(output.contains("event passed"));
    }

    @Test
    void mark_event_markedCorrectlyInToString() {
        LocalDateTime start = LocalDateTime.now().plusDays(2);
        LocalDateTime end = start.plusHours(1);
        Event event = new Event("Review session", start, end);
        event.mark();

        assertTrue(event.toString().contains("[X] Review session"));
    }

    @Test
    void unmark_event_unmarkedCorrectlyInToString() {
        LocalDateTime start = LocalDateTime.now().plusDays(2);
        LocalDateTime end = start.plusHours(1);
        Event event = new Event("Unmark test", start, end);
        event.mark();
        event.unmark();

        assertTrue(event.toString().contains("[ ] Unmark test"));
    }

    @Test
    void of_validIsoFormatInput_returnsEventInstance() {
        String input = "Project deadline /from 2025-12-01T09:00 /to 2025-12-01T17:00";
        Event event = Event.of(input);

        assertNotNull(event);
        assertTrue(event.toString().contains("[E]"));
        assertTrue(event.toString().contains("Project deadline"));
    }

    @Test
    void of_validCustomFormatInput_returnsEventInstance() {
        String input = "Team sync /from 01/12/2025 09:00 /to 01/12/2025 10:00";
        Event event = Event.of(input);

        assertNotNull(event);
        assertTrue(event.toString().contains("Team sync"));
    }

    @Test
    void of_nullInput_throwsException() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Event.of(null));
        assertEquals("Usage: event <taskName> /from <from> /to <to>", ex.getMessage());
    }

    @Test
    void of_missingFrom_throwsException() {
        String input = "Project deadline";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Event.of(input));
        assertEquals("Usage: event <taskName> /from <from> /to <to>", ex.getMessage());
    }

    @Test
    void of_missingTo_throwsException() {
        String input = "Launch /from 2025-09-10T09:00";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Event.of(input));
        assertEquals("Usage: event <taskName> /from <from> /to <to>", ex.getMessage());
    }

    @Test
    void of_inputWithExtraSpaces_parsesCorrectly() {
        String input = "  Client call   /from 2025-11-05T10:00   /to 2025-11-05T11:00 ";
        Event event = Event.of(input);

        assertTrue(event.toString().contains("Client call"));
        assertTrue(event.toString().contains("from:"));
        assertTrue(event.toString().contains("to:"));
    }

    @Test
    void of_unrecognizedDateFormat_throwsException() {
        String input = "Bad input /from someday /to next week";
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> Event.of(input));
        assertTrue(ex.getMessage().contains("Unrecognized date/time format"));
    }

    @Test
    void of_startAfterEnd_parsesButMayBeIllogical() {
        String input = "Backwards time /from 2025-12-01T17:00 /to 2025-12-01T09:00";
        Event event = Event.of(input);

        assertNotNull(event);
        assertTrue(event.toString().contains("Backwards time"));
    }
}
