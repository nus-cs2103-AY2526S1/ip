package BobbyWasabi.Tasks;

/**
 * Represents a generic task with a description and completion status.
 * Serves as the base class for more specific task types such as {@code Deadline}, {@code Event}, and {@code ToDo}.
 */
public class Task {

    private String description;
    private Boolean isMarked;

    /**
     * Constructs a new {@code Task} with the given description and completion status.
     *
     * @param description The description of the task.
     * @param isMarked Whether the task is marked as completed.
     */
    public Task(String description, Boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void setIsMarked(Boolean bool) {
        this.isMarked = bool;
    }

    public String getDescription() {
        return this.description;
    }

    public Boolean getIsMarked() {
        return this.isMarked;
    }

    /**
     * Returns a checkbox representation of the task's completion status.
     *
     * @return "[X]" if the task is completed, "[ ]" otherwise.
     */
    public String checked() {
        if (this.isMarked) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns a serialized string representation of the task for file storage.
     * Subclasses should override this to provide their specific format.
     *
     * @return A string representing the task for persistent storage.
     */
    public String getData() {
        return "";
    }

    /**
     * Returns the string representation of the task,
     * including the checkbox and the task description.
     *
     * @return Formatted string representing the task.
     */
    @Override
    public String toString() {
        return this.checked() + " " + this.description;
    }
}
