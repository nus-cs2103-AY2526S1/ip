package stella;

/**
 * Represent a task with the only detail being the task description
 */
public class ToDo extends Task{

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
