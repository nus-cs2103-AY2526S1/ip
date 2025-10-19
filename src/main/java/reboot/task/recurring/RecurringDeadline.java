package reboot.task.recurring;

import java.time.LocalDate;
import java.time.LocalDateTime;

import reboot.task.Deadline;

/**
 * Represents a repeating task that has a deadline.
 */
public class RecurringDeadline extends Deadline {

    // Only "daily", "weekly", "monthly" is accepted for now
    private final String frequency;

    /**
     * Creates a RecurringDeadline with the specified due date.
     */
    public RecurringDeadline(String description, boolean isDone, LocalDate dueDate,
                             String frequency) {
        super(description, isDone, dueDate);
        this.frequency = frequency.toLowerCase();
    }

    /**
     * Creates a RecurringDeadline with the specified due date and time.
     */
    public RecurringDeadline(String description, boolean isDone, LocalDateTime dueDateTime,
                             String frequency) {
        super(description, isDone, dueDateTime);
        this.frequency = frequency.toLowerCase();
    }

    /**
     * Checks if the deadline day has passed and auto-update to the next occurrence.
     * Unmarks the task.
     */
    public void updateDateIfOverdue() {
        LocalDate today = LocalDate.now();
        while (getDueDate().isBefore(today)) {
            this.unmark();
            switch (frequency) {
            case "daily":
                setDueDate(getDueDate().plusDays(1));
                break;
            case "weekly":
                setDueDate(getDueDate().plusWeeks(1));
                break;
            case "monthly":
                setDueDate(getDueDate().plusMonths(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown recurrence: " + frequency);
            }
        }
    }

    /**
     * Checks if the deadline day and time has passed and auto-update to the next occurrence.
     * Unmarks the task.
     */
    public void updateDateTimeIfOverdue() {
        LocalDateTime today = LocalDateTime.now();
        while (getDueDateTime().isBefore(today)) {
            switch (frequency) {
            case "daily":
                setDueDateTime(getDueDateTime().plusDays(1));
                break;
            case "weekly":
                setDueDateTime(getDueDateTime().plusWeeks(1));
                break;
            case "monthly":
                setDueDateTime(getDueDateTime().plusMonths(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown recurrence: " + frequency);
            }
        }
    }

    @Override
    public String toFileString() {
        return "R" + super.toFileString() + " | " + frequency;
    }

    @Override
    public String toString() {
        return super.toString() + " (repeats " + frequency + ")";
    }
}
