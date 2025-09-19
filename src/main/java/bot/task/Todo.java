package bot.task;

/**
 * Represents a simple to-do task without any specific date or time constraints.
 * This class extends the base Task class and provides to-do specific functionality.
 */
public class Todo extends Task {

    private static final String TASK_TYPE = "T";

    /**
     * Constructs a To-do task with the specified task name.
     * The task is initially marked as not completed.
     *
     * @param taskName the name/description of the to-do task
     */
    public Todo(String taskName) {
        super(taskName);
    }

    /**
     * Constructs a To-do task with the specified task name and completion status.
     *
     * @param taskName the name/description of the to-do task
     * @param isDone the completion status of the task
     */
    public Todo(String taskName, boolean isDone) {
        super(taskName, isDone);
    }

    /**
     * Return the string format of the To-do to be written into a file.
     * The format is: "T | [status] | [task name]"
     *
     * @return The string format of To-do suitable for file writing
     **/
    @Override
    public String toFileString() {
        String fieldSeparator = " | ";
        return TASK_TYPE + fieldSeparator + super.toFileString() + "\n";
    }

    /**
     * Compares this to-do task to another task based on their date/time for sorting.
     * <p>
     * Note: The current implementation implies that {@code To-do} tasks do not have a
     * specific date/time and will sort in a predetermined order relative to
     * {@code Deadline} and {@code Event} tasks. The value returned (1) when compared
     * to non-To-do tasks means this To-do task will be considered "greater than" (i.e.,
     * come after) those tasks during a sort.
     * </p>
     *
     * @param otherTask the task to be compared with this to-do task
     * @return 0 if the other task is also a {@code To-do}, 1 if the other task is
     *         a {@code Deadline} or {@code Event}
     */
    @Override
    public int compareDateTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return 0;
        }
        return 1;
    }

    /**
     * Display string format of To-do task with status and task name.
     * The format is: "[T][status] [task name]"
     *
     * @return The string format of To-do suitable for display
     **/
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString();
    }
}
