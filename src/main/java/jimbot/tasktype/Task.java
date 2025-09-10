package jimbot.tasktype;

import java.io.Serial;
import java.io.Serializable;

public class Task implements Serializable {
    // Version identifier number that is stored with the serialized tasks
    @Serial
    private static final long SERIAL_VERSION_UID = 1L;
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
