package jimmy.task;

/**
 * Represents a task in the TaskList.
 */
public class Task {
    public static final String EMPTY_TAG = "";
    private boolean isCompleted;
    private final String description;
    private String tag; // Initialize tag to be empty

    /**
     * Constructor for a Task object.
     *
     * @param description The description of the task.
     */
    public Task(String description, boolean completed, String tag) {
        this.isCompleted = completed;
        this.description = description;
        this.tag = tag;
    }

    /**
     * Marks a task as done.
     */
    public void markDone() {
        this.isCompleted = true;
    }

    /**
     * Marks a task as not done.
     */
    public void markNotDone() {
        this.isCompleted = false;
    }

    /**
     * Returns the description of the task.
     *
     * @return Description of the task.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Returns the completion status of the task.
     *
     * @return Completion status of the task.
     */
    public boolean getCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns the appropriate status icon depending on the completion status of the task.
     *
     * @return Appropriate status icon depending on the completion status of the task.
     */
    public String getStatusIcon() {
        return this.isCompleted ? "X" : " ";
    }

    /**
     * Returns the formatted string to be used for storing in the hard disk.
     *
     * @return Formatted string.
     */
    public String toStorageString() {
        return String.format("%s", this.tag, this.getDescription(), this.getCompleted());
    }

    /**
     * Returns the string representation of this task.
     *
     * @return String representation of this task.
     */
    @Override
    public String toString() {
        String tagMessage = this.tag.equals(EMPTY_TAG) ? "" : String.format(" (#%s)", this.tag);
        String formattedString = String.format("[%s] %s%s", this.getStatusIcon(), this.getDescription(), tagMessage);
        return formattedString;
    }

    /**
     * Sets the tag of a task.
     * @param tag Tag to be set.
     */
    public void setTag(String tag) {
        this.tag = tag;
    };

    /**
     * Gets the tag of a task.
     */
    public String getTag() {
        return this.tag;
    };

    /**
     * Untags a task.
     */
    public void untag() {
        this.tag = "";
    };
}
