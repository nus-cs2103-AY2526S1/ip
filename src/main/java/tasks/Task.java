package tasks;

import java.util.Objects;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert description != null && !description.trim().isEmpty() : "Task description must not be empty";
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "[X]" : "[ ]"); // mark done task with X
    }

    //method to mark the task as done
    public void markAsDone() {
        this.isDone = true;
    }

    // Method to mark the task as not done
    public void unmark() {
        this.isDone = false;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean isDone() {
        return this.isDone;
    }

    @Override
    public String toString() {
        return getStatusIcon() + " " + getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return isDone == task.isDone && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), description, isDone);
    }

}
