package geegar.task;

import java.time.LocalDateTime;

/**
 * Deadline type that contains description and a by deadline date&time
 */
public class Deadline extends Task {
    protected LocalDateTime by;

    /**
     * creates a deadline instance based on only the description and by date/time
     *
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by) {
        super(description);
        this.by = by;
    }

    /**
     * creates a deadline instance based on the description and by date/time and if the task is completed
     *
     * @param description
     * @param by
     */
    public Deadline(String description, LocalDateTime by, Boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Returns the "by" deadline timing
     * @return
     */
    public LocalDateTime getBy() {
        return by;
    }

    @Override
    public String toSaveString() {
        return "[D]" + super.toString()
                + " (by: " + by.format(Task.SAVE_FORMATTER) + ")";
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(Task.DISPLAY_FORMATTER) + ")";
    }
}
