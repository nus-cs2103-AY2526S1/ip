package task;

import java.time.chrono.ChronoLocalDate;

/**
 * Represents a task with a name and a completion status.
 * This class serves as the base class for tasks.
 */
public abstract class Task {
    private String name;
    private boolean isCompleted;

    /**
     * Constructs a new task with the specified name. The task is initially marked as incomplete.
     *
     * @param name the name of the task
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Constructs a new task with the specified name and completion status.
     *
     * @param name the name of the task
     * @param isCompleted the initial completion status of the task
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Unmarks the task, marking it as incomplete.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    /**
     * Retrieves the name of the task.
     *
     * @return the name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * Retrieves the completion status of the task.
     *
     * @return <code>true</code> if the task is completed, <code>false</code> otherwise
     */
    public boolean getIsCompleted() {
        return this.isCompleted;
    }

    /**
     * Determines if the task is upcoming based on the given date. False as todo have no date.
     * This is overwritten by subclasses that have dates.
     *
     * @param today the current date to compare against
     * @return <code>true</code> if the task is upcoming, <code>false</code> otherwise
     */
    public boolean isUpcoming(ChronoLocalDate today) {
        return false;
    }

    public String save() {
        assert(name != null);
        return this.name.length()
                + "#"
                + this.name
                + "#"
                + this.isCompleted
                + "#";
    }

    @Override
    public String toString() {
        String result = "";
        if (this.isCompleted) {
            result += "[x]";
        } else {
            result += "[ ]";
        }
        return result + " " + this.name;
    }

}
