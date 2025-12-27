package smartstelle;

/**
 * Represents a general Task in the SmartStelle application.
 * <p>
 * This is an abstract class that defines the common properties and behaviors
 * of all types of tasks (e.g., ToDo, Deadline, Event). Each task has a name and
 * a completion status.
 */
public abstract class Task {
    private final String taskName;
    private boolean isCompleted;

    /**
     * Constructs a Task with the specified name and completion status.
     *
     * @param taskName    The name of the task.
     * @param isCompleted The initial completion status of the task.
     * @throws StelleException If the task name is empty.
     */
    public Task(String taskName, boolean isCompleted) throws StelleException {
        assert taskName != null : "Task name must not be null";

        if (taskName == null || taskName.isEmpty()) {
            throw new StelleException("Why you doing nothing.");
        }
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    /**
     * Returns a string representation of the task for display purposes.
     * <p>
     * The format includes the completion status as "[X]" for completed tasks
     * and "[ ]" for incomplete tasks, followed by the task name.
     *
     * @return The display string of the task.
     */
    public String display() {
        assert taskName != null && !taskName.isEmpty() : "Task name must be valid";

        return "[" + (isCompleted ? "X" : " ") + "] " + this.taskName;
    }

    public String getName() {
        return taskName;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }

    /**
     * Marks the task as completed.
     */
    public void mark() {
        this.isCompleted = true;
    }

    /**
     * Marks the task as not completed.
     */
    public void unmark() {
        this.isCompleted = false;
    }

    public boolean matches(String keyword) {
        assert keyword != null : "Keyword must not be null";
        return this.taskName.toLowerCase().contains(keyword.toLowerCase());
    }

    private boolean equalsIgnoringCase(Task task) {
        return this.getName().toLowerCase().equals(task.getName().toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }

        Task other = (Task) obj;
        return this.equalsIgnoringCase(other) && this.getIsCompleted() == other.getIsCompleted();
    }

    /**
     * Returns the string representation of the task in the format used for saving
     * to file.
     * <p>
     * This method must be implemented by subclasses to define how the task is
     * saved.
     *
     * @return The string representation of the task for storage.
     */
    public abstract String toSaveFormat();
}
