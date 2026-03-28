package Meal;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Deadline task
 * A deadline task has a task name and deadline date
 * */
public class Deadline extends Task {
    LocalDate taskTime;
    private static final DateTimeFormatter PRETTY = DateTimeFormatter.ofPattern("MMM d yyyy");

    /**
     * Deadline constructor
     * @param taskName name of task
     * @param taskTime deadline of task
     */
    public Deadline(String taskName, String taskTime) {
        super(taskName);
        this.taskTime = LocalDate.parse(taskTime);
    }

    /**
     * Returns deadline
     */
    public LocalDate getDeadline() {
        return this.taskTime;
    }

    /**
     * Returns string representation of deadline task
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.taskTime.format(PRETTY) + ")";
    }
}
