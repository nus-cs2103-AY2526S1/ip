package uy;

/**
 * Represents a generic task with a name, completion status and type.
 * Subclasses should provide more specific fields (e.g. deadlines or events).
 */
public class Task implements Comparable<Task> {
    protected String task_name;
    protected Boolean marked = false;
    protected String type;

    /**
     * Construct a Task.
     *
     * @param task_name description of the task
     * @param marked whether the task is marked done
     * @param type the single-letter type code (e.g. "T", "D", "E")
     * @throws IllegalArgumentException when task_name is empty
     */
    public Task(String task_name, Boolean marked, String type) throws IllegalArgumentException {
        if(task_name.length() == 0) {
            throw new IllegalArgumentException("OOPS!!! The description of a task cannot be empty.");
        }
        this.task_name = task_name;
        this.marked = marked;
        this.type = type;
    }

    /**
     * Construct a Task without specifying a type.
     * Used by subclasses when setting the type later.
     */
    public Task(String task_name, Boolean marked) {
        this.task_name = task_name;
        this.marked = marked;
    }

    /**
     * Returns the task description.
     *
     * @return task name
     */
    public String getTask_name() {
        return task_name;
    }

    /**
     * Set the task description.
     *
     * @param task_name new description
     */
    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    /**
     * Returns true if the task is marked done.
     *
     * @return marked status
     */
    public Boolean getMarked() {
        return marked;
    }

    /**
     * Mark the task as done.
     */
    public void mark() {
        this.marked = true;
    }

    /**
     * Mark the task as not done.
     */
    public void unmark() {
        this.marked = false;
    }

    public String getMarkedString() {
        if(this.marked) {
            return "[X]";
        } else {
            return "[]";
        }
    }

    public String getType() {
        return "[" + this.type + "]";
    }

    @Override
    public int compareTo(Task other) {
        return this.type.compareTo(other.type);
    }

    @Override
    public String toString() {
        return this.getType() + this.getMarkedString() + " " + this.task_name;
    }
}
