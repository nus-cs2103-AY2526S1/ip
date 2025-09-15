package bobby.task;

import java.time.LocalDateTime;

import bobby.exception.BobbyException;

/**
 * subclass of Task
 */
public class Event extends Task {
    protected LocalDateTime from;
    protected LocalDateTime to;

    /**
     * initialises an Event object
     * @param description
     * @param isMark
     * @param from
     * @param to
     * @throws BobbyException
     */
    public Event(String description, boolean isMark, String from, String to) throws BobbyException {
        super(description, isMark);
        this.from = parseString(from);
        this.to = parseString(to);
    }

    /**
     * used to categorise tasks
     * @return task type
     */
    @Override
    public int getTaskType() {
        return 2;
    }

    /**
     * converting the task to a String friendly format
     * @return String that is saved in storage
     */
    @Override
    public String toStorage() {
        return super.toStorage() + " /from " + datetimeToStorage(from) + " /to " + datetimeToStorage(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + datetimeToString(from) + " to: " + datetimeToString(to) + ")";
    }
}
