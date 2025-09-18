package dobby.task;

public class ToDo extends Task {
    protected String by;
    private TaskType type;

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

}
