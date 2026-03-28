package bugsbunny.tasks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class DeadlineTest {

    @Test
    void isDueBy_beforeDueDate_returnsTrue() {
        LocalDateTime due = LocalDateTime.of(2025, 8, 30, 14, 0);
        Deadline deadline = new Deadline("submit report", due);

        LocalDateTime cutoff = LocalDateTime.of(2025, 8, 30, 16, 0);

        assertTrue(deadline.isDueBy(cutoff),
                "Deadline at 14:00 should be due by cutoff 16:00");
    }

    @Test
    void isDueBy_afterDueDate_returnsFalse() {
        LocalDateTime due = LocalDateTime.of(2025, 8, 30, 18, 0);
        Deadline deadline = new Deadline("submit report", due);

        LocalDateTime cutoff = LocalDateTime.of(2025, 8, 30, 16, 0);

        assertFalse(deadline.isDueBy(cutoff),
                "Deadline at 18:00 should not be due by cutoff 16:00");
    }

    @Test
    void isDueBy_exactDueDate_returnsTrue() {
        LocalDateTime due = LocalDateTime.of(2025, 8, 30, 16, 0);
        Deadline deadline = new Deadline("submit report", due);

        LocalDateTime cutoff = LocalDateTime.of(2025, 8, 30, 16, 0);

        assertTrue(deadline.isDueBy(cutoff),
                "Deadline should be considered due if cutoff equals deadline time");
    }
}
