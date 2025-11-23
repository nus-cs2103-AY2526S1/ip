package jinwoo.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import jinwoo.exception.DukeException;
import jinwoo.util.Parser;

/**
 * A deadline task is a task that needs to be done by a specific date or time.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a Tasks.Deadline task with the given description and deadline.
     *
     * @throws DukeException if the description or deadline is empty.
     */
    public Deadline(String description, String by) throws DukeException {
        super(description);
        this.by = Parser.parseToDate(by);
        if (description.isBlank()) {
            throw new DukeException("Warning ??? : the description of a deadline cannot be empty.");
        }
        if (by.isBlank()) {
            throw new DukeException("Warning ??? : the deadline of a deadline cannot be empty.");
        }
    }

    /**
     * LocalDate to String
     * Storage print purpose
     */
    public String getBy() {
        return by.toString();
    }

    /**
     * Creates and returns a copy for different history states.
     * Refer to copilot suggestion.
     */
    @Override
    public Task copyTask() throws DukeException {
        Deadline copy = new Deadline(this.description, this.by.toString());
        if (this.isDone) {
            copy.mark();
        }
        return copy;
    }


    /**
     * Get description for user print purpose
     */
    @Override
    public String getDescription() {
        return description + " (by: " + by.format(DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH)) + ")";
    }

    @Override
    public String toString() {
        return " [D]" + super.toString();
    }
}
