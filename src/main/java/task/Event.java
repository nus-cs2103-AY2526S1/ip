package task;

import java.time.LocalDateTime;

import sunday.DateTimeHelper;

/**
 * A task that spans from a start to an end datetime.
 */
public class Event extends Task {
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Event(String taskName, LocalDateTime startDate, LocalDateTime endDate, boolean done) {
        super(taskName, done);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Event(String taskName, String startDate, String endDate, boolean done) {
        super(taskName, done);
        this.startDate = DateTimeHelper.parseDateTime(startDate);
        this.endDate = DateTimeHelper.parseDateTime(endDate);
    }

    @Override
    public LocalDateTime getStartDate() {
        return startDate;
    }

    @Override
    public LocalDateTime getEndDate() {
        return endDate;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(from: "
                + this.startDate.format(DateTimeHelper.DATE_TIME_PRINT)
                + " to: "
                + this.endDate.format(DateTimeHelper.DATE_TIME_PRINT)
                + ")";
    }

    @Override
    public String convertor() {
        return "E | "
                + (isDone() ? 1 : 0)
                + " | "
                + getTaskName()
                + " | "
                + this.startDate.format(DateTimeHelper.DATE_TIME_SAVE)
                + " | "
                + this.endDate.format(DateTimeHelper.DATE_TIME_SAVE);
    }
}
