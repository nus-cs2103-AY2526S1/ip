package task;

/**
 * Represents a task that can be done, with no other pertinent characteristics.
 * It solely inherits from {@link Task} with no additional variables or methods.
 */
public class ToDo extends Task {

    /**
     * Constructs a {@link ToDo} task with the given description.
     *
     * @param description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

