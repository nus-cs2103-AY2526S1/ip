package lenny.logic.task;

import lenny.logic.util.DateFormatter;


/**
 * Represents an event task that has a start and end time.
 */
public class Event extends Task {
    private String from;
    private String to;

    /**
     * Creates a new Event task.
     *
     * @param taskName Description of the event task.
     * @param fromRaw The start date/time of the event.
     * @param toRaw The end date/time of the event.
     * @param isDone True if the event task is already completed, otherwise false.
     */
    public Event(String taskName, String fromRaw, String toRaw , Boolean isDone) {
        super(taskName);
        this.from = DateFormatter.format(fromRaw);
        this.to = DateFormatter.format(toRaw);
        this.setIsDone(isDone);
    }

    public String getTaskType() {
        return "E";
    }

    /**
     * Returns the duration of the event as a formatted string.
     *
     * @return A string containing start and end times of the event.
     */
    public String getDuration() {
        return from + " - " + to;
    }

    @Override
    public String toString() {
        return "[E]" + (this.getIsDone() ? "[X] " : "[ ] ") + getTaskName() + " (from: " + from + " to: " + to + ")";
    }

}
