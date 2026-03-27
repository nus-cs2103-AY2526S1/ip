package joe.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Type of task that contains a description and due date/time.
 */
public class Deadline extends Task {
    private String deadline;

    private static String formatDateTime(String by) {
        // Expecting input like "yyyy-MM-dd HHmm" (e.g. 2019-12-02 1800)
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

        String formatted = LocalDateTime.parse(by, inputFormat).format(outputFormat);

        return " (by: " + formatted + ")";
    }

    /**
     * Creates a Deadline task that has not been completed.
     *
     * @param description Description of the task.
     * @param by Due date/time of the task in "yyyy-MM-dd HHmm".
     */
    public Deadline(String description, String by) {
        super(description + formatDateTime(by));
        this.deadline = by;
    }

    /**
     * Creates a Deadline task whose completion status can be specified.
     *
     * @param description Description of the task.
     * @param by Due date/time of the task in "yyyy-MM-dd HHmm".
     * @param isDone Completion status of the task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description + formatDateTime(by), isDone);
        this.deadline = by;
    }

    public String getNextDate() {
        return this.deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString();
    }
}
