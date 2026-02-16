package hope.tasks;

/**
 * Represents a task with a fixed duration, extending the {@link Task} class.
 * This class includes a duration attribute to specify the time required to complete the task.
 * It provides methods to format the task for storage and to display the task as a string.
 */
public class FixedDurationTask extends Task {
    private String duration;

    /**
     * Constructs a new {@code FixedDurationTask} with the specified description and duration.
     *
     * @param description the description of the task
     * @param duration    the duration required to complete the task
     */
    public FixedDurationTask(String description, String duration) {
        super(description, TaskType.FD);
        this.duration = duration;
    }

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
                .append("|").append(description)
                .append("|").append(duration);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getTaskType()).append(" [").append(this.getStatusIcon())
                .append("] ").append(this.description).append(" (needs: ")
                .append(duration).append(")");
        return sb.toString();
    }

}
