package john.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Base type for all tasks.
 * <p>
 * Responsibilities:
 * - Hold the task title and completion flag.
 * - Provide common string rendering and serialisation helpers for subclasses.
 * <p>
 * String representation:
 * - toString() renders as: "[X] title" when completed, or "[ ] title" when not.
 * <p>
 * Serialization support:
 * - baseSerialize(type, extras...) builds a pipe-delimited record:
 * type | done(0|1) | title | extra1 | extra2 ...
 * Subclasses should call this from their serialise() implementation.
 * <p>
 * Date-time formatting:
 * - formatTime(LocalDateTime) uses the pattern "dd/MM/yyyy HH:mm:ss".
 * Keep this consistent with the parser's expected format.
 */
public abstract class Task {
    private boolean completed = false;
    private final String title;

    /**
     * Creates a task with the given title.
     *
     * @param title task description; must not be null
     */
    public Task(String title) {
        assert title != null : "title must not be null";
        this.title = title;
    }

    /**
     * Marks this task as complete.
     */
    public void markAsComplete() {
        this.completed = true;
    }

    /**
     * Marks this task as incomplete.
     */
    public void markAsIncomplete() {
        this.completed = false;
    }

    /**
     * Returns a human-readable string in the form "[X] title" or "[ ] title".
     */
    @Override
    public String toString() {
        return String.format("[" + this.convert() + "] " + title);
    }

    /**
     * Returns whether this task is completed.
     *
     * @return true if completed; false otherwise
     */
    public boolean isCompleted() {
        return this.completed;
    }

    /**
     * Serialises this task to a single line suitable for persistent storage.
     * Subclasses provide the concrete encoding using baseSerialize(...).
     *
     * @return the serialised representation
     */
    public abstract String serialise();

    /**
     * Converts the completion flag to its single-character marker.
     * "X" when completed, space when not.
     */
    private String convert() {
        return completed ? "X" : " ";
    }

    /**
     * Helper for subclasses to build a pipe-delimited record.
     * Format: type | done(0|1) | title | extras...
     *
     * @param type   single-letter task type tag (for example, T, D, E)
     * @param extras additional fields to append
     * @return the assembled record
     */
    protected String baseSerialize(String type, String... extras) {
        assert type != null && !type.isBlank() : "task type tag must be non-blank";
        StringBuilder sb = new StringBuilder();
        sb.append(type)
                .append(" | ")
                .append(completed ? "1" : "0")
                .append(" | ")
                .append(title);

        for (String extra : extras) {
            sb.append(" | ").append(extra == null ? "" : extra);
        }
        return sb.toString();
    }

    /**
     * Formats a LocalDateTime using pattern "dd/MM/yyyy HH:mm:ss".
     *
     * @param ldt the date-time to format
     * @return the formatted string
     */
    protected String formatTime(LocalDateTime ldt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return ldt.format(formatter);
    }

    public String getTitle() {
        return this.title;
    }
}