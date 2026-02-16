package geni.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import geni.exception.GeniException;

public class DeadlineTest {

    @Test
    public void testGetStatusIcon() throws GeniException {
        Deadline deadline = new Deadline("Submit report", "2025-09-01 1800");
        assertEquals(" ", deadline.getStatusIcon(), "New deadline should be not done");
        deadline.markAsDone();
        assertEquals("X", deadline.getStatusIcon());
    }

    @Test
    public void testToStringFormat() throws GeniException {
        Deadline deadline = new Deadline("Submit report", "2025-09-01 1800");
        String expected = "[D][ ] Submit report (by: Sep 1 2025, 6:00pm)";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void testToSaveFormatNotDone() throws GeniException {
        Deadline deadline = new Deadline("Submit report", "2025-09-01 1800");
        String expected = "D | false | Submit report | 2025-09-01 1800";
        assertEquals(expected, deadline.toSaveFormat());
    }

    @Test
    public void testToSaveFormatDone() throws GeniException {
        Deadline deadline = new Deadline("Submit report", "2025-09-01 1800");
        deadline.markAsDone();
        String expected = "D | true | Submit report | 2025-09-01 1800";
        assertEquals(expected, deadline.toSaveFormat());
    }

    @Test
    public void testInvalidDateFormatThrowsException() {
        Exception exception = assertThrows(GeniException.class, () -> {
            new Deadline("Submit report", "09-01-2025 1800");
        });
        String expectedMessage = "Invalid date-time format! Please use format: yyyy-MM-dd HHmm";
        assertTrue(exception.getMessage().contains(expectedMessage));
    }
}
