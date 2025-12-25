package leo;

import java.time.LocalDate;
import java.util.Optional;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        assert description != null && !description.isBlank()
                : "Task description must not be null/blank";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status whether the string is done
     * @return String indicating if task is done
     */
    public String getStatusIcon() {
        assert this.isDone == true || this.isDone == false
                : "isDone should always be a boolean";
        return (isDone ? "X" : " ");
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsUndone() {
        this.isDone = false;
    }

    @Override
    public String toString() {
        return ("[" + this.getStatusIcon() + "] " + this.description);
    }

    public abstract String toSaveFormat();

    public boolean isUpcoming(LocalDate now, LocalDate max) {
        return false;
    }
}
