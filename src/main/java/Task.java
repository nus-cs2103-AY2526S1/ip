/**
 * Task class (ToDo class essentially)
 */

public class Task {
    // Fields declared
    private String completionStatus;
    private String nameOfTask;

    // Constructor
    public Task(String nameOfTask) {
        this.nameOfTask = nameOfTask;
        this.completionStatus = " ";
    }

    // Mark task as done
    public void markAsDone() {
        completionStatus = "X";
    }

    // Unmark task completion
    public void unmarkTask() {
        completionStatus = " ";
    }

    // Getter for completion status
    public String getCompletionStatus() {
        return this.completionStatus;
    }

    // Getter for task name
    public String getNameOfTask() {
        return this.nameOfTask;
    }

    // toString overriding
    @Override
    public String toString() {
        return "[T][" + completionStatus + "] " + nameOfTask;
    }
}
