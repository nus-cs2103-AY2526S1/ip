package v.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import v.enums.TaskType;

/**
 * A task that starts at a specific time and ends at a specific time.
 * A performance in the grand play of your schedule.
 */
public class Event extends Task {
    private static final DateTimeFormatter DISPLAY_FORMATTER = DateTimeFormatter.ofPattern("MMM d yyyy");
    protected final LocalDate from;
    protected final LocalDate to;

    /**
     * Creates a new Event task with description, start and end dates.
     *
     * @param description The nature of this engagement.
     * @param from When this event begins.
     * @param to When this event concludes.
     */
    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the type of this task.
     *
     * @return The TaskType.EVENT enum value.
     */
    @Override
    public TaskType getTaskType() {
        return TaskType.EVENT;
    }

    /**
     * Returns a string representation of this event task.
     *
     * @return Formatted string with event timing details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DISPLAY_FORMATTER)
                + " to: " + to.format(DISPLAY_FORMATTER) + ")";
    }

    /**
     * Returns the serialized representation of this event for persistence.
     * Format: {@code E | <done:1|0> | <description> | <from> | <to>}.
     *
     * @return A save-friendly string for this event.
     */
    @Override
    public String toSaveString() {
        return "E | " + (isDone() ? "1" : "0") + " | " + getDescription() + " | " + from + " | " + to;
    }
}
