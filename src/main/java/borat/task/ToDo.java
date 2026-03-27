package borat.task;

/**
 * A basic to-do task with only a description.
 */
public class ToDo extends Task {

    /**
     * Creates a new to-do with the given description.
     *
     * @param description task description
     */
    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getType() {
        return "T";
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return super.toString();
    }

}


