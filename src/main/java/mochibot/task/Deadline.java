package mochibot.task;

import java.time.LocalDateTime;

import mochibot.util.DateTimeParser;

/**
 * This class represents a {@code Deadline} task with a specified datetime.
 */
public class Deadline extends Task {
    private final LocalDateTime deadlineDate;

    /**
     * Constructs a new {@code Deadline} task with a description and a specified deadline datetime.
     *
     * @param description The description of the task.
     * @param deadlineDate The datetime that the task should be completed by.
     */
    public Deadline(String description, LocalDateTime deadlineDate) {
        super(description);
        assert deadlineDate != null : "deadlineDate must not be null";
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructs a new {@code Deadline} task with a description, a specified deadline datetime,
     * and a completion status.
     *
     * @param description The description of the task.
     * @param isDone The completion status of the task.
     * @param deadlineDate The datetime that the task should be completed by.
     */
    public Deadline(String description, boolean isDone, LocalDateTime deadlineDate) {
        super(description, isDone);
        assert deadlineDate != null : "deadlineDate must not be null";
        this.deadlineDate = deadlineDate;
    }

    public String getType() {
        return "[D]";
    }

    public LocalDateTime getDeadlineDate() {
        return this.deadlineDate;
    }

    @Override
    public String toString() {
        return this.getType() + super.toString() + " "
                + DateTimeParser.formatDateTimeDisplay(this.deadlineDate);
    }
}
