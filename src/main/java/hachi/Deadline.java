package hachi;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a deadline task that has a due date. This class is a subclass of Task
 * and includes an additional field to represent the time when the task is due. The task
 * will be marked as done or undone, and it will include a description and a due date.
 */
public class Deadline extends Task {
    LocalDateTime by;

    /**
     * Constructs a new Deadline object with the specified description and due date.
     *
     * @param description the description of the task
     * @param by          the due date and time of the task
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * Constructs a new Deadline object with the specified description, completion status,
     * and due date.
     *
     * @param description the description of the task
     * @param status      the completion status of the task
     * @param by          the due date and time of the task
     */
    public Deadline(String description, boolean status, LocalDateTime by) {
        super(description, status);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline task. The string includes the task's
     * description, completion status, and the formatted due date.
     *
     * @return a string representing the deadline task
     */
    @Override
    public String toString() {
        String formatted = new DateFormatter().formatTime(by);
        return "[D]" + super.toString() + String.format(" | By: %s", formatted);
    }
}
