public class ToDo extends Task{
    protected String by;

    public ToDo(String description) {
        super(description, TaskType.TODO);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
