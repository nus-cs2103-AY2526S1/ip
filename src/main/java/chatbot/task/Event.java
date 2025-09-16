package chatbot.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents an event task that occurs over a period of time.
 * An {@code Event} has a description, a start date, an end date,
 * and a completion status.
 */
public class Event extends Task {

    /**
     * The start date of the event.
     */
    private LocalDate start;

    /**
     * The end date of the event.
     */
    private LocalDate end;

    /**
     * Formatter used to display event dates in {@code MMM dd yyyy} format.
     */
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Creates a new {@code Event} task with the given description,
     * start date, and end date. The task is initialized as not done.
     *
     * @param description Description of the event
     * @param start Start date in ISO-8601 format (yyyy-MM-dd)
     * @param end End date in ISO-8601 format (yyyy-MM-dd)
     */
    public Event(String description, String start, String end) {
        this.description = description;
        this.isDone = false;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Creates a new {@code Event} task with the given completion status,
     * description, start date, and end date.
     *
     * @param isDone {@code true} if the task is completed, {@code false} otherwise
     * @param description Description of the event
     * @param start Start date in ISO-8601 format (yyyy-MM-dd)
     * @param end End date in ISO-8601 format (yyyy-MM-dd)
     */
    public Event(boolean isDone, String description, String start, String end) {
        this.description = description;
        this.isDone = isDone;
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    @Override
    public void snooze() {
        end = end.plusDays(1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + "(from: " + start.format(formatter) + " to: " + end.format(formatter) + ")";
    }

    @Override
    public String toFile() {
        return "chatbot.task.ToDo||" + (isDone ? "X" : "") + "||" + description + "||" + start + "||" + end;
    }
    public LocalDate getStart() {
        return start;
    }
    public LocalDate getEnd() {
        return end;
    }
}
