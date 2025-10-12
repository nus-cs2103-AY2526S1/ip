package com.oadultradeepfield.smartotter.task;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.oadultradeepfield.smartotter.util.DateParser;

/**
 * A {@link Task} representing an event with a start and end date.
 */
public class EventTask extends Task {
    private final LocalDateTime startDateTime;
    private final LocalDateTime endDateTime;

    /**
     * Creates an event task with the given name, start date, and end date.
     *
     * @param taskName      the name of the event
     * @param startDateTime the start date of the event
     * @param endDateTime   the end date of the event
     */
    public EventTask(String taskName, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        super(taskName);
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getIcon() {
        return "[E] %s".formatted(super.getIcon());
    }

    /**
     * Returns the string representation of this event, including its date range.
     */
    @Override
    public String toString() {
        return "%s (from: %s, to: %s)".formatted(super.toString(), DateParser.format(startDateTime),
            DateParser.format(endDateTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String convertToLine() {
        return "E | %s | %s | %s".formatted(super.convertToLine(), DateParser.formatForLine(startDateTime),
            DateParser.formatForLine(endDateTime));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isToday() {
        LocalDate now = LocalDate.now();
        LocalDate startDate = startDateTime.toLocalDate();
        LocalDate endDate = endDateTime.toLocalDate();

        boolean isAfterOrEqualStart = now.isAfter(startDate) || now.isEqual(startDate);
        boolean isBeforeOrEqualEnd = now.isBefore(endDate) || now.isEqual(endDate);

        return isAfterOrEqualStart && isBeforeOrEqualEnd;
    }
}
