package pero.model;

import pero.exception.PeroException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Abstract base class that represents a generic task in task management system.
 * Subclasses ToDo, Deadline, Event extend this class.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    private static final String DATE_TIME_PATTERN = "yyyy-dd-MM HHmm";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);

    /**
     * Constructs a new Task with a description and completion status.
     *
     * @param description The description of the task.
     * @param isDone True if the task is initially marked as done, false otherwise.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Gets the description of this task.
     *
     * @return Task description.
     */
    public String getDescription() { // may not be used
        return this.description;
    }

    /**
     * Gets the status icon representing whether this task is done.
     *
     * @return "X" if task is done, " " (blank) if task is not done.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Marks this task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as undone.
     */
    public void markAsUndone() {
        this.isDone = false;
    }

    /**
     * Parses string input of time and date to LocalDatetime datetime object
     *
     * @param input String date time format from storage.
     * @return LocalDateTime
     * @throws PeroException If invalid date time format.
     */

    public static LocalDateTime parseDateTime(String input) throws PeroException {
        try {
            return LocalDateTime.parse(input, INPUT_FORMATTER);
        } catch (DateTimeException e) {
            throw new PeroException("Invalid datetime: '" + input + "'. Please follow format: YYYY-DD-MM HHmm");
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public abstract String toStorageString();

    public abstract LocalDate getDueDate();
}
