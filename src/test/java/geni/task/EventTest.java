package geni.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import geni.exception.GeniException;

public class EventTest {

    @Test
    public void testGetStatusIcon() throws GeniException {
        Event event = new Event("Team Meeting", "2025-09-01 1400", "2025-09-01 1600");
        assertEquals(" ", event.getStatusIcon());
        event.markAsDone();
        assertEquals("X", event.getStatusIcon());
    }

    @Test
    public void testToStringFormat() throws GeniException {
        Event event = new Event("Team Meeting", "2025-09-01 1400", "2025-09-01 1600");
        String expected = "[E][ ] Team Meeting (from: Sep 1 2025, 2:00pm to: Sep 1 2025, 4:00pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testToSaveFormatNotDone() throws GeniException {
        Event event = new Event("Team Meeting", "2025-09-01 1400", "2025-09-01 1600");
        String expected = "E | 0 | Team Meeting | 2025-09-01 1400 - 2025-09-01 1600";
        assertEquals(expected, event.toSaveFormat());
    }

    @Test
    public void testToSaveFormatDone() throws GeniException {
        Event event = new Event("Team Meeting", "2025-09-01 1400", "2025-09-01 1600");
        event.markAsDone();
        String expected = "E | 1 | Team Meeting | 2025-09-01 1400 - 2025-09-01 1600";
        assertEquals(expected, event.toSaveFormat());
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        Exception exception = assertThrows(GeniException.class, () -> {
            new Event("Team Meeting", "01-09-2025 1400", "2025-09-01 1600");
        });
        String expectedMessage = "Invalid date-time format! Please use format: yyyy-MM-dd HHmm";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
