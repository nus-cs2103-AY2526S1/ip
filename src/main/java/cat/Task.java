package cat;

/**
 * Represents a task with a name and a completion status.
 * Provides methods to mark the task as done/undone and to get its status.
 */
public class Task {

    /**name of task */
    protected String name;

    /**Whether the task is completed or not */
    protected Boolean isDone;

    /**
     * Creates a new Task with the given name
     * By default, task is not completed.
     *
     * @param name The name or description of the task.
     */
    public Task(String name){
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks the task as completed.
     */
    public void setDone(){
        this.isDone = true;
    }

    /**
     * Marks the task as not complete.
     */
    public void setUnDone(){
        this.isDone = false;
    }

    /**
     * Returns the status of the task as a string
     * "[X]" indicates completed, "[ ]" indicates not completed.
     *
     * @return A string representing the tasks's completion status
     */
    public String getStatus(){
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns a formatted string representation of the task.
     * Intended to be overridden by subclasses.
     *
     * @return A String representing the formatted task.
     */
    public String getFormat(){
        return "@Override";
    }

}
