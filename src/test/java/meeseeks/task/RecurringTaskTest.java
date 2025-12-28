package meeseeks.task;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

class RecurringTaskTest {

    @Test
    void testRecurringTaskCreation() {
        LocalDateTime dueDate = LocalDateTime.of(2024, 1, 15, 14, 0);
        RecurringTask task = new RecurringTask("Weekly team meeting", RecurrenceFrequency.WEEKLY, dueDate);
        
        assertEquals("Weekly team meeting", task.getName());
        assertEquals(RecurrenceFrequency.WEEKLY, task.getFrequency());
        assertEquals(dueDate, task.getNextDueDate());
        assertFalse(task.isDone());
        assertNull(task.getLastCompletedDate());
    }

    @Test
    void testMarkAsDoneUpdatesNextDueDate() {
        LocalDateTime dueDate = LocalDateTime.of(2024, 1, 15, 14, 0);
        RecurringTask task = new RecurringTask("Daily standup", RecurrenceFrequency.DAILY, dueDate);
        
        task.markAsDone();
        
        assertTrue(task.isDone());
        assertNotNull(task.getLastCompletedDate());
        // Next due date should be one day later
        assertEquals(dueDate.plusDays(1), task.getNextDueDate());
    }

    @Test
    void testIsOverdue() {
        LocalDateTime pastDate = LocalDateTime.now().minusDays(1);
        RecurringTask overdueTask = new RecurringTask("Overdue task", RecurrenceFrequency.DAILY, pastDate);
        
        assertTrue(overdueTask.isOverdue());
        
        LocalDateTime futureDate = LocalDateTime.now().plusDays(1);
        RecurringTask futureTask = new RecurringTask("Future task", RecurrenceFrequency.DAILY, futureDate);
        
        assertFalse(futureTask.isOverdue());
    }

    @Test
    void testToFileFormat() {
        LocalDateTime dueDate = LocalDateTime.of(2024, 1, 15, 14, 0);
        RecurringTask task = new RecurringTask("Monthly report", RecurrenceFrequency.MONTHLY, dueDate);
        
        String fileFormat = task.toFileFormat();
        assertTrue(fileFormat.startsWith("R | 0 | Monthly report | monthly | 2024-01-15T14:00"));
    }
}
