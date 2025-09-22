package izayoi.task;

/**
 * Represents an object that can be marked as completed or uncompleted
 */
public interface Actionable {
    /**
     * Marks this object as completed
     */
    public void markAsDone();

    /**
     * Marks this object as uncompleted
     */
    public void markAsNotDone();

    /**
     * Checks if the current object is marked as completed
     * @return whether the object is completed
     */
    public boolean isCompleted();
}
