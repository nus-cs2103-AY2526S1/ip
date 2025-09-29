package johnny.tasks;

/**
 * Represents a task that the user wishes to store and keep note of
 */
public class Task {
    protected String name;
    protected boolean isCompleted;

    /**
     * Creates a new Task with the given name
     * 
     * @param name
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Creates a new Task with the given name and boolean
     * 
     * @param name
     * @param isCompleted Whether task is done
     */
    public Task(String name, boolean isCompleted) {
        this.name = name;
        this.isCompleted = isCompleted;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDone() {
        return this.isCompleted;
    }

    public void markComplete() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }

    /**
     * Returns the string format used in storing the task in the save file
     * 
     * @return String format
     */
    public String getStoredString() {
        if (isCompleted)
            return "T|1|" + this.name;
        return "T|0|" + this.name;
    }

    public boolean contains(String subString) {
        return this.name.contains(subString);
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }
}
