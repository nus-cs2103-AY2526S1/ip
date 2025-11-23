package Mithrandir.task;

import java.time.format.DateTimeFormatter;

public class Task {
    protected final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final String description;
    private Boolean isDone = false;

    /**
     * Constructs a new Task with the given description.
     *
     * @param description the description of the task (cannot be null or empty)
     * @throws AssertionError if description is null or empty
     */
    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Description cannot be null or empty";
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getMark() {
        return isDone ? "x" : " ";
    }

    public void markDone() {
        isDone = true;
    }

    public void markUndone() {
        isDone = false;
    }

    public boolean isMarked() {
        return isDone;
    }

    /**
     * Returns a string representation of the task in a format suitable for writing to a file.
     * The string consists of three parts separated by double pipes: the status of the task (either "done" or "undone"),
     * the description of the task, and the description of the task.
     *
     * @return a string representation of the task in a format suitable for writing to a file
     */
    public String toFileString() {
        return String.format("%s || %s", isMarked() ? "done" : "undone", getDescription());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", getMark(), getDescription());
    }
}
