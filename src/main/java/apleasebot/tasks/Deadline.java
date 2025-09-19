package apleasebot.tasks;

import java.time.LocalDateTime;

import apleasebot.ui.TimeFormatter;

/**
 * Encapsulates Deadline task logic
 */
public class Deadline extends Task {

    private final LocalDateTime completeBy;

    /**
     * Constructor for the deadline class
     * @param name Name of the task
     * @param todo Completion status of the task for storage and loading purposes
     * @param by Time the deadline task needs to be completed by
     */
    public Deadline(String name, boolean todo, LocalDateTime by) {
        super(name, todo);
        this.completeBy = by;
    }

    @Override
    public String toString() {
        return "[D] " + (isDone ? "[X] " : "[ ] ") + desc + " (by: " + TimeFormatter.getTime(this.completeBy) + ")";
    }

    @Override
    public String translateTaskToText() {
        return "D," + this.checkDone() + "," + this.desc + "," + this.completeBy;
    }

    @Override
    public LocalDateTime getTime() {
        return completeBy;
    }
}
