package moon.models;

/**
 * Represents a deadline task with a description and a due time.
 * <p>
 * Inherits the name and done status from {@link Task}.
 */
public class Deadline extends Task {
    private final MoonDateTime dueTime;

    /**
     * Creates a new deadline task.
     *
     * @param name     Description of the deadline
     * @param dueTime  Due time of the deadline
     */
    public Deadline(String name, MoonDateTime dueTime) {
        super(name);
        this.dueTime = dueTime;
    }

    private String getDueTime() {
        return this.dueTime.toString();
    }

    /**
     * Converts this deadline to its storage string representation.
     * <p>
     * Format: {@code D | doneFlag | name | dueTime}
     * <ul>
     *   <li>{@code D} = deadline identifier</li>
     *   <li>{@code doneFlag} = {@code 1} if done, {@code 0} otherwise</li>
     *   <li>{@code name} = description</li>
     *   <li>{@code dueTime} = deadline due time</li>
     * </ul>
     *
     * @return Storage string for this deadline
     */
    @Override
    public String toStorageString() {
        return String.join(" | ",
                "D",
                this.isDone() ? "1" : "0",
                getName(),
                this.getDueTime());
    }

    /**
     * Returns the string representation of this deadline for user display.
     * <p>
     * Format: {@code [E][X] name (by: dueTime)}
     *
     * @return Formatted string for display
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.getDueTime());
    }
}
