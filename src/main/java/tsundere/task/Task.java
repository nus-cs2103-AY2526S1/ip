package tsundere.task;

import java.util.Objects;


/**
 * Represents an abstract task with a name and completion status.
 */
public abstract class Task {
    private final String name;
    private boolean done = false;

    /**
     * Constructs a new Task with the specified name.
     * The task is initially marked as not done.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
    }

    /**
     * Checks if the current task has been completed.
     * @return True if the task is completed, else false.
     */
    public boolean isDone() {
        return done;
    }

    /**
     * Marks the current task as completed.
     */
    public void markDone() throws TsundereAlreadyMarkedException {
        if (done) {
            throw new TsundereAlreadyMarkedException(this);
        }
        done = true;
    }

    /**
     * Marks the current task as not completed.
     */
    public void markUndone() throws TsundereAlreadyUnmarkedException {
        if (!done) {
            throw new TsundereAlreadyUnmarkedException(this);
        }
        done = false;
    }

    public abstract String toStorageString();

    /**
     * Returns the name of the task.
     * @return name of the task.
     */
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task task) {
            return this.name.equals(task.name) && this.done == task.done;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.done);
    }
}

