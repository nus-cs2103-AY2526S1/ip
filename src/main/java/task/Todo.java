package task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public void complete() {
        isDone = true;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
