package heimerdinger.task;

/**
 * Represents a simple task without any associated date/time.
 *
 * <p>{@code ToDo} is the most basic type of {@link Task}, consisting only of
 * a description and a completion status. It is identified by the icon {@code "T"}.</p>
 *
 * <p><b>Usage:</b></p>
 * <pre>{@code
 * ToDo todo = new ToDo("buy milk");
 * System.out.println(todo);
 * // [T][ ] buy milk
 *
 * todo.markAsDone();
 * System.out.println(todo);
 * // [T][X] buy milk
 * }</pre>
 */
public class ToDo extends Task {
    protected String icon = "T";

    /**
     * Constructs a {@code ToDo} with the given description.
     *
     * @param description the description of the task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the identifying icon for this task type.
     *
     * @return the string {@code "T"}
     */
    public String getIcon() {
        return icon;
    }

    public String toString() {
        return "[" + this.getIcon() + "][" + this.getStatusIcon() + "] " + this.getDescription();
    }
}
