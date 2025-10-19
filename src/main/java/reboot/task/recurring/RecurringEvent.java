package reboot.task.recurring;

import java.time.LocalDate;
import java.time.LocalDateTime;

import reboot.task.Event;

/**
 * Represents a repeating task that has a start time and end time.
 */
public class RecurringEvent extends Event {

    // Only "daily", "weekly", "monthly" is accepted for now
    private final String frequency;

    /**
     * Creates a RecurringEvent that has a start date and end date.
     */
    public RecurringEvent(String description, boolean isDone, LocalDate start, LocalDate end,
                          String frequency) {
        super(description, isDone, start, end);
        this.frequency = frequency;
    }

    /**
     * Creates a RecurringEvent that has a start and end date and time.
     */
    public RecurringEvent(String description, boolean isDone, LocalDateTime start, LocalDateTime end,
                          String frequency) {
        super(description, isDone, start, end);
        this.frequency = frequency;
    }

    /**
     * Checks if the deadline day has passed and auto-update to the next occurrence.
     * Unmarks the task.
     */
    public void updateDateIfOverdue() {
        LocalDate today = LocalDate.now();
        while (getEndDate().isBefore(today)) {
            this.unmark();
            switch (frequency) {
            case "daily":
                setStartDate(getStartDate().plusDays(1));
                setEndDate(getEndDate().plusDays(1));
                break;
            case "weekly":
                setStartDate(getStartDate().plusWeeks(1));
                setEndDate(getEndDate().plusWeeks(1));
                break;
            case "monthly":
                setStartDate(getStartDate().plusMonths(1));
                setEndDate(getEndDate().plusMonths(1));
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
        while (getEndDateTime().isBefore(today)) {
            switch (frequency) {
            case "daily":
                setStartDateTime(getStartDateTime().plusDays(1));
                setEndDateTime(getEndDateTime().plusDays(1));
                break;
            case "weekly":
                setStartDateTime(getStartDateTime().plusWeeks(1));
                setEndDateTime(getEndDateTime().plusWeeks(1));
                break;
            case "monthly":
                setStartDateTime(getStartDateTime().plusMonths(1));
                setEndDateTime(getEndDateTime().plusMonths(1));
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
