package shef.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents deadline tasks.
 */
public class DeadlineTask extends Task {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("MMM d yyyy");

    private LocalDate deadline;

    public DeadlineTask(String description, String deadline) {
        this(description, false, deadline);
    }

    /**
     * Returns DeadlineTask object.
     * @param description task description.
     * @param isDone denotes whether the task is done.
     * @param deadline deadline of the task.
     * @throws DateTimeParseException error with DateTime format.
     */
    public DeadlineTask(String description, boolean isDone, String deadline) throws DateTimeParseException {
        super(description, isDone);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toCsvString() {
        return String.format("D,%s,%s", super.toCsvString(), deadline);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)",
                super.toString(),
                deadline.format(format));
    }
}
