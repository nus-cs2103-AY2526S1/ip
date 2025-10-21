package Task;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Base abstraction for a user task.
 * Stores a name/description and completion state,
 * and defines the common rendering and serialization contracts used by the app.
 * Concrete subclasses include Todo, Deadline, and Event.
 */
public abstract class TaskItem {
    protected final String name;
    protected boolean isDone;

    /**
     * Creates a task with the given name and completion flag.
     *
     * @param name Description of the task.
     */
    public TaskItem(String name) {
        assert name != null : "TaskItem: name must not be null";
        assert !name.trim().isEmpty() : "TaskItem: name must not be empty";
        this.name = name;
        this.isDone = false;
    }

    /**
     * Marks this task as completed.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns a brief two-letter tag that identifies the task type.
     */
    protected abstract String typeTag();

    /**
     * Returns extra details specific to the task type, formatted for display.
     * Subclasses return an empty string when not applicable.
     */
    protected String extraDetail() {
        return "";
    }

    /**
     * Returns a machine-friendly representation suitable for storage.
     */
    public abstract String toSaveString();

    /**
     * Returns the UI display string, combining the
     * type tag, completion status, name, and any extra detail.
     */
    @Override
    public String toString() {
        String status = isDone ? "[X] " : "[ ] ";
        return typeTag() + status + name + extraDetail();
    }

    public String getName() {
        return this.name;
    }

    public Optional<LocalDate> getDate() {
        return Optional.empty();
    }
}

