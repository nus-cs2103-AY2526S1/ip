/**
 * Represents a simple todo task without date/time attached.
 */

package salah;

/**
 * A todo task that has only a description.
 */
public class ToDos extends Task {
    /**
     * Constructs a new {@code ToDos} task with the given description.
     *
     * @param description description of the task
     */
    public ToDos(String description) {
        super(description);
    }

    /**
     * Parses a user input string into a {@code ToDos} task.
     * Accepts either raw text (e.g., {@code read book}) or a prefixed command
     * (e.g., {@code todo read book}).
     *
     * @param input the raw user input
     * @return a new {@code ToDos} task
     * @throws EmptyDescriptionException if the description is empty
     */
    public static ToDos parser(String input) throws EmptyDescriptionException {
        String description;
        if (input.toLowerCase().startsWith("todo")) {
            if (input.length() <= 4) {
                description = "";
            } else {
                description = input.substring(4).trim(); 
            }
        } else {
            description = input.trim();
        }
        if (description.isEmpty()) {
            throw new EmptyDescriptionException("Description cannot be empty.");
        }
        return new ToDos(description);
    }

    /**
     * Returns the string representation of this todo task.
     *
     * @return string representation of the task, including completion status
     */
    @Override
    public String toString() {
        return "[T]" + (this.getIsComplete() ? "[X] " : "[ ] ") + super.toString();
    }

    /**
     * Serializes this todo task into a format suitable for saving to disk.
     *
     * @return serialized string representation of the task
     */
    @Override
    public String serialize() {
        return "T | " + (getIsComplete() ? "1" : "0") + " | " + getDescription();
    }

    /**
     * Reconstructs a {@code ToDos} task from serialized parts.
     *
     * @param parts tokenized string array containing serialized data
     * @return reconstructed {@code ToDos} task
     */
    public static ToDos fromData(String[] parts) {
        boolean done = parts[1].trim().equals("1");
        String desc = parts[2].trim();
        ToDos todo = new ToDos(desc);
        if (done) todo.markAsComplete();
        return todo;
    }
}