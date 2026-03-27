package david.task;

/**
 * Represents a Task.
 */
public class Task {
    private String text;
    private boolean isDone;

    /**
     * Initialises a normal Task.
     *
     * @param text Task description.
     */
    public Task(String text) {
        assert text != null : "Text should never be null";
        this.text = text;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Marks Task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    public String getText() {
        return this.text;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.getStatusIcon(), text);
    }
}
