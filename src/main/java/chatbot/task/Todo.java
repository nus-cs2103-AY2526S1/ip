package chatbot.task;

/**
 * Represents a "Todo" task in the chatbot system.
 * A Todo is a type of Task that has only a description.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo with the given description.
     *
     * @param description the description of the Todo task
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns a string representation of the Todo, including its type and status.
     *
     * @return a string representation of the Todo task
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
