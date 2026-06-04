package sora.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * Represents a {@code After} task that must be completed after a specific date and time.
 */
public class After extends Task {
    protected LocalDateTime required;

    /**
     * Constructs a {@code After} task with the specified description and required time.
     *
     * @param description the description of the task.
     * @param required the date and time that we need to do the task.
     */
    public After(String description, LocalDateTime required) {
        super(Task.TaskType.AFTER, description);
        assert required != null : "The date/time should not be null";
        this.required = required;
    }

    /**
     * Formats the required time into a different string.
     *
     * @return the formatted required time in the pattern "MMM dd yyyy HHmm".
     */
    public String requiredToFormat() {
        assert required != null : "The 'required' field must not be null before formatting";
        return required.format(DateTimeFormatter.ofPattern("MMM dd yyyy HHmm", Locale.ENGLISH));
    }

    /**
     * Returns a string representation of the {@code After} task,
     * including its description, status, and required time.
     *
     * @return the formatted string representing the after task.
     */
    @Override
    public String toString() {
        return super.toString() + " (required: " + this.requiredToFormat() + ")";
    }
}
