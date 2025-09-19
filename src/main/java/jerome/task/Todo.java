package jerome.task;

import jerome.JeromeException;

/**
 * Represents a Todo task in the system.
 * A <code>Todo</code>  object holds details like description and whether it is completed.
 */
public class Todo extends Task {
    /**
     * Constructs a new <code>Task</code> with description and the status for whether it is done set to false.
     *
     * @param description Description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Constructs a new <code>Task</code> with description and the status for whether it is done.
     *
     * @param description Description of the Todo task.
     * @param isDone Whether this Todo task is done.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Provides string representation to save in .txt file
     * @return corresponding string representation for a Todo task
     */
    @Override
    public String toSaveFormat() {
        return "T | " + (this.isDone ? "1" : "0") + " | " + this.description;
    }

    @Override
    public void adjustDate(String dates) throws JeromeException {
        throw new JeromeException("Input error: Todo task does not have a date");
    }
}
