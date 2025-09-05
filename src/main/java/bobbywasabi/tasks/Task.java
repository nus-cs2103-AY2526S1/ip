package bobbywasabi.tasks;

/**
 * Represents a generic task with a description and completion status.
 */
public class Task {
    private String description;
    private Boolean isMarked;

    /**
     * Constructs a Task with a description and marked status.
     *
     * @param description The description of the task.
     * @param isMarked    True if the task is marked as done, false otherwise.
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

    /**
     * Checks if the task's description contains the exact keyword.
     * Splits the description by spaces and matches whole words.
     *
     * @param keyword The keyword to search for.
     * @return True if the keyword matches any word in the description, false otherwise.
     */
    public boolean find(String keyword) {
        String[] wordList = this.description.split(" ");
        for (String word : wordList) {
            if (keyword.equals(word)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the Mark/Unmarked Checkbox
     * Depends on the Boolean value of isMarked for the Task instance
     *
     * @return Mark/Unmarked Checkbox.
     */
    public String getMarkedStatus() {
        if (this.isMarked) {
            return "[X]";
        } else {
            return "[ ]";
        }
    }

    /**
     * Returns the String representation of task
     * Has the Marked/UnMarked Checkbox and then the task's description
     *
     * @return String representation of task.
     */
    public String toString() {
        return this.getMarkedStatus() + " " + this.description;
    }

    /**
     * Returns a formatted string representing the task data for storage or export.
     * This base implementation returns an empty string and should be overridden in subclasses.
     *
     * @return Formatted task data string.
     */
    public String getData() {
        return "";
    }
}
