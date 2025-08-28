public class Todo extends Task {
    private static final String TASKTYPE = "T";

    public Todo(String description) {
        super(description);
    }

    @Override
    public String exportString() {
        return String.format(
            "%s | %s",
            Todo.TASKTYPE,
            super.exportString()
        );
    }

    @Override
    public String toString() {
        return String.format(
            "[%s]%s",
            Todo.TASKTYPE,
            super.toString()
        );
    }
}
