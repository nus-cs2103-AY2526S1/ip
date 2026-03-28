package chlo.task;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
        this.raw = String.format("todo %s", description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
