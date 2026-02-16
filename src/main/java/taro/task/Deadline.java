package taro.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task with a deadline by which it should be completed.
 * A {@code Deadline} is a type of {@link Task} that has a description
 * and a specific due date. It can be marked as done or not done like
 * other tasks.
 * Example: {@code deadline return book /by 2025-09-10}
 */
public class Deadline extends Task {
    protected LocalDate by;

    /**
     * Constructs a {@code Deadline} task with the given description, due date, and completion status.
     *
     * @param description text description of the deadline task
     * @param by the due date of the task, in the form YYYY-MM-DD
     * @param isCurrentTaskDone {@code true} if the task is already marked as done,
     *                          {@code false} otherwise
     */
    public Deadline(String description, LocalDate by, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
        this.by = by;
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("MMM dd yyyy");
        return "[D]" + super.toString() + " (by: " + by.format(fmt) + ")";
    }

    @Override
    public String toFile() {
        return "D " + super.toFile() + " | " + by.toString();
    }

    /**
     * This method is used to extract due date
     * @return due date only of a deadline task.
     */
    @Override
    public LocalDate getDueDate() {
        return this.by;
    }
}
