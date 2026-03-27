package marvin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A Deadline object that contains a task description with an additional due date.
 */
public class Deadline extends Task {
    private final LocalDateTime dueTime;

    /**
     * Instantiates a deadline object.
     *
     * @param description The main description of the deadline.
     * @param due         The LocalDateTime object representing the due date of the task.
     */
    public Deadline(String description, LocalDateTime due) {
        super(description);
        this.dueTime = due;
    }

    @Override
    public String toString() {
        // Construct string based on description and status
        return String.format("[D]%s %s (by: %s)", super.toString(), this.getDescription(),
                this.dueTime.format(DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy, ha"
                ))
        );
    }
}
