package gokschat.tasks;

/// This class extends from gokschat.tasks.Task, representing a task with a deadline.
///
/// @author Ravichandran Gokul
public class DeadlineTask extends Task {
    // New fields declared
    private String deadline;

    /**
     * Constructs a new {@code gokschat.tasks.DeadlineTask} object with the name of the task and its deadline.
     * This constructor initializes the object's internal state based on the provided parameters.
     *
     * @param nameOfTask  The name of the task
     * @param deadline    The deadline of the task
     */
    public DeadlineTask(String nameOfTask, String deadline) {
        super(nameOfTask);
        this.deadline = deadline;
    }

    // toFileString overriding
    @Override
    public String toFileString() {
        return "D | " + (getCompletionStatus().equals("X") ? "1 | " : "0 | ") + getNameOfTask() + " | by " + deadline;
    }

    // toString overriding
    @Override
    public String toString() {
        return "[D][" + getCompletionStatus() + "] " + getNameOfTask() + " (by: " + deadline + ")";
    }
}
