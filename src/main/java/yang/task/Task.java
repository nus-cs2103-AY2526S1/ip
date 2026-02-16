package yang.task;

import java.time.LocalDate;

/**
 * Represents an abstract task with a description and completion state.
 * Subclasses provide task types such as {@link Todo},
 * {@link Deadline}, and {@link Event}.
 */
public abstract class Task {

    private static final String TASK_TODO = "T";
    private static final String TASK_DEADLINE = "D";
    private static final String TASK_EVENT = "E";
    private static final String DONE_FLAG = "1";

    protected final String description;
    protected boolean isDone;

    /**
     * Constructs a task.
     *
     * @param description the task description text
     * @param isDone      initial completion state
     */
    protected Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    /** Marks this task as completed. */
    public void markDone() {
        this.isDone = true;
    }

    /** Marks this task as not completed. */
    public void markUndone() {
        this.isDone = false;
    }

    /**
     * Returns whether this task has been completed.
     *
     * @return {@code true} if completed; {@code false} otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task other = (Task) o;
        return this.getClass().equals(other.getClass())
                && this.description.equalsIgnoreCase(other.description);
    }

    @Override
    public int hashCode() {
        return (getClass().getSimpleName() + description.toLowerCase()).hashCode();
    }

    /**
     * Converts this task into a string suitable for saving to storage.
     *
     * @return serialized form of the task
     */
    public abstract String toStorage();

    /**
     * Returns true if this task and {@code other} represent the same logical task.
     * Default implementation compares type and normalized description.
     */
    public boolean sameIdentity(Task other) {
        return other != null
                && this.getClass().equals(other.getClass())
                && this.description.trim().equalsIgnoreCase(other.description.trim());
    }

    /**
     * Reconstructs a {@link Task} from a storage line.
     *
     * @param line the serialized line
     * @return the corresponding {@link Task}, or {@code null} if malformed
     */
    public static Task fromStorage(String line) {
        String[] parts = line.split("\\s*\\|\\s*", 4);
        if (parts.length < 3) {
            return null;
        }

        boolean isDone = DONE_FLAG.equals(parts[1]);
        String description = parts[2];

        switch (parts[0]) {
        case TASK_TODO:
            return new Todo(description, isDone);
        case TASK_DEADLINE:
            return (parts.length >= 4)
                    ? new Deadline(description, LocalDate.parse(parts[3]), isDone)
                    : null;
        case TASK_EVENT:
            return (parts.length >= 4)
                    ? new Event(description, LocalDate.parse(parts[3]), isDone)
                    : null;
        default:
            return null;
        }
    }
}
