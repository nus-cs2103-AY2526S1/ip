package jinwoo.tasks;

import jinwoo.exception.DukeException;

/**
 * A Tasks.Todo task is a simple task without any date or time constraints.
 */
public class Todo extends Task {
    /**
     * Constructs a Tasks.Todo with the given description.
     *
     * @throws DukeException if the description is empty.
     */
    public Todo(String description) throws DukeException {
        super(description);
        if (description.isBlank()) {
            throw new DukeException("Warning ??? : the description of a todo cannot be empty.");
        }
    }

    /**
     * Creates and returns a copy for different history states.
     * Refer to copilot suggestion.
     */
    @Override
    public Todo copyTask() throws DukeException {
        Todo copy = new Todo(this.description);
        if (this.isDone) {
            copy.mark();
        }
        return copy;
    }

    @Override
    public String toString() {
        return " [T]" + super.toString();
    }
}
