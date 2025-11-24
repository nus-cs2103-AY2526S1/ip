package burgerburglar;

/**
 * Represents a Todo task in BurgerBurglar.
 * <p>
 * A Todo is a simple task that has a description and a completion status,
 * but no associated date or time.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo with the given description.
     *
     * @param description the description of the task
     */
    public Todo(String description) {
        super(description);
        assert !description.isBlank() : "Todo description cannot be blank";
    }

    /**
     * Constructs a Todo with the given description and completion status.
     *
     * @param description the description of the task
     * @param isDone      whether the task is completed
     */
    public Todo(String description, boolean isDone) {
        this(description);
        this.isDone = isDone;
    }

    /** Returns the type icon for Todo tasks. */
    @Override
    public String getTypeIcon() {
        return "[T]";
    }

    /** Serializes the Todo for saving in the storage file. */
    @Override
    public String serialize() {
        return String.format("T | %s | %s", isDone ? "1" : "0", description);
    }
}
