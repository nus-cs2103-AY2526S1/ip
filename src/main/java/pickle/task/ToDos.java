package pickle.task;

/**
 * A task that needs to be done with no specific due date.
 */
public class ToDos extends Task {

    /**
     * Creates a TODO task.
     * @param description information of the task.
     */
    public ToDos(String description) {
        super(description);
    }

    public String type() {
        return "T";
    }

    /**
     * Format to save the file into the storage text file.
     * @return a string to save the file
     */
    public String writeToFile() {
        String state = super.isComplete() ? "1" : "0";
        return "T | " + state + " | " + getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
