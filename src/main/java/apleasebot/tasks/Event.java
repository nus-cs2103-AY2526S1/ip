package apleasebot.tasks;

import java.time.LocalDateTime;

import apleasebot.ui.TimeFormatter;

/**
 * Encapsulates the event logic
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Constructor for the event class
     * @param name Name of event
     * @param todo Completion status of the task for storage and loading purposes
     * @param from Event start time
     * @param to Event end time
     */
    public Event(String name, boolean todo, LocalDateTime from, LocalDateTime to) {
        super(name, todo);
        this.startTime = from;
        this.endTime = to;
    }
    @Override
    public String toString() {
        return "[E] " + (isDone ? "[X] " : "[ ] ") + desc
                + " (from: " + TimeFormatter.getTime(this.startTime) + " to: " + TimeFormatter.getTime(this.endTime) + ")";
    }

    @Override
    public String translateTaskToText() {
        return "E," + this.checkDone() + "," + this.desc + "," + this.startTime + "," + this.endTime;
    }

    @Override
    public LocalDateTime getTime() {
        return endTime;
    }
}
