package tweety.tasks;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import java.time.LocalDate;

import tweety.exceptions.TweetyException;

/**
 * Represents a deadline task with a specific due date.
 * Extends the Task class to include deadline functionality.
 */
public class Deadline extends Task {
    protected LocalDate deadline;

    /**
     * Creates a new deadline task with the specified description and deadline.
     *
     * @param description the task description
     * @param deadline the deadline for completing the task
     */
    public Deadline(String description, String deadline) throws TweetyException{
        super(description);
        try {
            this.deadline = LocalDate.parse(deadline);
        } catch (DateTimeParseException e) {
            throw new TweetyException("Oopsie, hon! Invalid date format: " + deadline +
                    ". Aww, Please use yyyy-mm-dd format (e.g. 2025-9-30)");
        }
    }

    @Override
    public void editField(String field, String newValue) throws TweetyException {
        switch (field) {
            case "description":
                setDescription(newValue);
                break;
            case "deadline":
                setDeadline(newValue);
                break;
            default:
                throw new TweetyException("Aww, hon! For deadline tasks, ya can only edit " +
                        "the description or the deadline!");
        }
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(String by) {
        this.deadline = LocalDate.parse(by);
    }

   @Override
    public String toString() {
        return "[D]" + getStatusIcon() + " " + getDescription() + " (by: "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";
    }
}
