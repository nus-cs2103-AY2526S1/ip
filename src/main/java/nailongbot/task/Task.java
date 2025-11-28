package nailongbot.task;

import java.time.LocalDateTime;

/**
 * General task
 * Contains methods and variables applicable to all sub category of task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Creates a new Task with the specified description.
     * The task is initially not completed.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    public String getDesc() {
        return description;
    }

    public void doTask() {
        isDone = true;
    }

    public void undoTask() {
        isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String getDescription() {
        return description;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDesc();
    }

    public abstract LocalDateTime getDate();
}
