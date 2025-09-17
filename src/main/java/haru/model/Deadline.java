package haru.model;

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    private final TaskTime by;

    /**
     * Constructs a Deadline task with name and deadline time.
     *
     * @param name the task name
     * @param by   the deadline time
     */
    public Deadline(String name, TaskTime by) {
        super(name, TaskType.DEADLINE);
        this.by = by;
    }

    /**
     * Returns the task description with deadline.
     *
     * @return the task description
     */
    @Override
    public String getDescription() {
        return String.format("%s (by %s)", this.getName(), this.by);
    }
}
