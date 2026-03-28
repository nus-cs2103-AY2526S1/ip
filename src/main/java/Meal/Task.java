package Meal;

public class Task {
    private String taskName;
    private boolean marked;

    /**
     * Initialises a task
     * @param taskName description of task
     */
    public Task(String taskName) {
        this.taskName = taskName;
        this.marked = false;
    }

    /**
     * Returns the name of the task
     * @return name of the task
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Marks a task as done
     * @return a confirmation message saying task is marked
     */
    public String mark() {
        this.marked = true;
        return ("Nice! I've marked this task as done:\n"
                + this.toString());
    }

    /**
     * Marks task as done without returning confirmation message
     */
    public void silentMark() {
        this.marked = true;
    }

    /**
     * Marks a task as not done
     * @return a confirmation message saying the task is unmarked
     */
    public String unmark() {
        this.marked = false;
        return ("OK, I've marked this task as not done yet:\n"
                + this.toString());
    }

    /**
     * Returns whether the task is marked or not
     * @return true if task is marked, false otherwise
     */
    public boolean isMarked() {
        return this.marked;
    }

    /**
     * Returns a string representation of the task
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return (marked ? "[X] " : "[ ] ") + this.taskName;
    }
}
