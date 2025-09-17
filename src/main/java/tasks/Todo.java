package tasks;

import app.Messages;
import errors.BoopError;

/**
 * Represents a Todo task with just a name and completion status.
 */
public class Todo extends Task {

    /**
     * Creates a Todo with a specified completion status.
     *
     * @param name       Name of the todo task
     * @param isComplete Whether the todo task is marked as complete
     */
    public Todo(String name, boolean isComplete) {
        super(name, isComplete);
    }

    /**
     * Creates an incomplete Todo.
     *
     * @param name Name of the todo task
     */
    public Todo(String name) {
        this(name, false);
    }

    @Override
    public String toString() {
        return "[T]%s".formatted(super.toString());
    }

    @Override
    public String toSaveString() {
        return "T | %s".formatted(super.toSaveString());
    }

    @Override
    public Todo copy() {
        return new Todo(getName(), isComplete());
    }

    /**
     * Converts a Save String format of a Todo back into a Todo instance
     *
     * @param saveString Todo in format written in save file
     * @return Todo using data from save file
     * @throws BoopError
     */
    public static Todo fromSaveString(String saveString) throws BoopError {
        String[] parts = saveString.split(" \\| ");
        String type = parts[0];

        if (!type.equals("T")) {
            throw new BoopError(String.format(Messages.ERROR_WRONG_TYPE_TASKSAVESTRING, "Todo", type));
        }

        if (parts.length < 3) {
            throw new BoopError(Messages.ERROR_SAVE_CORRUPTED);
        }

        boolean isDone = parts[1].equals("X");
        String name = parts[2];
        assert name != null && !name.isEmpty() : "Task name must not be null or empty";

        return new Todo(name, isDone);
    }
}
