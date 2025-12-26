package friday.tasks;

import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

/**
 * This represents a task with a specific deadline. A <code>Deadline</code>
 * object corresponds to a deadline with specified deadline represented by
 * a string.
 */
public class Deadlines extends Task {
    protected LocalDateTime deadline;

    private static final DateTimeFormatter inputFormats =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    private static final DateTimeFormatter outputFormatter =
            DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm");

    public Deadlines(String description, String by, String tag) {
        super(description, tag);
        this.deadline = LocalDateTime.parse(by, inputFormats);
    }

    /**
     * Returns the deadline of the deadline task.
     *
     * @return deadline of the task,
     */
    public String getDeadline() {
        return deadline.format(inputFormats);
    }

    /**
     * Formats the task into a String ready to print.
     * @return a String of task in the correct format.
     */
    @Override
    public String taskAsString() {
        return "[D]" + getStatusIcon() + " " + getDescription() + " (by :" + getDeadline() +") " + getTag();
    }

}
