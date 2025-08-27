package tasks;

import exception.RainyException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void toFileFormat_notDone_correctFormat() throws RainyException {
        Deadline deadline = new Deadline("submit report", "2025-12-31 2359");
        assertEquals("D | 0 | submit report | 2025-12-31T23:59", deadline.toFileFormat());
    }

    @Test
    public void toFileFormat_done_correctFormat() throws RainyException {
        Deadline deadline = new Deadline("submit report", "31/12/2025 2359");
        deadline.markAsDone();
        assertEquals("D | 1 | submit report | 2025-12-31T23:59", deadline.toFileFormat());
    }

    @Test
    public void invalidDateFormat_throwsException() {
        assertThrows(RainyException.class, () -> new Deadline("submit report", "31-12-2025 11:59PM"));
    }

    @Test
    public void constructorWithLocalDateTime_correctFormat() {
        LocalDateTime dateTime = LocalDateTime.of(2025, 12, 31, 23, 59);
        Deadline deadline = new Deadline("submit report", dateTime);
        assertEquals("D | 0 | submit report | 2025-12-31T23:59", deadline.toFileFormat());
    }

    @Test
    public void testToString() throws RainyException {
        Deadline deadline = new Deadline("submit report", "2025-08-30 1100");
        assertEquals("[D][ ] submit report (by: Aug 30 2025 11:00 am)", deadline.toString());
    }

    @Test
    public void testMarkAsDone() throws RainyException {
        Deadline deadline = new Deadline("submit report", "2025-08-30 1100");
        deadline.markAsDone();
        assertEquals("[D][X] submit report (by: Aug 30 2025 11:00 am)", deadline.toString());
    }
}

