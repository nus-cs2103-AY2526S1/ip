package HawkerUncle.tests;

import HawkerUncle.task.Deadline;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {
    @Test
    public void testGetDescription() {
        Deadline deadline = new Deadline("Return book", LocalDateTime.of(2025, 12 ,25, 12, 0), false);
        assertEquals("Return book", deadline.getDescription());
    }

    @Test
    public void testGetBy() {
        LocalDateTime expectedDeadline = LocalDateTime.of(2025, 12, 25, 12, 0);
        Deadline deadline = new Deadline("Return book", LocalDateTime.of(2025, 12 ,25, 12, 0), false);
        assertEquals(expectedDeadline, deadline.getBy());
    }
}
