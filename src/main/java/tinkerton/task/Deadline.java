package tinkerton.task;

import tinkerton.util.Date;
import tinkerton.core.TinkertonException;
import java.time.LocalDateTime;

/**
 * Represents a Deadline task with a name, completion status, and a due date/time.
 */
public class Deadline extends Task {
    /** The due date of the deadline task. */
    private Date date;
    /** The time string representing the deadline. */
    private String time;

    /**
     * Constructs a Deadline task.
     *
     * @param name The name of the task.
     * @param isCompleted True if the task is completed, false otherwise.
     * @param time The deadline time as a string.
     * @throws TinkertonException If the deadline is already overdue.
     */
    public Deadline(String name, boolean isCompleted, String time) throws TinkertonException {
        super(name, isCompleted);
        this.time = time;
        this.date = new Date(time);

        if (date.date().isBefore(LocalDateTime.now())) {
            throw new TinkertonException(
                    "Deadline that is already overdue? A bit too late to add that...");
        }
    }

    /**
     * Returns the string representation of the Deadline task for display.
     *
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)", this.date);
    }

    /**
     * Returns the string representation of the Deadline task for file storage.
     *
     * @return String representation for file storage.
     */
    @Override
    public String toFile() {
        String completed = this.isCompleted() ? "1" : "0";
        return "D | " + completed + " | " + this.name() + " | " + this.time;
    }

    /**
     * Checks if the Deadline task occurs on the given date.
     *
     * @param date The date to check.
     * @return True if the deadline is on the given date, false otherwise.
     */
    @Override
    public boolean onDate(Date date) {
        return date.date().toLocalDate().isEqual(this.date.date().toLocalDate());
    }
}
