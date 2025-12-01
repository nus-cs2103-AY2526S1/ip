package tasks;

import time.Time;

public class Task{
    protected boolean isDone;
    private TaskInformation information;

    /**
     * Constructs a Task
     *
     * @param information TaskInformation object holding information of task
     */
    public Task(TaskInformation information) {
        this.information = information;
        this.isDone = false;
    }

    /**
     * Returns string of isDone
     * 'X' if true
     * empty space if false
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks isDone as true
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks isDone as false
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns the description
     */
    public String getDescription() {
        return this.information.getDescription();
    }

    /**
     * Returns the type
     */
    public String getType() {
        return this.information.getType();
    }

    /**
     * Returns the start time
     */
    public Time getStartTime() {
        return this.information.getStartTime();
    }

    /**
     * Returns the end time
     */
    public Time getEndTime() {
        return this.information.getEndTime();
    }

    /**
     * Updates the Information
     */
    public void setInformation(TaskInformation info) {
        this.information = info;
    }

    /**
     * Returns string of task
     * Used for printing task in storage
     */
    public String toSave() {
        return getStatusIcon() + " | " + getDescription();
    }

    /**
     * Returns string of the task
     * Used for printing out task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + getDescription();
    }
}
