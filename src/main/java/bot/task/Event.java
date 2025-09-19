package bot.task;

import bot.util.DateTimeUtils;

import java.time.LocalDateTime;

/**
 * Represents an event task that has a specific start and end date/time.
 * This class extends the base Task class and adds event-specific functionality.
 */
public class Event extends Task {
    private static final String TASK_TYPE = "E";

    /** Start date time of the event task */
    private final LocalDateTime startTime;

    /** End date time of the event task */
    private final LocalDateTime endTime;

    /**
     * Constructs an Event task with the specified task name, start time, and end time.
     * The task is initially marked as not completed.
     *
     * @param taskName the name/description of the event
     * @param startTime the start date and time as a string in a supported format
     * @param endTime the end date and time as a string in a supported format
     * @throws IllegalArgumentException if the start or end time strings cannot be parsed into valid date/time
     */
    public Event(String taskName, String startTime, String endTime)
            throws IllegalArgumentException {
        super(taskName);

        LocalDateTime start = DateTimeUtils.fromString(startTime);
        LocalDateTime end = DateTimeUtils.fromString(endTime);

        if (!DateTimeUtils.isValidTimeRange(start, end)) {
            String invalidTimeMsg = "Hey, the date doesn't sound right, do you want to check again?";
            throw new IllegalArgumentException(invalidTimeMsg);
        }

        this.startTime = start;
        this.endTime = end;
    }

    /**
     * Constructs an Event task with the specified task name, start time, end time, and completion status.
     *
     * @param taskName the name/description of the event
     * @param startTime the start date and time as a string in a supported format
     * @param endTime the end date and time as a string in a supported format
     * @param isDone the completion status of the task
     * @throws IllegalArgumentException if the start or end time strings cannot be parsed into valid date/time
     */
    public Event(String taskName, String startTime, String endTime, boolean isDone)
            throws IllegalArgumentException {
        super(taskName, isDone);
        this.startTime = DateTimeUtils.fromString(startTime);
        this.endTime = DateTimeUtils.fromString(endTime);
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    /**
     * Return the string format of the bot.task.Event to be written into a file
     *
     * @return The string format of bot.task.Event suitable for file writing
     **/
    @Override
    public String toFileString() {
        String fieldSeparator = " | ";
        return TASK_TYPE + fieldSeparator + super.toFileString()
                + fieldSeparator + DateTimeUtils.toFileString(startTime)
                + fieldSeparator + DateTimeUtils.toFileString(endTime) + "\n";
    }

    /**
     * Compares this event task to another task based on their date/time for sorting.
     *
     * @param otherTask the task to be compared with this event task
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
            return endTime.compareTo(((Deadline) otherTask).getDeadline());
        } else if (otherTask instanceof Event) {
            return endTime.compareTo(((Event) otherTask).endTime);
        } else {
            throw new IllegalArgumentException("Invalid task type");
        }
    }

    /**
     * Display string format of bot.task.Event task with status, task name,
     * start time and end time
     *
     * @return The string format of bot.task.Event suitable for display
     **/
    @Override
    public String toString() {
        return "[" + TASK_TYPE + "]" + super.toString()
                + " (from: " + DateTimeUtils.toDisplayString(startTime)
                + ", to: " + DateTimeUtils.toDisplayString(endTime) + ")";
    }
}
