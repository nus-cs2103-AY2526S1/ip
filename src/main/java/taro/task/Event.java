package taro.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs on a specific date and within a specific time period.
 * An {@code Event} is a type of {@link Task} that has a description,
 * a date, a start time, and an end time. It can also be marked as done
 * or not done like other tasks.
 * Example: {@code event project meeting /from 2025-09-01 14:00 /to 16:00}
 */
public class Event extends Task {
    private LocalDate date;
    private LocalTime from;
    private LocalTime to;

    /**
     * Constructs an {@code Event} task with the given description, date, time period, and completion status.
     *
     * @param description text description of the event task
     * @param date the date on which the event occurs
     * @param from the starting time of the event
     * @param to the ending time of the event
     * @param isCurrentTaskDone {@code true} if the task is already marked as done,
     *                          {@code false} otherwise
     */
    public Event(String description, LocalDate date, LocalTime from,
                 LocalTime to, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
        this.date = date;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmtDate = DateTimeFormatter.ofPattern("MMM dd yyyy");
        DateTimeFormatter fmtTime = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString()
                + " (from: " + date.format(fmtDate) + " " + from.format(fmtTime) + " to: "
                + to.format(fmtTime) + ")";
    }

    @Override
    public String toFile() {
        return "E " + super.toFile() + " | " + date + " | " + from + "-" + to;
    }

    /**
     * This method is used to extract due date
     * @return due date only of an event.
     */
    @Override
    public LocalDate getDueDate() {
        return this.date;
    }
}
