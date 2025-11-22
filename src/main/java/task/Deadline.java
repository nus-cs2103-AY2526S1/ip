package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.GenieweenieException;

/**
 * Represents a Deadline task with a due date.
 */
public class Deadline extends Task {


    private final LocalDate by;


    /**
     * Creates a new Deadline task.
     *
     * @param description description of the deadline
     * @param by due date
     */
    public Deadline(String description, String by) throws GenieweenieException {
        super(description);
        try {
            this.by = LocalDate.parse(by); // expects yyyy-MM-dd format
        } catch (DateTimeParseException e) {
            throw new GenieweenieException("☹ OOPS!!! Invalid date format: " + by);
        }
    }


    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: "
                + by.format(DateTimeFormatter.ofPattern("MMM d yyyy)"));
    }

    @Override
    public String toSaveFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }
}
