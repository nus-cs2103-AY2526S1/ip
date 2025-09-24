package cortana.task;

import java.util.Objects;

import cortana.exception.CortanaException;

/**
 * Abstract base class representing a cortana.task.Task. Contains common fields and behavior for all
 * task types.
 */
public abstract class Task {
    private String name;
    private boolean isDone;

    /**
     * Constructs a cortana.task.Task with the specified name.
     *
     * @param name the name/description of the task
     */
    public Task(String name) {
        this.name = name;
        isDone = false;
    }

    /**
     * Marks this task as done.
     *
     * @throws CortanaException if the task is already marked as done
     */
    public void mark() throws CortanaException {
        if (isDone) {
            throw new CortanaException("Action failed. cortana.task.Task is already marked as done");
        }
        isDone = true;
    }

    /**
     * Marks this task as not done.
     *
     * @throws CortanaException if the task is already marked as not done
     */
    public void unmark() throws CortanaException {
        if (!isDone) {
            throw new CortanaException("Action failed. cortana.task.Task is already marked as undone");
        }
        isDone = false;
    }
    /**
     * Gets name field of task.
     *
     * @return name of task
     */
    public String getName() {
        return name;
    }
    /**
     * Gets isDone field of task.
     * @return boolean representing whether task is done
     */
    public boolean getIsDone() {
        return isDone;
    }

    /**
     * Compares this task object (of the same class) to another task object by name and isDone fields
     * @param obj
     * @return
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) { // object check
            return true;
        }
        if (obj == null) { // null check
            return false;
        }
        if (this.getClass() != obj.getClass()) { // class check
            return false;
        }
        Task task = (Task) obj;
        return Objects.equals(this.getName(), task.getName()) && this.getIsDone() == task.getIsDone();
    }
    /**
     * Generates a hash code for the ToDo object based on its name and isDone fields.
     * @return the hash code value for this object
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.getName(), this.getIsDone());
    }
    /**
     * Returns string representation of the task. Subclasses should override for specific formatting.
     *
     * @return string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", name);
    }
}
