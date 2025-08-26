package rafayel.task;

/**
 *  Represents a Todo task which is a subtype of Task.
 *  A Todo task has a description but no date/time.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given description.
     *
     * @param description the description of the Todo task.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String saveTaskName() {
        return "T" + super.saveTaskName();
    }
}
