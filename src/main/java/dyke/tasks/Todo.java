package dyke.tasks;

/**
 * Houses methods for {@code Todo} task.
 */
public class Todo extends Task {
    /**
     * Creates a {@code Todo} task.
     * @param desc Description of task.
     */
    public Todo(String desc) {
        super(desc);
    }

    @Override
    public String encode() {
        return "T | " + super.encode();
    }

    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}

