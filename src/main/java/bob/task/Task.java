package bob.task;

/**
 * Base class for task
 */
public class Task {
    private String taskName;
    private boolean isDone;
    private String tag;
    private int id;

    /**
     * Constructor for task
     * 
     * @param name Name of the task
     * @param tag Tag of the class e.g. E for event
     * @param isDone Indicate whether the task is done
     * @param id Id of the task
     */
    public Task(String name, String tag, boolean isDone, int id) {
        this.taskName = name;
        this.isDone = isDone;
        this.tag = tag;
        this.id = id;
    }

    /**
     * Mark the task as done
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Getter for task name
     * 
     * @return Task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Getter for tag
     * 
     * @return Tag of the task
     */
    public String getTag() {
        return this.tag;
    }

    /**
     * Unmark the class as not done
     */
    public void unmarkDone() {
        this.isDone = false;
    }

    /**
     * Return the done indicator X for done empty string for not done
     * 
     * @return Done indicator
     */
    public String returnDone() {
        return isDone ? "X" : " ";
    }

    /**
     * String for saving and loading of task
     * 
     * @return String used for saving and loading
     */
    public String saveString() {
        return "";
    }

    /**
     * Getter for Id
     * 
     * @return Id of the task
     */
    public int getId() {
        return this.id;
    }

    /**
     * toString method to return task string
     * 
     * @return Returns task string
     */
    public String toString() {
        String box = "[" + (isDone ? "X" : "") + "]";
        return box + " " + taskName;
    }
}
