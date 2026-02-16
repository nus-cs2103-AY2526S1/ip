package hope.tasks;

import hope.common.MaybeDate;

/**
 * A class representing a task with a deadline, extending the Task class.
 * It includes a description, a task type (D for deadline), and a deadline date.
 */
public class DeadlineTask extends Task {
    private MaybeDate deadline;

    /**
     * Constructs a DeadlineTask with the specified description and deadline.
     *
     * @param description the description of the task
     * @param deadline the deadline of the task as a string, parsed into a MaybeDate object
     */
    public DeadlineTask(String description, String deadline) {
        super(description, Task.TaskType.D);
        this.deadline = MaybeDate.parse(deadline);
    }

    /**
     * Formats the task into a string representation suitable for storage.
     * The format includes the task type, status (1 for done, 0 for not done), description, and deadline.
     *
     * @return a formatted string representing the task for storage
     */
    @Override
    public String format() {
        int status;

        if (this.isDone) {
            status = 1;
        } else {
            status = 0;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(type).append("|").append(status).append("|")
                .append(description).append("|").append(deadline);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon())
                .append("] ").append(this.description).append(" (by: ")
                .append(deadline).append(")");
        return sb.toString();
    }

}
