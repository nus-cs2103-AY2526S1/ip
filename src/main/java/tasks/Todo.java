package tasks;

import misc.PepeException;

/**
 * Class representing a task without a specified deadline or duration.
 */
public class Todo extends Task {
    public Todo(String name) {
        super(name, false);
    }

    private Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Factory method to construct a Todo class from the user input
     * @param inputs A list of user-input strings
     * @return An instance of the Todo object
     * @throws PepeException if an exception occurred while parsing user input or constructing Todo class
     */
    public static Todo fromInput(String[] inputs) throws PepeException {
        if (inputs.length == 0) {
            throw new PepeException("Empty name for todo.");
        }
        return new Todo(String.join(" ", inputs));
    }

    /**
     * Deserialize Todo fromFileInput.
     * Expected format is {Type} | {Active} | {Name}
     * Example: T | 1 | read book
     * @param inputs List of file inputs delimited by |
     * @return a deserialized Todo object
     * @throws PepeException in event of parse error
     */
    public static Todo fromFileInput(String[] inputs) throws PepeException {
        if (inputs.length != 3) {
            throw new PepeException("Invalid number of arguments. Expected 3 but got " + inputs.length);
        }

        boolean isDone;
        if (inputs[1].equals("0")) {
            isDone = false;
        } else if (inputs[1].equals("1")) {
            isDone = true;
        } else {
            throw new PepeException("Invalid event format. Expected 1 or 0 for second argument but got " + inputs[1]);
        }

        if (inputs[2].isEmpty()) {
            throw new PepeException("Empty todo name.");
        }

        return new Todo(inputs[2], isDone);
    }

    /**
     * Serializes the Todo class into a string suitable for saving to the file.
     * @return the serialized string for file saving.
     */
    @Override
    public String toFileInput() {
        return String.format("T | %s | %s", this.getStatusFileIcon(), this.getName());
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public int compareTo(Task o) {
        if (o instanceof Event || o instanceof Deadline) {
            // Todo has no deadline and is always less than these 2 tasks
            return -1;
        } else {
            // Maintain original ordering with other Todo
            return 0;
        }
    }
}
