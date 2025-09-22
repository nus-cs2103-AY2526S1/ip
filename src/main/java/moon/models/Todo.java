package moon.models;

/**
 * Represents a deadline task with a description.
 * <p>
 * Inherits the name and done status from {@link Task}.
 */
public class Todo extends Task {

    /**
     * Creates a new todo task.
     *
     * @param name     Description of the todo task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Converts this todo task to its storage string representation.
     * <p>
     * Format: {@code T | doneFlag | name}
     * <ul>
     *   <li>{@code T} = todo identifier</li>
     *   <li>{@code doneFlag} = {@code 1} if done, {@code 0} otherwise</li>
     *   <li>{@code name} = description</li>
     * </ul>
     *
     * @return Storage string for this todo task.
     */
    @Override
    public String toStorageString() {
        return String.join(" | ",
                "T",
                this.isDone() ? "1" : "0",
                getName());
    }

    /**
     * Returns the string representation of this todo for user display.
     * <p>
     * Format: {@code [E][X] name}
     *
     * @return Formatted string for display.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
