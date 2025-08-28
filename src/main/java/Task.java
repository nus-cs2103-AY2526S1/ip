/**
 * This class represents a To-do task.
 *
 * @author Ravichandran Gokul
 */
public class Task {
    // Fields declared
    private String completionStatus;
    private String nameOfTask;

    /**
     * Constructs a new {@code Task} object with the name of the task.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param nameOfTask  The name of the task
     */
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
        this.completionStatus = " ";
    }

    /**
     * Marks task as done
     */
    public void markAsDone() {
        completionStatus = "X";
    }

    /**
     * Unmarks task completion
     */
    public void unmarkTask() {
        completionStatus = " ";
    }

    /**
     * Getter for completion status of task
     *
     * @return String
     */
    public String getCompletionStatus() {
        return this.completionStatus;
    }

    /**
     * Getter for the name of the task
     *
     * @return String
     */
    public String getNameOfTask() {
        return this.nameOfTask;
    }

    // toString overriding
    @Override
    public String toString() {
        return "[T][" + completionStatus + "] " + nameOfTask;
    }
}
