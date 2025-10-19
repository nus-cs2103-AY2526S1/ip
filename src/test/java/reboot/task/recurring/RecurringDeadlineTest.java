package reboot.task.recurring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecurringDeadlineTest {

    @Test
    public void toString_withDateOnly_success() {
        RecurringDeadline d = new RecurringDeadline("return book", false,
                LocalDate.of(2025, 12, 2), "DAILY");
        String expected = "[D][ ] return book (by: 02 12 2025) (repeats daily)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void toString_withDateTime_success() {
        RecurringDeadline d = new RecurringDeadline("return book", false,
                LocalDateTime.of(2025, 12, 2, 21, 0), "monthly");
        String expected = "[D][ ] return book (by: 02 12 2025 2100) (repeats monthly)";
        assertEquals(expected, d.toString());
    }

    @Test
    public void toFileString_withDateOnly_success() {
        RecurringDeadline d = new RecurringDeadline("return book", false,
                LocalDate.of(2025, 12, 2), "daily");
        String expected = "RD | 0 | return book | 02 12 2025 | daily";
        assertEquals(expected, d.toFileString());
    }

    @Test
    public void toFileString_withDateTime_success() {
        RecurringDeadline d = new RecurringDeadline("return book", false,
                LocalDateTime.of(2025, 12, 2, 21, 0), "monthly");
        String expected = "RD | 0 | return book | 02 12 2025 2100 | monthly";
        assertEquals(expected, d.toFileString());
    }

    @Test
    public void updateIfOverdue_dailyTask_rollsForward() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        RecurringDeadline task = new RecurringDeadline("Daily exercise", false,
                LocalDate.of(2025, 12, 2), "DAILY");
        task.updateDateIfOverdue();
        assertTrue(task.getDueDate().isAfter(yesterday));
    }

    @Test
    public void updateIfOverdue_weeklyTask_rollsForward() {
        LocalDate past = LocalDate.now().minusWeeks(2);
        RecurringDeadline task = new RecurringDeadline("Daily exercise", false,
                LocalDate.of(2025, 12, 2), "DAILY");
        task.updateDateIfOverdue();
        assertTrue(task.getDueDate().isAfter(past));
    }
}
