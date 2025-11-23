package burh;

/**
 * Represents a todo task with a description.
 */
public class Todo extends Task {

    /**
     * Creates an todo task with the given description, start date and end date.
     *
     * @param task Description of the todo task.
     */
    public Todo(String task) {
        super(task);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String getSaveString() {
        return "T|" + super.getSaveString() + super.getDescription();
    }

    @Override
    public int compareTo(Task t) {
        if (t instanceof Todo) {
            return -1;
        } else if (t instanceof Deadline) {
            return 1;
        } else {
            assert t instanceof Event;
            return 1;
        }
    }
}
