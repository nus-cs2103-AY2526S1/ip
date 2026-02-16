package taro.task;

import java.time.LocalDate;

/**
 * Represents a simple to-do task without any specific date or time constraints.
 * A {@code Todo} is the most basic type of {@link Task}, consisting only
 * of a description and a completion status (done or not done).
 * Example: {@code todo read book}
 */
public class Todo extends Task {
    /**
     * Constructs a {@code Todo} task with the given description and completion status.
     *
     * @param description text description of the todo task
     * @param isCurrentTaskDone {@code true} if the task is already marked as done,
     *                          {@code false} otherwise
     */
    public Todo(String description, boolean isCurrentTaskDone) {
        super(description, isCurrentTaskDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFile() {
        return "T " + super.toFile();
    }

    /**
     * This method is used to extract due date
     * @return null; since {@code todo} task doesn't have due date
     */
    @Override
    public LocalDate getDueDate() {
        return null;
    }

}
