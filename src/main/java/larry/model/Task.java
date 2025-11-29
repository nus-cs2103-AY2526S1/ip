package larry.model;

/**
 * Represents a user task with a description and completion state.
 * Subclasses specialize display (Todo, Deadline, Event).
 */
public class Task {
    private final String description;
    private boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /** Marks this task as done. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks this task as not done yet. */
    public void markUndone() {
        this.isDone = false;
    }

    /** Returns a human-readable form used by the UI and tests. */
    private String statusIcon() {
        return isDone ? "X" : " ";
    }
    protected String typeIcon() {
        return "?";
    }
    @Override
    public String toString() {
        return "[" + typeIcon() + "][" + statusIcon() + "] " + description;
    }
    public String getDescription() { return description; }
    public boolean isDone() { return isDone; }
}
