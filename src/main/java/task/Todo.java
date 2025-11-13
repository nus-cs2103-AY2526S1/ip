package task;

/**
 * Class handling the internals of subtype task todo
 */
public class Todo extends Task {

    public Todo(String description) {
        super(TaskType.TODO, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toFileString() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }
}
