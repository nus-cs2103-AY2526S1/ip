package klalopz.tasks;

/**
 * Represents a simple To-Do task without a specific date.
 * Inherits from the Task class and marks the task type as "To-Do".
 */

public class ToDo extends Task {

    /**
     * Constructs a ToDo task with the given description and completion status.
     *
     * @param description The details of the To-Do task.
     * @param isCompleted True if the task is completed, false otherwise.
     */
    public ToDo(String description, Boolean isCompleted) {
        super(description, isCompleted);

        assert description != null && !description.isBlank() : "Description must not be null or blank";
    }

    @Override
    public String getTaskLogo() {
        return "[T]";
    }

}
