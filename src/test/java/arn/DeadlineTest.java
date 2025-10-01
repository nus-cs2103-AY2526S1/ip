package arn;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

//Used ChatGPT to generate JUnit test cases
public class DeadlineTest {
    @Test
    public void testValidDeadlineWithDateTime() throws ArnException {
        Deadline d = new Deadline("submit report", "2025-10-01 1800");
        assertTrue(d.toString().contains("submit report"));
        assertTrue(d.toString().contains("Oct")); // formatted month
        assertEquals("D", d.getType());
    }

    @Test
    public void testValidDeadlineWithDateOnly() throws ArnException {
        Deadline d = new Deadline("exam", "2025-10-01");
        assertFalse(d.formatDate(true).contains(":")); // no time when date only
    }

    @Test
    public void testInvalidDeadlineThrowsException() {
        assertThrows(ArnException.class, () -> new Deadline("oops", "2025/10/01"));
    }
}

