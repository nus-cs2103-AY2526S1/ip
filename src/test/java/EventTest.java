import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

public class EventTest {

    @Test
    public void testEventWithDateTime() {
        Event event = new Event("team meeting", "2019-12-03 1400", "2019-12-03 1500");
        assertEquals("team meeting", event.description);
        assertFalse(event.isDone);
        assertTrue(event.toString().contains("from: Dec 03 2019, 2:00 pm"));
        assertTrue(event.toString().contains("to: Dec 03 2019, 3:00 pm"));
    }

    @Test
    public void testEventWithDateOnly() {
        Event event = new Event("conference", "2019-12-15", "2019-12-16");
        assertEquals("conference", event.description);
        assertFalse(event.isDone);
        assertTrue(event.toString().contains("from: Dec 15 2019, 12:00 am"));
        assertTrue(event.toString().contains("to: Dec 16 2019, 12:00 am"));
    }

    @Test
    public void testEventWithMDYFormat() {
        Event event = new Event("meeting", "12/3/2019 1400", "12/3/2019 1500");
        assertEquals("meeting", event.description);
        assertFalse(event.isDone);
        assertTrue(event.toString().contains("from: Dec 03 2019, 2:00 pm"));
        assertTrue(event.toString().contains("to: Dec 03 2019, 3:00 pm"));
    }

    @Test
    public void testEventWithMDYFormatDateOnly() {
        Event event = new Event("workshop", "12/15/2019", "12/16/2019");
        assertEquals("workshop", event.description);
        assertFalse(event.isDone);
        assertTrue(event.toString().contains("from: Dec 15 2019, 12:00 am"));
        assertTrue(event.toString().contains("to: Dec 16 2019, 12:00 am"));
    }

    @Test
    public void testEventWithLocalDateTime() {
        LocalDateTime from = LocalDateTime.of(2019, 12, 3, 14, 0);
        LocalDateTime to = LocalDateTime.of(2019, 12, 3, 15, 0);
        Event event = new Event("team meeting", from, to);
        assertEquals("team meeting", event.description);
        assertFalse(event.isDone);
        assertEquals(from, event.getFrom());
        assertEquals(to, event.getTo());
    }

    @Test
    public void testEventMarkAsDone() {
        Event event = new Event("team meeting", "2019-12-03 1400", "2019-12-03 1500");
        assertFalse(event.isDone);
        event.markAsDone();
        assertTrue(event.isDone);
        assertTrue(event.toString().contains("[X]"));
    }

    @Test
    public void testEventMarkAsNotDone() {
        Event event = new Event("team meeting", "2019-12-03 1400", "2019-12-03 1500");
        event.markAsDone();
        assertTrue(event.isDone);
        event.markAsNotDone();
        assertFalse(event.isDone);
        assertTrue(event.toString().contains("[ ]"));
    }

    @Test
    public void testEventToString() {
        Event event = new Event("team meeting", "2019-12-03 1400", "2019-12-03 1500");
        String expected = "[E][ ] team meeting (from: Dec 03 2019, 2:00 pm to: Dec 03 2019, 3:00 pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventToStringMarked() {
        Event event = new Event("team meeting", "2019-12-03 1400", "2019-12-03 1500");
        event.markAsDone();
        String expected = "[E][X] team meeting (from: Dec 03 2019, 2:00 pm to: Dec 03 2019, 3:00 pm)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testEventInvalidDateFormat() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("meeting", "invalid date", "2019-12-03 1500");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("meeting", "2019-12-03 1400", "invalid date");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            new Event("meeting", "32/13/2019", "2019-12-03 1500");
        });
    }

    @Test
    public void testEventEdgeCases() {
        Event event1 = new Event("event 1", "1/1/2019 0000", "1/1/2019 2359");
        assertTrue(event1.toString().contains("from: Jan 01 2019, 12:00 am"));
        assertTrue(event1.toString().contains("to: Jan 01 2019, 11:59 pm"));
        
        Event event2 = new Event("event 2", "12/31/2019 0000", "12/31/2019 2359");
        assertTrue(event2.toString().contains("from: Dec 31 2019, 12:00 am"));
        assertTrue(event2.toString().contains("to: Dec 31 2019, 11:59 pm"));
        
        Event event3 = new Event("event 3", "2019-01-01 0000", "2019-01-01 2359");
        assertTrue(event3.toString().contains("from: Jan 01 2019, 12:00 am"));
        assertTrue(event3.toString().contains("to: Jan 01 2019, 11:59 pm"));
    }

    @Test
    public void testEventGetFromAndTo() {
        LocalDateTime expectedFrom = LocalDateTime.of(2019, 12, 3, 14, 0);
        LocalDateTime expectedTo = LocalDateTime.of(2019, 12, 3, 15, 0);
        Event event = new Event("team meeting", "2019-12-03 1400", "2019-12-03 1500");
        assertEquals(expectedFrom, event.getFrom());
        assertEquals(expectedTo, event.getTo());
    }

    @Test
    public void testEventCrossDay() {
        Event event = new Event("overnight event", "2019-12-03 2300", "2019-12-04 0100");
        assertEquals("overnight event", event.description);
        assertTrue(event.toString().contains("from: Dec 03 2019, 11:00 pm"));
        assertTrue(event.toString().contains("to: Dec 04 2019, 1:00 am"));
    }
}
