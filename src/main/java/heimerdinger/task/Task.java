package heimerdinger.task;

/**
 * Represents a generic task with a description and completion status.
 *
 * <p>This is the abstract base class for all specific task types
 * such as {@link ToDo}, {@link Deadline}, and {@link Event}. Each
 * subclass provides its own identifying icon and additional data
 * (e.g., deadlines or date ranges).</p>
 *
 * <p>By default, a task is created as <i>not done</i>. The status can
 * be toggled using {@link #markAsDone()} and {@link #markAsNotDone()}.</p>
 *
 * <p><b>Example:</b></p>
 * <pre>{@code
 * Task t = new ToDo("buy milk");
 * System.out.println(t.getStatusIcon()); // " "
 * t.markAsDone();
 * System.out.println(t.getStatusIcon()); // "X"
 * }</pre>
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Constructs a new {@code Task} with the given description.
     * The task is initially not marked as done.
     *
     * @param description the description of the task
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Returns the status icon for this task.
     *
     * <p>{@code "X"} if the task is done, or {@code " "} (a space) if not.</p>
     *
     * @return the status icon as a string
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks this task as completed.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks this task as not completed.
     */
    public void markAsNotDone() {
        this.isDone = false;
    }

    /**
     * Returns the identifying icon for this task type.
     *
     * <p>Implemented by subclasses to return values such as:
     * <ul>
     *     <li>{@code "T"} for {@link ToDo}</li>
     *     <li>{@code "D"} for {@link Deadline}</li>
     *     <li>{@code "E"} for {@link Event}</li>
     * </ul></p>
     *
     * @return the icon string representing the task type
     */
    public abstract String getIcon();

    /**
     * Returns the description of this task.
     *
     * @return the description text
     */
    public String getDescription() {
        return this.description;
    }

    public String toString() {
        return this.description;
    }
}
