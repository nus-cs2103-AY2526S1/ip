package nacho.tasks;

/**
 * Abstract Parent Class of all Task Classes
 * <p>
 *     Every Task will have at least a Title and Completion Status
 * </p>
 */

public abstract class Task {
    private boolean isCompleted;
    private String title;

    /**
     * Creates a new Task Object with the input Title
     * Default isCompleted status is false
     * @param title String title of Task
     */
    public Task(String title) {
        this.title = title;
        this.isCompleted = false;
    }

    public String getTitle() {
        return this.title;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public void unmarkCompleted() {
        this.isCompleted = false;
    }

    public int isCompleted() {
        return this.isCompleted ? 1 : 0;
    }


    /**
     *  Returns a string with details of all fields in a specific format.
     *  <p>
     *  Exact implementation will be handled by child classes.
     *  Content within all representations are to be delimited by " | "  strings.
     *  </p>
     *
     *  @return string representation of all fields in Task instance.
     */
    public abstract String getStorageRepresentation();

    @Override
    public String toString() {
        String checkbox = this.isCompleted ? "[X]" : "[ ]";
        return checkbox + " " + this.title;
    }

}
