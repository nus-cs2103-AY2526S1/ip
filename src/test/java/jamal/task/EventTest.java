package jamal.task;

import org.junit.jupiter.api.Test;

import java.time.format.DateTimeParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EventTest {

    @Test
    public void eventBadDateTest() {
        DateTimeParseException exception = assertThrows(DateTimeParseException.class, () -> {
            new Event("test", "2020-10-10", "2021-10-10");
        });
    }

    @Test
    public void eventOngoingTest() {
        assertEquals((new Event("test", "2020-10-10T00:00:00", "2020-10-10T00:00:01").isOngoing()),false);
    }

    @Test
    public void eventOverdueTest() {
        assertEquals((new Event("test", "2020-10-10T00:00:00", "2020-10-10T00:00:02").isOverdue()),true);
    }

    @Test
    public void eventUpcomingTest() {
        assertEquals((new Event("test", "2039-10-10T00:00:00", "2040-10-10T00:00:00").isUpcoming()),true);
    }
}
