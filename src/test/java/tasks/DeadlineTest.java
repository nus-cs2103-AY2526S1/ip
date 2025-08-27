package tasks;

import exception.RainyException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

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

