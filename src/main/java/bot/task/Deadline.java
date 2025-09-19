package bot.task;

import bot.util.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents a deadline task that has a specific due date and time.
 * This class extends the base Task class and adds deadline-specific functionality.
 */
public class Deadline extends Task {
    private static final String TASK_TYPE = "D";

    /** Deadline date time of the task */
    private final LocalDateTime deadline;

    /**
     * Constructs a Deadline task with the specified task name and deadline.
     * The task is initially marked as not completed.
     *
     * @param taskName the name/description of the task
     * @param deadline the deadline date and time as a string in a supported format
     * @throws IllegalArgumentException if the deadline string cannot be parsed into a valid date/time
     */
    public Deadline(String taskName, String deadline)
            throws IllegalArgumentException {
        super(taskName);
        this.deadline = DateTimeUtils.fromString(deadline);
    }

    /**
     * Constructs a Deadline task with the specified task name, deadline, and completion status.
     *
     * @param taskName the name/description of the task
     * @param deadline the deadline date and time as a string in a supported format
     * @param isDone the completion status of the task
     * @throws IllegalArgumentException if the deadline string cannot be parsed into a valid date/time
     */
    public Deadline(String taskName, String deadline, boolean isDone)
            throws IllegalArgumentException {
        super(taskName, isDone);
        this.deadline = DateTimeUtils.fromString(deadline);
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    /**
     * Return the string format of the bot.task.Deadline to be written into a file
     *
     * @return The string format of bot.task.Deadline suitable for file writing
     **/
    @Override
    public String toFileString() {
        String fieldSeparator = " | ";
        return TASK_TYPE + fieldSeparator + super.toFileString()
                + fieldSeparator + DateTimeUtils.toFileString(deadline) + "\n";
    }

    /**
     * Compares this deadline task to another task based on their date/time for sorting.
     *
     * @param otherTask the task to be compared with this deadline task
     * @return a negative integer if this task should come before the other task,
     *         zero if they are considered equal for date sorting,
     *         or a positive integer if this task should come after the other task
     * @throws IllegalArgumentException if the {@code otherTask} is not a recognized subclass
     */
    @Override
    public int compareDateTo(Task otherTask) {
        if (otherTask instanceof Todo) {
            return -1;
        } else if (otherTask instanceof Deadline) {
            return deadline.compareTo(((Deadline) otherTask).getDeadline());
        } else if (otherTask instanceof Event) {
            return deadline.compareTo(((Event) otherTask).getEndTime());
        } else {
            throw new IllegalArgumentException("Invalid task type");
        }
    }

    /**
     * Display string format of bot.task.Deadline task with status, task name
     * and deadline
     *
     * @return The string format of bot.task.Deadline suitable for display
     **/
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (by: " + DateTimeUtils.toDisplayString(deadline) + ")";
    }
}
