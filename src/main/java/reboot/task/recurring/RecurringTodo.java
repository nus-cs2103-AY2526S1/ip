package reboot.task.recurring;

import java.time.LocalDate;

import reboot.task.Todo;

/**
 * Represents a repeating basic task that has to be done.
 */
public class RecurringTodo extends Todo {

    // Only "daily", "weekly", "monthly" is accepted for now
    private final String frequency;
    private LocalDate startDate;

    /**
     * Creates a RecurringTodo with the specified frequency to repeat.
     */
    public RecurringTodo(String description, boolean isDone, String frequency) {
        super(description, isDone);
        this.frequency = frequency;
        this.startDate = LocalDate.now();
    }

    public void setStartDate(LocalDate date) {
        this.startDate = date;
    }

    /**
     * Checks if the deadline day has passed and auto-update to the next occurrence.
     * Unmarks the task.
     */
    public void updateDateIfOverdue() {
        LocalDate today = LocalDate.now();
        while (startDate.isBefore(today)) {
            this.unmark();
            switch (frequency) {
            case "daily":
                setStartDate(startDate.plusDays(1));
                break;
            case "weekly":
                setStartDate(startDate.plusWeeks(1));
                break;
            case "monthly":
                setStartDate(startDate.plusMonths(1));
                break;
            default:
                throw new IllegalArgumentException("Unknown recurrence: " + frequency);
            }
        }
    }

    public LocalDate getStartDate() {
        return this.startDate;
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
