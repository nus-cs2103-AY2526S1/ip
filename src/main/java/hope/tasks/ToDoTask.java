package hope.tasks;

/**
 * A class representing a simple to-do task, extending the Task class.
 * It includes a description and a task type (T for to-do).
 */
public class ToDoTask extends Task {
    /**
     * Constructs a ToDoTask with the specified description.
     *
     * @param description the description of the task
     */
    public ToDoTask(String description) {
        super(description, TaskType.T);
    }

    /**
     * Formats the task into a string representation suitable for storage.
     * The format includes the task type, status (1 for done, 0 for not done), and description.
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
        sb.append(type).append("|").append(status)
                .append("|").append(description);
        return sb.toString();
    }
}
