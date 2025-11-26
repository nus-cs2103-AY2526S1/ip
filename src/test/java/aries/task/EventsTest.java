package aries.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import aries.util.DateTime;

public class EventsTest {
    @Test
    public void testToString() {
        Events event = new Events("Project Meeting", "2023-10-01 1000", "2023-10-01 1100");
        String expected = "[E] [ ] Project Meeting (from: OCT 1 2023, 10:00AM) (to: OCT 1 2023, 11:00AM)";
        assertEquals(expected, event.toString());
    }

    @Test
    public void testDateTimeParsing() {
        Events event = new Events("Conference", "2023-12-15 0900", "2023-12-15 1700");
        LocalDateTime expectedFrom = LocalDateTime.of(2023, 12, 15, 9, 0);
        LocalDateTime expectedTo = LocalDateTime.of(2023, 12, 15, 17, 0);
        assertEquals(expectedFrom, event.fromDate);
        assertEquals(expectedTo, event.toDate);
    }

    @Test
    public void testDateTimeFormatting() {
        Events event = new Events("Workshop", "2024-01-20 1400", "2024-01-20 1600");
        String formattedFrom = DateTime.format(event.fromDate);
        String formattedTo = DateTime.format(event.toDate);
        assertEquals("JAN 20 2024, 2:00PM", formattedFrom);
        assertEquals("JAN 20 2024, 4:00PM", formattedTo);
    }

    @Test
    public void testMarkAndUnmark() {
        Events event = new Events("Team Building", "2023-11-05 1300", "2023-11-05 1700");
        event.markAsDone();
        assertEquals("[E] [X] Team Building (from: NOV 5 2023, 1:00PM) (to: NOV 5 2023, 5:00PM)", event.toString());
        event.unmark();
        assertEquals("[E] [ ] Team Building (from: NOV 5 2023, 1:00PM) (to: NOV 5 2023, 5:00PM)", event.toString());
    }
}
