package jimbot.tasktype;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the Event class, which represents tasks occurring
 * over a period of time with defined start and end points.
 * Note: AI assistance was used to aid in the creation of this test class.
 *
 * @author limjimin-nus
 */
class EventTest {

    @Test
    void testValidEventCreation() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 18, 12, 0);
        Event event = new Event("Meeting", from, to);

        assertEquals("Meeting", event.getDescription());
        assertEquals(from.toLocalDate(), event.getFrom());
        assertEquals(to.toLocalDate(), event.getTo());
        assertEquals(from, event.getFromDateTime());
    }

    @Test
    void testInvalidNullDescriptionThrowsAssertionError() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 18, 12, 0);
        assertThrows(AssertionError.class, () -> new Event(null, from, to));
    }

    @Test
    void testInvalidEmptyDescriptionThrowsAssertionError() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 10, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 18, 12, 0);
        assertThrows(AssertionError.class, () -> new Event("", from, to));
    }

    @Test
    void testNullFromThrowsAssertionError() {
        LocalDateTime to = LocalDateTime.of(2025, 9, 18, 12, 0);
        assertThrows(AssertionError.class, () -> new Event("Meeting", null, to));
    }

    @Test
    void testNullToThrowsAssertionError() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 10, 0);
        assertThrows(AssertionError.class, () -> new Event("Meeting", from, null));
    }

    @Test
    void testToBeforeFromThrowsAssertionError() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 12, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 18, 10, 0);
        assertThrows(AssertionError.class, () -> new Event("Meeting", from, to));
    }

    @Test
    void testToStringWithMidnightFormatting() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 0, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 19, 0, 0);
        Event event = new Event("Conference", from, to);

        String output = event.toString();
        assertTrue(output.contains("FROM: Sep 18 2025"));
        assertTrue(output.contains("TO: Sep 19 2025"));
    }

    @Test
    void testToStringWithTimeFormatting() {
        LocalDateTime from = LocalDateTime.of(2025, 9, 18, 10, 30);
        LocalDateTime to = LocalDateTime.of(2025, 9, 18, 15, 0);
        Event event = new Event("Workshop", from, to);

        String output = event.toString();
        assertTrue(output.contains("FROM: Sep 18 2025, 10:30"));
        assertTrue(output.contains("TO: Sep 18 2025, 15:00"));
    }
}
