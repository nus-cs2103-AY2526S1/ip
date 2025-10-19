package reboot.task.recurring;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class RecurringTodoTest {

    @Test
    public void updateIfOverdue_dailyTask_rollsForward() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        RecurringTodo task = new RecurringTodo("Daily exercise", false, "DAILY");
        task.updateDateIfOverdue();
        assertTrue(task.getStartDate().isAfter(yesterday));
    }

    @Test
    public void updateIfOverdue_weeklyTask_rollsForward() {
        LocalDate past = LocalDate.now().minusWeeks(2);
        RecurringTodo task = new RecurringTodo("Daily exercise", false, "WEEKLY");
        task.updateDateIfOverdue();
        assertTrue(task.getStartDate().isAfter(past));
    }
}
