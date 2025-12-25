package khat.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import khat.exception.DeadlineTaskException;

public class DeadlineTest {

    @Test
    public void constructor_validDeadline_setsFieldsCorrectly() {
        Deadline deadline = new Deadline("essay", false, "29-08-2025 2359");
        assertEquals("essay", deadline.description);
        assertFalse(deadline.isDone);
        assertEquals("29-08-2025 2359", deadline.by);
        assertTrue(deadline.hasTime());
        assertEquals(LocalDateTime.of(2025, 8, 29, 23, 59), deadline.dateTime);
    }

    @Test
    public void constructor_invalidDeadline_exceptionThrown() {
        try {
            Deadline deadline = new Deadline("essay", false, "29/08/2025");
        } catch (DeadlineTaskException e) {
            assertEquals("Invalid date/time format! Use dd-MM-yyyy or dd-MM-yyyy HHmm!", e.getMessage());
        }
    }

    @Test
    public void toSaveString_validDeadline_returnsCorrectFormat() {
        Deadline deadline = new Deadline("essay", false, "29-08-2025 2359");
        assertEquals("D | 0 | essay | 29-08-2025 2359", deadline.toSaveString());
    }

    @Test
    public void toString_doneDeadline_returnsCorrectFormat() {
        Deadline deadline = new Deadline("essay", true, "29-08-2025 2359");
        assertEquals("[D][X] essay (by: 29 Aug 25 11:59 pm)", deadline.toString());
    }
}
