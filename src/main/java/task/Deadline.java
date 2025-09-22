package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline.
 * It has a description (inherited from {@link Task}) and a deadline
 * stored as a {@link LocalDate}.
 */
public class Deadline extends Task {

    public final LocalDate deadline;

    /**
     * Constructs a {@link Deadline} task with the given description and deadline as strings.
     * The deadline is parsed into a {@link LocalDate} object.
     *
     * @param description the description/title of the {@link Deadline} task
     * @param deadline the deadline of the {@link Deadline} task, stored as a {@link LocalDate}
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = LocalDate.parse(deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline.format(DateTimeFormatter.ofPattern("dd MMM yyyy")) + ")";
    }
}

