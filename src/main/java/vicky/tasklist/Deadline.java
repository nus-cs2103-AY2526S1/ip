package vicky.tasklist;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task, extending the Todo class, with a specific date and time when the task is due.
 */
public class Deadline extends Todo {
    protected static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    protected static final DateTimeFormatter TIME_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    protected LocalDateTime by;

    /**
     * Constructor for Deadline class, initializes the deadline task with a name and due time.
     *
     * @param name The name of the task.
     * @param by The due date and time of the deadline task.
     */
    public Deadline(String name, LocalDateTime by) {
        super(name);
        this.by = by;
    }

    /**
     * Overloaded constructor for Deadline class, initializes the deadline task with a name and due time.
     *
     * @param name The name of the task.
     * @param by The due date and time of the deadline task.
     * @param isDone The completion status of the deadline task.
     */
    public Deadline(String name, LocalDateTime by, boolean isDone) {
        super(name, isDone);
        this.by = by;
    }

    /**
     * Returns a string representation of the deadline based on output format.
     *
     * @return string representation of the deadline.
     */
    public String deadlineDateTime() {
        return this.by.format(OUTPUT_FORMAT);
    }

    /**
     * Changes the deadline by to a new deadline.
     *
     * @param by New LocalDateTime deadline.
     */
    public void changeBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Returns a storage string of the deadline in the format:
     * "Deadline | {completion status} | {deadline name} | {end time}"
     *
     * @return A storage string describing the event with its name, completion status, and end time.
     */
    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Deadline | %d | %s | %s", done, this.name, this.deadlineDateTime());
    }

    /**
     * Returns a string representation of the deadline in the format:
     * "[D] [{completion status}] {deadline name} (by: {end time})"
     *
     * @return A string describing the deadline with its name, completion status, start time, and end time.
     */
    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[D] [%c] %s (by: %s)", p, this.name, deadlineDateTime());
    }

}
