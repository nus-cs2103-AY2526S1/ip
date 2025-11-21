package jamal.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeadlineTest {

    @Test
    public void deadlineBadDateTest() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            new Deadline("test", "2020-10-10");
        });
    }

    @Test
    public void deadlineOngoingTest() {
        assertEquals((new Deadline("test", "2020-10-10T00:00:00").isOngoing()),false);
    }

    @Test
    public void deadlineOverdueTest() {
        assertEquals((new Deadline("test", "2020-10-10T00:00:00").isOverdue()),true);
    }

    @Test
    public void deadlineUpcomingTest() {
        assertEquals((new Deadline("test", "2028-10-10T00:00:00").isUpcoming()),false);
    }
}
