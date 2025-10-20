package yap.task;

/**
 * Represents a ToDo task.
 */
public class ToDos extends Task {

    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]" + super.toString());
    }
}
