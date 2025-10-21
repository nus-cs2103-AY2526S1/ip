package geegar.task;

/**
 * Todo type that only contains description
 */
public class Todo extends Task {

    /**
     * Creates a Todo instance based on the description
     *
     * @param description
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Creates a Todo instance based ont he description and sets the file as completed
     *
     * @param description
     * @param isDone
     */
    public Todo(String description, Boolean isDone) {

        super(description, isDone);
    }

    @Override
    public String toSaveString() {

        return "[T]" + super.toString();
    }

    @Override
    public String toString() {

        return "[T]" + super.toString();
    }

}
