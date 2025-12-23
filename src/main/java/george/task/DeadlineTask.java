package george.task;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import george.exceptions.GeorgeException;
import george.utils.DateTimeParser;

/**
 * Represents a task with a specific deadline.
 * Extends the base Task class with deadline-specific functionality.
 */
public class DeadlineTask extends Task {
    private LocalDateTime deadline;

    /**
     * Constructs a DeadlineTask with description and deadline string.
     *
     * @param description The description of the deadline task
     * @param deadline The deadline time string to be parsed
     * @throws GeorgeException If the description is invalid or deadline time cannot be parsed
     */
    public DeadlineTask(String description, String deadline) throws GeorgeException {
        this(description, deadline, false);
    }

    /**
     * Constructs a DeadlineTask with description, deadline string, and completion status.
     *
     * @param description The description of the deadline task
     * @param deadline The deadline time string to be parsed
     * @param isDone The completion status of the task
     * @throws GeorgeException If the description is invalid or deadline time cannot be parsed
     */
    public DeadlineTask(String description, String deadline, boolean isDone) throws GeorgeException {
        super(description);

        assert deadline != null : "Deadline string cannot be null";
        assert !deadline.trim().isEmpty() : "Deadline string cannot be empty";

        this.deadline = DateTimeParser.parseDateTime(deadline);
        this.isDone = isDone;

        assert this.deadline != null : "Deadline should be properly parsed and set";
        assert this.deadline.isAfter(LocalDateTime.MIN) : "Deadline should be a valid date";
    }

    /**
     * Constructs a DeadlineTask with description, deadline as LocalDateTime object, and completion status.
     * This constructor is used when the deadline datetime is already parsed and available.
     *
     * @param description The description of the deadline task
     * @param deadline The deadline time as a LocalDateTime object
     * @param isDone The completion status of the task
     */
    public DeadlineTask(String description, LocalDateTime deadline, boolean isDone) throws GeorgeException {
        super(description);

        assert deadline != null : "Deadline LocalDateTime cannot be null";
        assert deadline.isAfter(LocalDateTime.MIN) : "Deadline should be a valid date";

        this.deadline = deadline;
        this.isDone = isDone;
    }

    @Override
    public String getType() {
        return "[D]";
    }

    /**
     * Returns the formatted deadline string.
     *
     * @return The deadline formatted as "MMM dd yyyy" (e.g., "Jan 15 2024")
     */
    public String getDeadline() {
        assert deadline != null : "Deadline should not be null when calling getDeadline()";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");
        String formattedDeadline = deadline.format(formatter);

        assert formattedDeadline != null : "Formatted deadline cannot be null";
        assert formattedDeadline.length() >= 11
                : "Formatted deadline should be at least 11 characters (e.g., 'Jan 15 2024')";
        assert formattedDeadline.matches("[A-Za-z]{3} \\d{1,2} \\d{4}")
                : "Formatted deadline should match pattern 'MMM dd yyyy'";

        return formattedDeadline;
    }

    @Override
    public String getDisplayText() {
        assert deadline != null : "Deadline should not be null when generating display text";

        String displayText = this.getType() + this.getStatus()
                + " " + this.getDescription() + " (by: " + this.getDeadline() + ")";

        assert displayText != null : "Display text cannot be null";
        assert displayText.contains("[D]") : "Display text should contain task type";
        assert displayText.contains(this.getDescription()) : "Display text should contain description";
        assert displayText.contains("(by:") : "Display text should contain deadline indicator";
        assert displayText.contains(this.getDeadline()) : "Display text should contain formatted deadline";

        return displayText;
    }

    @Override
    public String toString() {
        return getType().charAt(1) + " | " + (isDone() ? 1 : 0) + " | " + getDescription() + " | " + getDeadline();
    }
}
