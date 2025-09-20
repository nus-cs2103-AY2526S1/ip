package pickle.task;

/**
 * Abstract class of a Task to have all the basic methods that a task can execute.
 */
public abstract class Task {
    private final String description;
    private boolean isComplete;

    /**
     * Constructor of a Task.
     * @param description information of what the Task is.
     */
    public Task(String description) {
        this.description = description;
        this.isComplete = false;
    }

    public void mark() {
        isComplete = true;
    }

    public void unmark() {
        isComplete = false;
    }

    /**
     * shows the status of whether it is completed.
     * @return true if task is completed.
     */
    public boolean isComplete() {
        return this.isComplete;
    }

    public String getDescription() {
        return this.description;
    }

    public abstract String type();

    public abstract String writeToFile();

    @Override
    public String toString() {
        String symbol = " ";
        if (isComplete) {
            symbol = "X";
        }
        return "[" + symbol + "] " + description;
    }
}
