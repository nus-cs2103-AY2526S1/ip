package charli.task;

import charli.exception.CharliException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeadlineTest {

    @Test
    public void testDeadlineCreation() throws CharliException {
        Deadline deadline = new Deadline("Release album", "25/12/2029 1800");
        assertEquals("Release album", deadline.getDescription());
        assertFalse(deadline.isDone());
        assertTrue(deadline.toString().contains("Release album"));
        assertTrue(deadline.toString().contains("Dec 25 2029, 6:00pm"));
    }

    @Test
    public void testDeadlineWithLocalDateTime() {
        LocalDateTime by = LocalDateTime.of(2029, 12, 25, 18, 0);
        Deadline deadline = new Deadline("Release album", by);
        assertEquals(by, deadline.getBy());
    }

    @Test
    public void testDeadlineMarkAsDone() throws CharliException {
        Deadline deadline = new Deadline("Release album", "25/12/2029 1800");
        deadline.markAsDone();
        assertTrue(deadline.isDone());
        assertTrue(deadline.toString().contains("[✓]"));
    }

    @Test
    public void testInvalidDateThrowsException() {
        assertThrows(DateTimeParseException.class, () -> {
            new Deadline("Release album", "invalid-date");
        });
    }


}
