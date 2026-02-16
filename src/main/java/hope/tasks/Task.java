package hope.tasks;

import hope.customexceptions.RedundantStateChangeException;

/**
 * An abstract class representing a generic task with a description, completion status, and type.
 * This class serves as the base for specific task types such as ToDoTask, DeadlineTask, and EventTask.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * An enumeration representing the possible types of tasks.
     * T represents a to-do task, D represents a deadline task, and E represents an event task.
     */
    protected enum TaskType {
        T,
        D,
        E,
        FD
    }
    protected TaskType type;

    /**
     * Constructs a Task with the specified description and task type.
     * The task is initialized as not done.
     *
     * @param description the description of the task
     * @param type the type of the task (T, D, or E)
     */
    public Task(String description, TaskType type) {
        this.description = description;
        this.type = type;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task.
     * An "X" indicates the task is done, while a space indicates it is not done.
     *
     * @return the status icon as a string
     */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /**
     * Returns the task type as a string, formatted with square brackets.
     *
     * @return the task type as a string (e.g., "[T]", "[D]", or "[E]")
     */
    public String getTaskType() {
        return "[" + type + "]";
    }

    /**
     * Marks the task as done.
     *
     * @throws RedundantStateChangeException if the task is already marked as done
     */
    public void markAsDone() throws RedundantStateChangeException {
        if (this.isDone) {
            throw new RedundantStateChangeException("");
        }
        this.isDone = true;
    }

    public String getDescription() {
        return this.description;
    }

    /**
     * Marks the task as not done.
     *
     * @throws RedundantStateChangeException if the task is already marked as not done
     */
    public void unmark() throws RedundantStateChangeException {
        if (!this.isDone) {
            throw new RedundantStateChangeException("");
        }
        this.isDone = false;
    }


    /**
     * Formats the task into a string representation suitable for storage.
     * The format includes the task type, status (1 for done, 0 for not done), and description.
     *
     * @return a formatted string representing the task for storage
     */
    public String format() {
        int status;

        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(status).append("|").append(description);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [")
                .append(this.getStatusIcon()).append("] ")
                .append(this.description);
        return sb.toString();
    }
}

