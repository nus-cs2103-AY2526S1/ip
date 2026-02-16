package mambo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import mambo.parser.DateTimeParser;

/**
 * Represents a deadline task that has a set deadline
 *
 * @author kentalim2
 */
public class DeadlineTask extends Task {
    private String deadline;
    private LocalDateTime deadlineDateTime;

    /**
     * Creates a new instance of the deadline task.
     * If deadline passed through into input follows a recognized date time format, represent the
     * deadline as a LocalDateTime, else represent it as a String
     *
     * @param description Description of task
     * @param isDone Whether the task is done or not
     * @param deadline Deadline of task in input
     */
    public DeadlineTask(String description, boolean isDone, String deadline) {
        super(description, isDone);
        this.deadline = deadline;
        try {
            deadlineDateTime = DateTimeParser.parseDateTime(deadline);

        } catch (DateTimeParseException e) {
            deadlineDateTime = null;
        }

    }

    /**
     * If deadline is represented as a LocalDateTime, return string representation of the date and time.
     * Else return deadline.
     *
     * @return Deadline of task
     */
    public String getDeadline() {
        if (this.deadlineDateTime != null) {
            return DateTimeParser.formatDateTime(deadlineDateTime);
        } else {
            return this.deadline;
        }
    }

    @Override
    public String convertToFileFormat() {
        return String.format("D / %s / %s / %s",
                this.isMarked(), this.getDescription(), getDeadline());
    }

    @Override public String toString() {
        return String.format("(D) %s (by: %s)", super.toString(), getDeadline());
    }
}
