public class TodoTask extends Task {
    public TodoTask(String name) { super(name, TaskType.TODO); }

    @Override
    public String toString() {
        return String.format("%s%s %s (id:%d)", type.getSymbol(), status.getSymbol(), name, id);
    }
}