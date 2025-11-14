package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import exception.BaymaxException;

/**
 * Represents an event-type task in the Baymax task management system.
 * <p>
 * An Event task has a description, a status (done or not done), and a start and end date
 * represented as {@link LocalDate}.
 * <p>
 * This class extends {@link Task} and provides functionality to
 * retrieve the start and end dates and display the task in a human-readable format.
 * <p>
 * The dates must be entered in the format "d/M/yyyy" (e.g., "2/12/2019").
 * If the dates are invalid or the start date is after the end date, a {@link BaymaxException} is thrown.
 */
public class Event extends Task {

    private final LocalDate from;
    private final LocalDate to;

    /**
     * Constructs an Event task with the given description, type, start date, and end date.
     *
     * @param taskName the description of the task
     * @param taskType the type of the task (should be TaskType.EVENT)
     * @param fromStr  the start date as a string in "d/M/yyyy" format
     * @param toStr    the end date as a string in "d/M/yyyy" format
     * @throws BaymaxException if the date format is invalid or start date is after end date
     */
    public Event(String taskName, TaskType taskType, String fromStr, String toStr) throws BaymaxException {
        super(taskName, taskType);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            this.from = LocalDate.parse(fromStr.split(" ")[0], formatter);
            this.to = LocalDate.parse(toStr.split(" ")[0], formatter);
        } catch (DateTimeParseException e) {
            throw new BaymaxException("I'm confused... Invalid date format! Please use dd/MM/yyyy, e.g., 2/12/2019");
        }

        if (this.from.isAfter(this.to)) {
            throw new BaymaxException("I'm confused... Start date cannot be after end date!");
        }
    }

    /**
     * Constructs an Event task with the given description, type, start date, end date, and completion status.
     *
     * @param taskName the description of the task
     * @param taskType the type of the task (should be TaskType.EVENT)
     * @param fromStr  the start date as a string in "d/M/yyyy" format
     * @param toStr    the end date as a string in "d/M/yyyy" format
     * @param isDone   the completion status of the task
     * @throws BaymaxException if the date format is invalid or start date is after end date
     */
    public Event(String taskName, TaskType taskType, String fromStr, String toStr, boolean isDone) throws BaymaxException {
        super(taskName, taskType, isDone);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy");

        try {
            this.from = LocalDate.parse(fromStr.split(" ")[0], formatter); // ignore time if present
            this.to = LocalDate.parse(toStr.split(" ")[0], formatter);     // ignore time if present
        } catch (DateTimeParseException e) {
            throw new BaymaxException("I'm confused... Invalid date format! Please use dd/MM/yyyy, e.g., 2/12/2019");
        }

        if (this.from.isAfter(this.to)) {
            throw new BaymaxException("I'm confused... Start date cannot be after end date!");
        }
    }

    /**
     * Returns a string representation of the Event task, including its type,
     * completion status, description, and formatted start and end dates.
     *
     * @return the string representation of the Event task
     */
    @Override
    public String toString() {
        String fromFormatted = from.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        String toFormatted = to.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        return "[E]" + super.toString() + " (from: " + fromFormatted + " to: " + toFormatted + ")";
    }


    /**
     * Returns the start date of the Event.
     *
     * @return the start date as a {@link LocalDate}
     */
    public LocalDate getFrom() {
        return this.from;
    }

    /**
     * Returns the end date of the Event.
     *
     * @return the end date as a {@link LocalDate}
     */
    public LocalDate getTo() {
        return this.to;
    }
}
