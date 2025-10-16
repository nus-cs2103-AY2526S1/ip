package lebron.task;

import lebron.common.Constants;

/**
 * Represents a task that is a to-do item.
 */
public class ToDo extends Task {
    /**
     *  Constructor for ToDo class
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return Constants.TYPE_PREFIX_T + super.toString();
    }
}
