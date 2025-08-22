package lynx.task;

public class TodoTask extends Task {
    public TodoTask(String name) { super(name, Task.TaskType.TODO); }

    public String testRepresentation() {
        return String.format("%s%s %s", type.getSymbol(), status.getSymbol(), name);
    }

    @Override
    public String toString() {
        return String.format("%s%s %s (id:%d)", type.getSymbol(), status.getSymbol(), name, id);
    }
}