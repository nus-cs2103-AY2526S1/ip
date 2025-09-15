package bobby.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import bobby.exception.BobbyException;

public class EventTest {
    /**
     * Checks that correct fields leads to Event creation
     *
     * @throws BobbyException
     */
    @Test
    void testConstructorAndFields() throws BobbyException {
        Event event = new Event("Meeting", false, "2025-08-25 0900", "2025-08-25 1000");
        assertEquals("Meeting", event.description);
        assertEquals(false, event.isMark);
        assertEquals(2025, event.from.getYear());
        assertEquals(9, event.from.getHour());
        assertEquals(10, event.to.getHour());
    }

    /**
     * Checks that incorrect from leads to a BobbyException
     */
    @Test
    void testConstructorInvalidFrom() {
        assertThrows(BobbyException.class, () -> {
            new Event("Test", false, "invalid-date", "2025-08-25 1000");
        });
    }

    /**
     * Checks that incorrect to leads to a BobbyException
     */
    @Test
    void testConstructorInvalidTo() {
        assertThrows(BobbyException.class, () -> {
            new Event("Test", false, "2025-08-25 0900", "invalid-date");
        });
    }

    @Test
    void testGetTaskType() throws BobbyException {
        Event event = new Event("Meeting", false, "2025-08-25 0900", "2025-08-25 1000");
        assertEquals(2, event.getTaskType());
    }

    @Test
    void testToStorage() throws BobbyException {
        Event event = new Event("Conference", true, "2025-12-01 1400", "2025-12-01 1600");
        String expected = "2 | 1 | Conference /from 2025-12-01 1400 /to 2025-12-01 1600";
        assertEquals(expected, event.toStorage());
    }

    @Test
    void testToString() throws BobbyException {
        Event event = new Event("Conference", true, "2025-12-01 1400", "2025-12-01 1600");
        String expected = "[E][X] Conference (from: 01/12/2025 1400 to: 01/12/2025 1600)";
        assertEquals(expected, event.toString());
    }
}
