package khat.task;

/** Represents a Todo task */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the given description and completion status.
     *
     * @param description Description of task.
     * @param isDone True if task is completed, false otherwise.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toSaveString() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.getDescription();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
