package chiikawa.task;

/**
 * Class for Tasks with deadlines, due before a specific date/time.
 */
public class DeadlineTask extends Task {
    private String deadline;
    private String formattedDeadline;

    /**
     * Constructor for creating new deadline tasks by user.
     *
     * @param name Name of the task.
     * @param deadline Due date of the task.
     */
    public DeadlineTask(String name, String deadline) {
        super(name);
        this.deadline = deadline;
        this.formattedDeadline = super.reformatTime(deadline);
    }

    /**
     * Constructor for creating new deadline tasks as they are loaded from save file.
     *
     * @param name Name of the task.
     * @param isCompleted Status of the task.
     * @param deadline Due date of the task.
     */
    public DeadlineTask(String name, boolean isCompleted, String deadline) {
        super(name, isCompleted);
        this.deadline = deadline;
        this.formattedDeadline = super.reformatTime(deadline);
    }

    @Override
    public String toString() {
        String outputTime = this.formattedDeadline.isEmpty() ? this.deadline : this.formattedDeadline;
        return "D " + super.toString() + " | " + outputTime;
    }
}
