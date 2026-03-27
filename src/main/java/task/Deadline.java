package task;

import java.time.LocalDate;

import sunday.DateTimeHelper;

/**
 * A task with a deadline date.
 */
public class Deadline extends Task {
    private final LocalDate deadLine;

    public Deadline(String taskName, LocalDate deadLine, boolean done) {
        super(taskName, done);
        this.deadLine = deadLine;
    }

    public Deadline(String taskName, String deadLine, boolean done) {
        super(taskName, done);
        this.deadLine = DateTimeHelper.parseDate(deadLine);
    }

    public LocalDate getDeadLine() {
        return deadLine;
    }

    @Override
    public String getTaskType() {
        return "D";
    }

    @Override
    public String toString() {
        return "[D]"
                + super.toString()
                + "(by "
                + this.deadLine.format(DateTimeHelper.DATE_PRINT)
                + ")";
    }

    @Override
    public String convertor() {
        return "D | "
                + (isDone() ? 1 : 0)
                + " | "
                + getTaskName()
                + " | "
                + this.deadLine.format(DateTimeHelper.DATE_SAVE);
    }
}
