package aries.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import aries.util.DateTime;

public class DeadlineTest {
    @Test
    public void testToString() {
        Deadline deadline = new Deadline("Submit report", "2023-12-01 1700");
        String expected = "[D] [ ] Submit report (by: DEC 1 2023, 5:00PM)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testLocalDateBehaviour() {
        Deadline deadline = new Deadline("Finish project", "2023-11-30 2359");
        LocalDateTime expectedDateTime = LocalDateTime.of(2023, 11, 30, 23, 59);
        assertEquals(expectedDateTime, deadline.by);
    }

    @Test
    public void testDateTimeFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2024, 1, 15, 9, 30);
        String formatted = DateTime.format(dateTime);
        assertEquals("JAN 15 2024, 9:30AM", formatted);
    }

    @Test
    public void testMarkAndUnmark() {
        Deadline deadline = new Deadline("Prepare slides", "2023-10-20 1200");
        deadline.markAsDone();
        assertEquals("[D] [X] Prepare slides (by: OCT 20 2023, 12:00PM)", deadline.toString());
        deadline.unmark();
        assertEquals("[D] [ ] Prepare slides (by: OCT 20 2023, 12:00PM)", deadline.toString());
    }
}
