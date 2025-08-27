package zell.task;

import zell.util.DateOrTime;
import zell.exception.ZellException;

public class Event extends Task{
    /** A DateOrTime which stores when this event starts */
    private final DateOrTime start;
    /** A DateOrTime which stores when this event ends */
    private final DateOrTime end;

    public Event(String name, String start, String end) throws ZellException {
        super(name);
        this.start = new DateOrTime(start);
        this.end = new DateOrTime(end);
    }

    /**
     * Overloads the constructor which we primarily use for creating an Event task from the local storage
     *
     * @param name The name of the Event task
     * @param start The string representation of the date or datetime of the starting
     * @param end The string representation of the date or datetime of the ending
     * @param isDone Indicates if a Event task is done
     */
    public Event(String name, String start, String end, boolean isDone) throws ZellException {
        this(name, start, end);
        setDone(isDone);
    }

    /**
     * Converts an Event task to a string to be stored for local storage.
     *
     * @return The string representation of the Event task to be stored.
     */
    @Override
    public String taskToString() {
        return String.format("%s | %b | %s | %s | %s", "E", getDone(), getName(),
                this.start.originalFormat(), this.end.originalFormat());
    }

    /**
     * Overrides the parent's (Task) toString
     *
     * @return The toString of the Event task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s (from: %s to: %s)", "E", super.toString(),
                this.start.toString(), this.end.toString());
    }
}

