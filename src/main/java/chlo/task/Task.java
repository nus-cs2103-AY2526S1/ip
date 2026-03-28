package chlo.task;

/**
 * Parent task class
 */
public class Task {
    protected String raw;
    protected String description;
    protected boolean isDone;

    /**
     * Task constructor
     * @param description
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    /**
     * Mark task as done. Append / marking to the raw string
     */
    public void markDone() {
        isDone = true;
        if (raw.charAt(raw.length() - 1) != '/') {
            raw = raw + '/';
        }
    }

    /**
     * Mark task as undone. Remove / marking at the end of the raw string
     */
    public void markUndone() {
        isDone = false;
        if (raw.charAt(raw.length() - 1) == '/') {
            raw = raw.substring(0, raw.length() - 1);
        }
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }

    public String getRaw() {
        return raw;
    }

    public String getDescription() {
        return description;
    }
}
