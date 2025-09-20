package airy;

/**
 * This class is a subclass from task
 * Works like a normal task without deadlines
 */
public class Todo extends Task {

    /**
     * Constructs a new To do task with the specified description.
     *
     * @param taskName the name of the to do task
     */
    public Todo(String taskName) {
        super(taskName);
        assert taskName != null && !taskName.isBlank() : "Task name must not be empty";
    }

    /**
     * Provides info of the to do task in string format
     *
     * @return a formatted string representation of the to do task
     */
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Since To do tasks do not have any additional details beyond the basic
     * task information, this method returns an empty string.
     *
     * @return an empty string, as To do tasks have no extra storage details
     */
    @Override
    public String getExtraDetailsForStorage() {
        return ""; // nothing extra
    }
}
