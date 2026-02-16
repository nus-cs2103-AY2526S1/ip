package ryuji.task;

/**
 * Abstract base class representing a generic task item.
 * <p>This class provides the core functionality for all task types, including:</p>
 * <ul>
 *   <li>Status tracking (marked/unmarked)</li>
 *   <li>Label handling</li>
 *   <li>CSV conversion for storage</li>
 * </ul>
 * <p>Concrete subclasses such as {@code ToDo}, {@code Deadline}, and {@code Event}
 * should extend this class and implement specific validation and data serialization logic.</p>
 */
public abstract class Task {

    /**
     * Indicates whether the task is marked as completed.
     */
    protected boolean isMarked = false;

    /**
     * The textual description or label of the task.
     */
    protected String label;

    /**
     * Constructs a {@code Task} with the given label.
     *
     * @param label the task's label or description
     */
    Task(String label) {
        this.label = label;
    }

    /**
     * Constructs a {@code Task} with the given label and completion status.
     *
     * @param label  the task's label or description
     * @param status the completion status (true if completed, false otherwise)
     */
    Task(String label, boolean status) {
        this.label = label;
        this.isMarked = status;
    }

    /**
     * Checks whether the task is valid.
     * <p>This method must be implemented by subclasses to perform type-specific validation.</p>
     *
     * @return {@code true} if the task is valid; {@code false} otherwise
     */
    abstract boolean checkValid();

    /**
     * Marks the task as completed.
     */
    public void mark() {
        isMarked = true;
    }

    /**
     * Unmarks the task (sets it as not completed).
     */
    public void unmark() {
        isMarked = false;
    }

    /**
     * Returns the status icon for the task.
     * <p>The icon will be "X" if the task is marked as completed; otherwise, a space character " " will be returned.</p>
     *
     * @return "X" if the task is completed; otherwise, a space character " "
     */
    public String getStatusIcon() {
        return isMarked ? "X" : " ";
    }

    /**
     * Checks whether the task's label contains the specified search term.
     *
     * @param searchTerm the keyword to search for in the label
     * @return {@code true} if the label contains the search term; {@code false} otherwise
     */
    public boolean checkLabel(String searchTerm) {
        return label.contains(searchTerm);
    }

    /**
     * Converts the task to a CSV-compatible string array for persistent storage.
     * <p>This method must be implemented by subclasses to include task-specific fields (e.g., dates, description).</p>
     *
     * @return a string array representing the task's fields, formatted for CSV storage
     */
    public abstract String toCsvRow();

    /**
     * Creates a {@code Task} object from a CSV row.
     * <p>The first element of the row should indicate the task type: "T" for ToDo, "D" for Deadline, or "E" for Event.</p>
     *
     * @param row the CSV row containing the task data
     * @return a {@code Task} object reconstructed from the row; or {@code null} if the type is invalid
     */
    public static Task fromCsvRow(String[] row) {
        Task task;
        String taskType = row[0];
        boolean isMarked = row[1].equals("X");
        String label = row[2];

        switch (taskType) {
        case "T":
            task = new ToDo(label, isMarked);
            break;
        case "D":
            task = new Deadline(label, isMarked);
            break;
        case "E":
            task = new Event(label, isMarked);
            break;
        default:
            task = null;
        }
        return task;
    }

    /**
     * Returns a string representation of the task, including its status icon and label.
     * <p>This method generates a simple string like: "[X] Task description" where X represents the completion status.</p>
     *
     * @return the formatted string representing the task, including its status icon and label
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + label;
    }
}
