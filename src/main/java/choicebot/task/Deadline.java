package choicebot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import choicebot.ChoiceBotException;

/**
 * Represents a Deadline task.
 * <p>
 * A Deadline task requires a description and a due date.
 * </p>
 */
public class Deadline extends Task {
    protected LocalDate dueDate;

    /**
     * Constructs a new Deadline task with the given description, completion status and due date.
     * @throws ChoiceBotException If description is null or blank, or if dueDate is null.
     */
    public Deadline(String description, Boolean isDone, LocalDate dueDate) throws ChoiceBotException {
        super(description, isDone);
        this.dueDate = dueDate;
        this.type = "D";
        if (description == null || description.isBlank()) {
            throw new ChoiceBotException(
                    "You must add a description for choicebot.task.Deadline tasks. Please try again.");
        }
        if (dueDate == null) {
            throw new ChoiceBotException(
                    "You must add a due date for choicebot.task.Deadline tasks. Please try again.");
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Prepends the string with D to indicate it is a Deadline task.
     * Appends due date to the end of the string.
     * </p>
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + convertDate(dueDate) + ")";
    }

    /**
     * {@inheritDoc}
     * <p>
     * Appends due date to the end of the saved string format.
     * </p>
     */
    @Override
    public String saveTask() {
        assert this.type != null : "Task type must be specified";
        assert this.description != null : "Task description must be specified";
        assert this.dueDate != null : "Due date must be specified";
        return String.format("%s | %d | %s | %s",
                this.getType(),
                this.isDone ? 1 : 0,
                this.description,
                this.dueDate);
    }

    /**
     * Converts the input date from YYYY-MM-DD format to MMM DD YYYY format.
     */
    public String convertDate(LocalDate date) {
        return date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    /**
     * Returns the due date for deadline task.
     */
    public LocalDate getDueDate() {
        return this.dueDate;
    }
}
