package Dan.Task;

import Dan.Parser.DateParser;

import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.util.ArrayList;

public class Deadline extends Task {
    private final LocalDate deadlineDate;

    /**
     * Constructs a new Deadline task with the specified completion status, description, and deadline date.
     *
     * @param isDone a String representation of the task's completion status ("true" or "false")
     * @param description the description of the deadline task
     * @param deadlineDate the LocalDate representing when this task is due
     */
    public Deadline(String isDone, String description, LocalDate deadlineDate) {
        super(isDone, description);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Creates a new Deadline task from the provided task information.
     * Expects exactly 3 elements: completion status, description, and deadline date string.
     *
     * @param taskInfo an ArrayList containing task information in the order: [isDone, description, deadlineString]
     * @return a new Deadline Task object created from the provided information
     * @throws IllegalArgumentException if taskInfo does not contain exactly 3 elements or if date parsing fails
     */
    public static Task create(ArrayList<String> taskInfo) throws IllegalArgumentException {
        if (taskInfo.size() != 3) {
           throw new IllegalArgumentException();
        }

        String isDone = taskInfo.get(0);
        String description = taskInfo.get(1);

        String deadlineString = taskInfo.get(2);
        LocalDate deadline = DateParser.parseDate(deadlineString);

        if (deadline == null) {
            return null;
        } else {
            return new Deadline(isDone, description, deadline);
        }
    }

    /**
     * Gets the deadline date of this task.
     *
     * @return the LocalDate representing when this task is due
     */
    public LocalDate getDeadlineDate() {
        return this.deadlineDate;
    }

    public LocalDate getReminderDate() {
        return this.deadlineDate;
    }

    /**
     * Gets the type of this task.
     *
     * @return TaskType.DEADLINE indicating this is a deadline task
     */
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Returns a string representation of the deadline task, including its type marker,
     * completion status, description, and deadline date.
     *
     * @return a formatted string in the format "[D][status] description (by:deadline)"
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + this.deadlineDate + ")";
    }
}
