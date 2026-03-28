package hachi;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.Month;

public class DeadlineTest {

    @Test
    public void createDeadline_withDefaultStatus() {
        LocalDateTime date = LocalDateTime.of(2025, Month.AUGUST, 28, 18, 0);
        Deadline deadline = new Deadline("submit report", date);

        assertEquals("submit report", deadline.description);
        assertFalse(deadline.isCompleted); // default should be false
    }

    @Test
    public void createDeadline_withExplicitStatus() {
        LocalDateTime date = LocalDateTime.of(2025, Month.AUGUST, 28, 18, 0);
        Deadline deadline = new Deadline("submit report", true, date);

        assertTrue(deadline.isCompleted);
    }

    @Test
    public void toString_notDone_correctFormat() {
        LocalDateTime date = LocalDateTime.of(2025, Month.AUGUST, 28, 18, 0);
        Deadline deadline = new Deadline("submit report", date);

        String expected = "[D][ ] submit report | By: Aug 28 2025, 6PM";
        assertEquals(expected, deadline.toString());
    }

    @Test
    public void toString_done_correctFormat() {
        LocalDateTime date = LocalDateTime.of(2025, Month.AUGUST, 28, 18, 0);
        Deadline deadline = new Deadline("submit report", date);
        deadline.mark();

        String expected = "[D][X] submit report | By: Aug 28 2025, 6PM";
        assertEquals(expected, deadline.toString());
    }
}

