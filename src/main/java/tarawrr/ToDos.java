package tarawrr;

/**
 * ToDos Class - Represents a Task with a description
 */
public class ToDos extends Task {
    // no new fields

    public ToDos(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }

    @Override
    public String toStorageString() {
        return String.format("T | %d | %s", super.isDone() ? 1 : 0, super.getDescription());
    }
}
