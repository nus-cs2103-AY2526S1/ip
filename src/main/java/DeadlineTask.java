/**
 * This task extends from task.
 */

public class DeadlineTask extends Task{
    // New fields declared
    private String deadline;

    // Constructor
    public DeadlineTask(String nameOfTask, String deadline) {
        super(nameOfTask);
        this.deadline = deadline;
    }

    // toString overriding
    @Override
    public String toString() {
        return "[D][" + getCompletionStatus() + "] " + getNameOfTask() + " (by: " + deadline + ")";
    }

}
