package pagrobot.tasks;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a general task in PagroBot.
 * A Task has a name, completion status, and optional time information.
 */
public abstract class Task {
    /** Formatter used for displaying dates. */
    protected DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
    private boolean isDone;
    private String name;


    /**
     * Constructs a new Task with the given name.
     * The task is initially marked as not done.
     *
     * @param name the description of the task.
     */
    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    /**
     * Constructs a new Task with the given name and completion status.
     *
     * @param name   the description of the task.
     * @param isDone whether the task is already completed.
     */
    public Task(String name, boolean isDone) {
        this(name);
        this.isDone = isDone;
    }

    /**
     * Returns the string representation of this Task.
     *
     * @return the string representation of the task.
     */
    @Override
    public String toString() {
        return isDone
                ? "[X] " + this.name
                : "[] " + this.name;
    }

    /**
     * Marks this Task as completed.
     *
     * @return this task, marked as done.
     */
    public Task mark() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks this Task as not completed.
     *
     * @return this task, marked as not done.
     */
    public Task unmark() {
        this.isDone = false;
        return this;
    }

    /**
     * Returns a string representation of this task for storage in memory.
     *
     * @return the serialized representation of this task.
     */
    public abstract String toMemory();

    /**
     * Reconstructs a Task from its saved memory representation.
     * By default, returns a ToDo task by default.
     *
     * @param input the serialized string.
     * @return the reconstructed task.
     */
    public static Task fromMemory(String input) {
        String[] parts = input.split("\\|");
        return new ToDo(parts[2], parts[1].equals("1"));
    }

    /**
     * Parses a date string into a LocalDateTime.
     * Accepts either ISO format or d/M/yyyy HHmm.
     *
     * @param dateString the string to parse.
     * @return the parsed LocalDateTime.
     * @throws DateTimeParseException if the string cannot be parsed.
     */
    public LocalDateTime parseDate(String dateString) throws DateTimeParseException {
        try {
            return LocalDateTime.parse(dateString);
        } catch (DateTimeException e) {
            DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
            return LocalDateTime.parse(dateString, inputFormatter);
        }
    }

    /**
     * Returns whether this task is marked as completed.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns the name (description) of this task.
     *
     * @return the name of the task.
     */
    public String getName() {
        return this.name;
    }
}
