package dino;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline type task with task description and task deadline.
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a new Deadline task with the given description and deadline.
     */
    public Deadline(String description, String by) {
        super(description, TaskType.DEADLINE);
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.by = LocalDate.parse(by, inputFormat);
    }

    /**
     * Returns the string representation of the Deadline task
     *
     * @return string containing the task type, status and description
     */
    @Override
    public String toString() {
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(outputFormat) + ")" ;
    }

    /**
     * Returns the string representation of the Deadline task for storage.
     *
     * @return string of the task in the storing format
     */
    @Override
    public String toStoreFormat() {
        String tagName = this.tag != null ? this.tag : "";
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by +  " | " + tagName;
    }

}
