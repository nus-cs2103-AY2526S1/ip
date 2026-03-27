package tasks;

import java.util.Objects;

import amogus.AmogusException;

/**
 * Represents a ToDo object.
 */
public class ToDo extends Task {
    /**
     * Creates the Tasks.ToDo object.
     *
     * @param description description of Tasks.ToDo task
     * @throws AmogusException insufficient information to create task
     */
    public ToDo(String description) throws AmogusException {
        super(description);
        if (Objects.equals(description, "")) {
            throw new AmogusException("Oh no! Please provide full information regarding your todo!");
        }
    }

    /**
     * Returns the type of task.
     * @return todo type of task.
     */
    @Override
    public String getType() {
        return "T";
    }

    /**
     * @return string representation of ToDo task
     */
    @Override
    public String toString() {
        return super.toString() + this.getTag();
    }
}
