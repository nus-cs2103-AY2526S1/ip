package botchat.task;

import botchat.util.DateTime;
import java.time.LocalDate;

/**
 * Represents a task that has a deadline.
 */
public class Deadline extends Task {
    private final LocalDate by;

    /**
     * Constructs a task that has a description and a deadline.
     * @param description the String description of a task.
     * @param by the String deadline of the task, in the format yyyy-MM-dd.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = LocalDate.parse(by, DateTime.INPUT_DATE);
    }


    @Override
    public String toStorage() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(DateTime.OUTPUT_DATE) + ")";
    }
}
