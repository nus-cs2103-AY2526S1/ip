/// This class extends from Task, representing a task with a deadline.
///
/// @author Ravichandran Gokul
public class DeadlineTask extends Task{
    // New fields declared
    private String deadline;

    /**
     * Constructs a new {@code DeadlineTask} object with the name of the task and its deadline.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param nameOfTask  The name of the task
     * @param deadline    The deadline of the task
     */
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
