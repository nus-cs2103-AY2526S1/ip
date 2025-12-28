package meeseeks.task;

import java.time.LocalDateTime;

/**
 * Represents a recurring task that repeats at specified intervals.
 * Extends the base Task class to add recurrence functionality.
 */
public class RecurringTask extends Task {
    private static final String RECURRING_TASK_TYPE = "R";
    private static final String DONE_FILE_VALUE = "1";
    private static final String NOT_DONE_FILE_VALUE = "0";
    
    private RecurrenceFrequency frequency;
    private LocalDateTime nextDueDate;
    private LocalDateTime lastCompletedDate;

    /**
     * Creates a new recurring task.
     * 
     * @param description the task description
     * @param frequency the recurrence frequency
     * @param nextDueDate the next due date for the task
     */
    public RecurringTask(String description, RecurrenceFrequency frequency, LocalDateTime nextDueDate) {
        super(description);
        assert frequency != null : "Frequency cannot be null";
        assert nextDueDate != null : "Next due date cannot be null";
        
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
        this.lastCompletedDate = null;
    }

    /**
     * Creates a recurring task from file format.
     * 
     * @param description the task description
     * @param frequency the recurrence frequency
     * @param nextDueDate the next due date
     * @param lastCompletedDate the last completed date (can be null)
     * @param isDone whether the task is currently done
     */
    public RecurringTask(String description, RecurrenceFrequency frequency, 
                        LocalDateTime nextDueDate, LocalDateTime lastCompletedDate, boolean isDone) {
        super(description);
        assert frequency != null : "Frequency cannot be null";
        assert nextDueDate != null : "Next due date cannot be null";
        
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
        this.lastCompletedDate = lastCompletedDate;
        
        if (isDone) {
            markAsDone();
        }
    }

    /**
     * Gets the recurrence frequency of this task.
     * 
     * @return the recurrence frequency (daily, weekly, monthly, or yearly)
     */
    public RecurrenceFrequency getFrequency() {
        return frequency;
    }

    /**
     * Gets the next due date for this recurring task.
     * 
     * @return the next due date as a LocalDateTime
     */
    public LocalDateTime getNextDueDate() {
        return nextDueDate;
    }

    /**
     * Gets the last completed date of this recurring task.
     * 
     * @return the last completed date, or null if never completed
     */
    public LocalDateTime getLastCompletedDate() {
        return lastCompletedDate;
    }

    /**
     * Marks the task as done and updates the next due date based on frequency.
     */
    @Override
    public void markAsDone() {
        super.markAsDone();
        lastCompletedDate = LocalDateTime.now();
        updateNextDueDate();
    }

    /**
     * Updates the next due date based on the recurrence frequency.
     */
    private void updateNextDueDate() {
        switch (frequency) {
            case DAILY:
                nextDueDate = nextDueDate.plusDays(1);
                break;
            case WEEKLY:
                nextDueDate = nextDueDate.plusWeeks(1);
                break;
            case MONTHLY:
                nextDueDate = nextDueDate.plusMonths(1);
                break;
            case YEARLY:
                nextDueDate = nextDueDate.plusYears(1);
                break;
        }
    }

    /**
     * Checks if the task is overdue.
     * 
     * @return true if the task is overdue, false otherwise
     */
    public boolean isOverdue() {
        return !isDone() && nextDueDate.isBefore(LocalDateTime.now());
    }

    /**
     * Gets the number of days until the next due date.
     * 
     * @return the number of days until due (negative if overdue)
     */
    public long getDaysUntilDue() {
        return java.time.temporal.ChronoUnit.DAYS.between(LocalDateTime.now(), nextDueDate);
    }

    @Override
    public String toString() {
        String baseString = "[R]" + super.toString();
        String dueInfo = " (due: " + nextDueDate + ")";
        String frequencyInfo = " [repeats " + frequency.getDisplayName() + "]";
        
        if (isOverdue()) {
            dueInfo = " (OVERDUE: " + nextDueDate + ")";
        }
        
        return baseString + dueInfo + frequencyInfo;
    }

    @Override
    public String toFileFormat() {
        StringBuilder sb = new StringBuilder();
        sb.append(RECURRING_TASK_TYPE).append(" | ");
        sb.append(isDone() ? DONE_FILE_VALUE : NOT_DONE_FILE_VALUE).append(" | ");
        sb.append(getName()).append(" | ");
        sb.append(frequency.getDisplayName()).append(" | ");
        sb.append(nextDueDate.toString());
        
        if (lastCompletedDate != null) {
            sb.append(" | ").append(lastCompletedDate.toString());
        }
        
        return sb.toString();
    }

}
