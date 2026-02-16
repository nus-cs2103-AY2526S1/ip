package yang.task;

/**
 * Represents a todo task, which is a task without any associated date or time.
 * A {@code Todo} stores a description and its completion status.
 */
public final class Todo extends Task {
    public Todo(String description) {
        super(description, false);
    }

    /**
     * Creates a new todo task that is initially not done.
     *
     * @param description description of the task
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T][" + (isDone ? "X" : " ") + "] " + description;
    }

    @Override
    public String toStorage() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    @Override
    public boolean sameIdentity(Task other) {
        if (!(other instanceof Todo t)) {
            return false;
        }
        return this.description.trim().equalsIgnoreCase(t.description.trim());
    }
}
