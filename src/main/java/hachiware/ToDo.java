package hachiware;

/**
 * Represents a simple ToDo task.
 * A ToDo task only has a description without any time requirement.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given description.
     *
     * @param description description of the ToDo task
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Returns the string representation of the ToDo task.
     *
     * @return task string denoted with [T] in front
     *     to represent ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
