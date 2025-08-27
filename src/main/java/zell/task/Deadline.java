package zell.task;

import zell.util.DateOrTime;
import zell.exception.ZellException;

/**
 * Represents a Deadline task for the Zell chatbot
 */
public class Deadline extends Task{
    /** A DateOrTime which stores when this deadline is due */
    private final DateOrTime dueBy;

    public Deadline(String name, String dueBy) throws ZellException {
        super(name);
        this.dueBy = new DateOrTime(dueBy);
    }

    /**
     * Overloads the constructor which we primarily use for creating a Deadline task from the local storage
     *
     * @param name The name of the Deadline task
     * @param dueBy The string representation of the date or datetime
     * @param isDone Indicates if a Deadline task is done
     */
    public Deadline(String name, String dueBy, boolean isDone) throws ZellException {
        this(name, dueBy);
        setDone(isDone);
    }

    /**
     * Converts a Deadline task to a string to be stored for local storage.
     *
     * @return The string representation of the Deadline task to be stored.
     */
    @Override
    public String taskToString() {
        return String.format("%s | %b | %s | %s", "D", getDone(), getName(), this.dueBy.originalFormat());
    }

    /**
     * Overrides the parent's (Task) toString
     *
     * @return The toString of the Deadline task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (by: %s)", "D", super.toString(), this.dueBy.toString());
    }
}

