package poopiemeow.task;

import poopiemeow.exception.EmptyDescriptionException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

class EventTest {

    @Test
    void testEventCreation() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        assertEquals("Test event", event.description);
        assertEquals(LocalDateTime.of(2023, 12, 25, 14, 0), event.getStartTime());
        assertEquals(LocalDateTime.of(2023, 12, 25, 16, 0), event.getEndTime());
    }

    @Test
    void testEventWithEmptyDescription() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Event("", "2023-12-25 1400", "2023-12-25 1600");
        });
    }

    @Test
    void testEventWithWhitespaceOnly() {
        assertThrows(EmptyDescriptionException.class, () -> {
            new Event("   ", "2023-12-25 1400", "2023-12-25 1600");
        });
    }

    @Test
    void testEventWithInvalidStartTime() {
        assertThrows(DateTimeParseException.class, () -> {
            new Event("Test event", "invalid-time", "2023-12-25 1600");
        });
    }

    @Test
    void testEventWithInvalidEndTime() {
        assertThrows(DateTimeParseException.class, () -> {
            new Event("Test event", "2023-12-25 1400", "invalid-time");
        });
    }

    @Test
    void testEventToFileString() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        assertEquals("E|0|Test event|2023-12-25 1400|2023-12-25 1600", event.toFileString());

        event.markAsDone();
        assertEquals("E|1|Test event|2023-12-25 1400|2023-12-25 1600", event.toFileString());
    }

    @Test
    void testEventToString() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        String expected = "[E][ ] Test event (from: Dec 25 2023, 2:00 PM to: Dec 25 2023, 4:00 PM)";
        String actual = event.toString();
        // Use case-insensitive comparison for AM/PM
        assertEquals(expected.toLowerCase(), actual.toLowerCase());

        event.markAsDone();
        String expectedDone = "[E][X] Test event (from: Dec 25 2023, 2:00 PM to: Dec 25 2023, 4:00 PM)";
        String actualDone = event.toString();
        assertEquals(expectedDone.toLowerCase(), actualDone.toLowerCase());
    }

    @Test
    void testEventGetStartTime() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        LocalDateTime expected = LocalDateTime.of(2023, 12, 25, 14, 0);
        assertEquals(expected, event.getStartTime());
    }

    @Test
    void testEventGetEndTime() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        LocalDateTime expected = LocalDateTime.of(2023, 12, 25, 16, 0);
        assertEquals(expected, event.getEndTime());
    }

    @Test
    void testEventMarkAsDone() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        assertFalse(event.isDone);
        assertEquals(" ", event.getStatusIcon());

        event.markAsDone();
        assertTrue(event.isDone);
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    void testEventMarkAsUndone() throws EmptyDescriptionException {
        Event event = new Event("Test event", "2023-12-25 1400", "2023-12-25 1600");
        event.markAsDone();
        assertTrue(event.isDone);

        event.markAsUndone();
        assertFalse(event.isDone);
        assertEquals(" ", event.getStatusIcon());
    }

    @Test
    void testEventWithDifferentTimeFormats() throws EmptyDescriptionException {
        // Test all-day event
        Event allDay = new Event("All day event", "2023-12-25 0000", "2023-12-25 2359");
        assertEquals(LocalDateTime.of(2023, 12, 25, 0, 0), allDay.getStartTime());
        assertEquals(LocalDateTime.of(2023, 12, 25, 23, 59), allDay.getEndTime());

        // Test short event
        Event shortEvent = new Event("Short event", "2023-12-25 1200", "2023-12-25 1201");
        assertEquals(LocalDateTime.of(2023, 12, 25, 12, 0), shortEvent.getStartTime());
        assertEquals(LocalDateTime.of(2023, 12, 25, 12, 1), shortEvent.getEndTime());
    }

    @Test
    void testEventWithCrossDayTimes() throws EmptyDescriptionException {
        Event crossDay = new Event("Cross day event", "2023-12-25 2300", "2023-12-26 0100");
        assertEquals(LocalDateTime.of(2023, 12, 25, 23, 0), crossDay.getStartTime());
        assertEquals(LocalDateTime.of(2023, 12, 26, 1, 0), crossDay.getEndTime());
    }
}
