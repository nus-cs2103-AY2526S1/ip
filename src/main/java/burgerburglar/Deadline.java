package burgerburglar;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline in BurgerBurglar.
 * <p>
 * Extends {@link Task} and adds a due date.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    protected LocalDateTime by;

    /**
     * Constructs a Deadline task with a description and deadline.
     *
     * @param description the task description
     * @param by          the deadline date and time
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        assert !description.isBlank() : "Deadline description cannot be blank";
        this.by = by;
    }

    /**
     * Constructs a Deadline task with a description, deadline, and completion status.
     *
     * @param description the task description
     * @param by          the deadline date and time
     * @param isDone      whether the task is completed
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        this(description, by);
        this.isDone = isDone;
    }

    /** Returns the type icon for deadline tasks. */
    @Override
    public String getTypeIcon() {
        return "[D]";
    }

    /** Returns a human-readable string of the deadline task. */
    @Override
    public String toString() {
        String byDisplay = (by != null) ? by.format(OUTPUT_FORMAT) : "unspecified";
        return getTypeIcon() + getStatusIcon() + " " + description + " (by: " + byDisplay + ")";
    }

    /** Serializes the task to a string suitable for storage. */
    @Override
    public String serialize() {
        String byString = (by != null) ? by.toString() : "";
        return String.format("D | %s | %s | %s", isDone ? "1" : "0", description, byString);
    }
}
