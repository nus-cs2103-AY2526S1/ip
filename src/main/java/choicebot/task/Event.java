package choicebot.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import choicebot.ChoiceBotException;

/**
 * Represents an Event task.
 * <p>
 * An Event task requires a description, a start date/time, and an end date/time
 * </p>
 */
public class Event extends Task {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    protected LocalDateTime startDate;
    protected LocalDateTime endDate;

    /**
     * Constructs a new Event task with the given description, completion status, start and end date/time.
     *
     * @param description Description of Event task.
     * @param isDone Whether the task is marked as completed.
     * @param startDate Start date/time.
     * @param endDate End date/time.
     * @throws ChoiceBotException If description is null or blank, or if from or to are null.
     */
    public Event(String description, Boolean isDone,
                 LocalDateTime startDate, LocalDateTime endDate) throws ChoiceBotException {
        super(description, isDone);
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = "E";
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException(
                    "You must add a description for choicebot.task.Event tasks. Please try again.");
        }
        if (startDate == null) {
            throw new ChoiceBotException(
                    "You must add a valid start date/time for choicebot.task.Event tasks. Please try again.");
        }
        if (endDate == null) {
            throw new ChoiceBotException(
                    "You must add a valid end date/time for choicebot.task.Event tasks. Please try again.");
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Prepends the string with E to indiciate it is an Event task.
     * Appends start and end date/time to the String description.
     * </p>
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startDate.format(DATE_FORMAT)
                + " to: " + this.endDate.format(DATE_FORMAT) + ")";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends start and end date/time to the saved String format.
     * </p>
     */
    @Override
    public String saveTask() {
        assert this.type != null : "Task type must be specified";
        assert this.description != null : "Task description must be specified";
        assert this.startDate != null || this.endDate != null : "From/To date must be specified";
        return String.format("%s | %d | %s | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description,
                this.startDate,
                this.endDate);
    }

    /**
     * Returns the start date for an Event task.
     */
    public LocalDateTime getStartDate() {
        return this.startDate;
    }
}
