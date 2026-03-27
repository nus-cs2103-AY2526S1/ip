package marvin.task;

import java.io.Serializable;
import java.util.ArrayList;

import marvin.MarvinException;

/**
 * An abstract task class that represents a task that Marvin can manage.
 */
public abstract class Task implements Serializable {
    private final String description;
    private boolean isDone;
    private Task parentTask;
    private final ArrayList<Task> dependentTasks;

    /**
     * Instantiates a task that is not done.
     *
     * @param description The task description.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.parentTask = null;
        this.dependentTasks = new ArrayList<>();
    }

    /**
     * Instantiates a task.
     *
     * @param description The task description.
     * @param isDone      The state of completeness of the task.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.parentTask = null;
        this.dependentTasks = new ArrayList<>();
    }

    public Task getParentTask() {
        return this.parentTask;
    }

    /**
     * Sets a task to be done after another task.
     *
     * @param child The task to be completed after
     */
    public void setChildTask(Task child) {
        if (child.parentTask != null) {
            throw new MarvinException("You cannot set a dependency for a task that already relies on another task.");
        } else {
            Task grandFather = this;
            while (grandFather.parentTask != null) {
                grandFather = grandFather.parentTask;
            }
            if (grandFather.equals(child)) {
                throw new MarvinException("Circular dependencies are not allowed.");
            }
        }

        // Carry on
        child.parentTask = this;
        this.dependentTasks.add(child);
    }

    public ArrayList<Task> getDependentTasks() {
        return this.dependentTasks;
    }

    /**
     * Unlinks a task that was set to be done-after another task.
     */
    public void unlinkParent() {
        if (this.parentTask != null) {
            this.parentTask.dependentTasks.remove(this);
        }
    }

    /**
     * Returns the description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the isDone status of the task.
     */
    public boolean getIsDone() {
        return this.isDone;
    }

    /**
     * A setter for the done parameter.
     * Affects the string display of the item.
     *
     * @param isDone represents the new state of doneness of the task
     */
    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
    }

    /**
     * A representation of a task's doneness state.
     * Task is abstract and can never be instantiated. This method
     * is used to keep the doneness representation uniform among all
     * subclasses.
     */
    @Override
    public String toString() {
        String mark = this.getIsDone() ? "X" : " ";
        return String.format("[%s]", mark);
    };
}
