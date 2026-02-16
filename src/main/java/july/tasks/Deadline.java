package july.tasks;

import july.datetime.StringTime;
import july.error.InvalidDeadlineException;

/**
 * Represents a task with a specific deadline date and time.
 * This class extends Task to include deadline functionality, allowing tasks
 * to have a "by" date that indicates when the task should be completed.
 */
public class Deadline extends Task {

    protected StringTime by;

    /**
     * Constructs a Deadline task with the specified description and deadline.
     *
     * @param description the description of the deadline task
     * @param by the deadline date/time as a string
     */
    public Deadline(String description, String by) {
        super(description);
        assert by != null : "Deadline time cannot be null";
        this.by = new StringTime(by);
    }

    /**
     * Processes a deadline command argument to create a Deadline object.
     * Parses the input string to extract the task description and deadline,
     * expecting the format "description /by deadline".
     *
     * @param argument the command argument containing description and deadline separated by "/by"
     * @return a new Deadline object created from the parsed input
     * @throws InvalidDeadlineException if the argument doesn't contain exactly one "/by" separator
     */
    public static Deadline process(String argument) throws InvalidDeadlineException {
        assert argument != null : "Argument cannot be null";
        String[] tmp = argument.split("/by", 2);
        if (tmp.length != 2) {
            throw new InvalidDeadlineException("You need to have ONE '/by' in deadline command");
        }
        String description = tmp[0].trim();
        String by = tmp[1].trim();
        assert !description.isEmpty() : "Description cannot be empty";
        assert !by.isEmpty() : "Deadline time cannot be empty";
        return new Deadline(description, by);
    }

    /**
     * Returns a string representation of this deadline task.
     * Includes the task type marker "[D]", the parent task information,
     * and the deadline in parentheses.
     *
     * @return a formatted string showing the deadline task details
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the save format representation of this deadline task.
     * Creates a string suitable for file storage containing the task type,
     * completion status, description, and deadline.
     *
     * @return a formatted string for saving the deadline task to storage
     */
    public String toSave() {
        return String.format("D %s %s /by %s", super.isDone ? "1" : "0", super.description, this.by.toSave());
    }

    /**
     * Checks if this deadline task is relevant for the specified date.
     * Compares the deadline date with the given date to determine if the task
     * should be included in date-specific queries.
     *
     * @param o the StringTime object representing the date to check against
     * @return true if the deadline is on or after the specified date and both dates are valid,
     *         false if either date is invalid or the deadline is before the specified date
     */
    public boolean check(StringTime o) {
        if (by.isString() || o.isString()) {
            return false;
        }
        return by.compareTo(o) >= 0 && !super.isDone;
    }

}
