package com.oadultradeepfield.smartotter.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.oadultradeepfield.smartotter.util.DateParser;

/**
 * A {@link Task} with a deadline.
 */
public class DeadlineTask extends Task {
    private final LocalDateTime deadline;

    /**
     * Creates a deadline task with the given name and deadline.
     *
     * @param taskName the name of the task
     * @param deadline the deadline description or date
     */
    public DeadlineTask(String taskName, LocalDateTime deadline) {
        super(taskName);
        this.deadline = deadline;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIcon() {
        return "[D] %s".formatted(super.getIcon());
    }

    /**
     * Returns the string representation of this task including its deadline.
     */
    @Override
    public String toString() {
        return "%s (by: %s)".formatted(super.toString(), DateParser.format(deadline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToLine() {
        return "D | %s | %s".formatted(super.convertToLine(), DateParser.formatForLine(deadline));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isToday() {
        LocalDate now = LocalDate.now();
        return now.isEqual(deadline.toLocalDate());
    }
}
