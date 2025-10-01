package panda.task;

/**
 * Task is the superclass for ToDo, Deadline and Event.
 */
public abstract class Task {
    protected String description;
    protected String status;

    /**
     * Constructs Task object with description from user.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        this.description = description;
        this.status = "[ ]";
    }

    /**
     * Constructs Task object based on info from text file.
     *
     * @param status Status of task completion.
     * @param description Description of task.
     */
    public Task(String status, String description) {
        this.description = description;
        this.status = status;
    }

    /**
     * Changes the status for the task.
     *
     * @param action Unmark or mark.
     */
    public void changeStatus(String action) {
        if (action.equals("mark")) {
            status = "[X]";
        } else {
            status = "[ ]";
        }
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status;
    }

    /**
     * Returns true if keyword is found in description.
     *
     * @param keyword Keyword provided by user.
     * @return true if description contains keyword, false otherwise.
     */
    public boolean hasKeyword(String keyword) {
        return description.contains(keyword);
    }

    public abstract String writeToFile();

    @Override
    public String toString() {
        return status + " " + description;
    }
}
