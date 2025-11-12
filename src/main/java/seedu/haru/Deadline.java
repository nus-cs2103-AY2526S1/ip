package seedu.haru;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task in the Haru chatbot.
 * An Event task has a name, completion status, type, and end date.
 */
public class Deadline extends Task {
    protected LocalDate end;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new Task with the given description, end date and type.
     *
     * @param name name of the task
     * @param end end date of the task
     * @param type type of the task
     */
    public Deadline(String name, String end, Type type) {
        super(name, type);
        assert end != null : "End date string must not be null";
        this.end = LocalDate.parse(end, INPUT_FORMAT);

    }

    /**
     * Returns a string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.end.format(OUTPUT_FORMAT) + ")";
    }

    /**
     * Converts the task to a format suitable for saving in storage.
     *
     * @return a string representation of the task
     */
    @Override
    public String toFileString() {

        return "D | " + super.toFileString() + " | " + this.end;
    }
}
