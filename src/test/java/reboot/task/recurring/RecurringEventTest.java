package reboot.task.recurring;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class RecurringEventTest {

    @Test
    public void toString_withDateOnly_success() {
        RecurringEvent e = new RecurringEvent("project meeting", false,
                LocalDate.of(2025, 12, 2),
                LocalDate.of(2025, 12, 3), "daily");
        String expected = "[E][ ] project meeting " +
                "(from: 02 12 2025 to: 03 12 2025) (repeats daily)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void toString_withDateTime_success() {
        RecurringEvent e = new RecurringEvent("project meeting", false,
                LocalDateTime.of(2025, 12, 2, 8, 0),
                LocalDateTime.of(2025, 12, 2, 10, 0), "weekly");
        String expected = "[E][ ] project meeting " +
                "(from: 02 12 2025 0800 to: 02 12 2025 1000) (repeats weekly)";
        assertEquals(expected, e.toString());
    }

    @Test
    public void toFileString_withDateOnly_success() {
        RecurringEvent e = new RecurringEvent("project meeting", false,
                LocalDate.of(2025, 12, 2),
                LocalDate.of(2025, 12, 3), "daily");
        String expected = "RE | 0 | project meeting | 02 12 2025 | 03 12 2025 | daily";
        assertEquals(expected, e.toFileString());
    }

    @Test
    public void toFileString_withDateTime_success() {
        RecurringEvent e = new RecurringEvent("project meeting", false,
                LocalDateTime.of(2025, 12, 2, 8, 0),
                LocalDateTime.of(2025, 12, 2, 10, 0), "weekly");
        String expected = "RE | 0 | project meeting | 02 12 2025 0800 | 02 12 2025 1000 | weekly";
        assertEquals(expected, e.toFileString());
    }

    @Test
    public void updateIfOverdue_dailyTask_rollsForward() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        RecurringEvent task = new RecurringEvent("project meeting", false,
                LocalDate.of(2025, 12, 2),
                LocalDate.of(2025, 12, 3), "daily");
        task.updateDateIfOverdue();
        assertTrue(task.getStartDate().isAfter(yesterday));
    }

    @Test
    public void updateIfOverdue_weeklyTask_rollsForward() {
        LocalDateTime past = LocalDateTime.now().minusWeeks(2);
        RecurringEvent task = new RecurringEvent("project meeting", false,
                LocalDateTime.of(2025, 12, 2, 8, 0),
                LocalDateTime.of(2025, 12, 2, 10, 0), "weekly");
        task.updateDateTimeIfOverdue();
        assertTrue(task.getStartDateTime().isAfter(past));
    }
}
