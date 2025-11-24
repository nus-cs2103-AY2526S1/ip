package shaduke.tasks;

import shaduke.ShadukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a due date.
 */
public class Deadline extends Task {
    private LocalDate by;

    private static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Constructs a new deadline task with the given description and by strings.
     *
     * @param description the description of the task.
     * @param byStr the due date of the task.
     */
    public Deadline(String description, String byStr) {
        this(description, byStr, false);
    }

    /**
     * Constructs an existing deadline task with the given description and by string.
     *
     * @param description the description of the task.
     * @param byStr the due date of the task.
     * @param isDone whether a task is marked as done.
     */
    public Deadline(String description, String byStr, boolean isDone) {
        super(description, isDone);
        try {
            this.by = LocalDate.parse(byStr, INPUT_FORMAT);
        } catch (DateTimeParseException e) {
            throw new ShadukeException("Please use yyyy-mm-dd format for deadlines!");
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(OUTPUT_FORMAT) + ") " + getClientString();
    }

    @Override
    public String store() {
        return "D" + super.store() + " | " + by.format(INPUT_FORMAT);
    }
}
