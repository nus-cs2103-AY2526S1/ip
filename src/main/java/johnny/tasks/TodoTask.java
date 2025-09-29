package johnny.tasks;

/**
 * A task that does not have any deadline or timing requirement
 */
public class TodoTask extends Task {
    public TodoTask(String name) {
        super(name);
    }

    public TodoTask(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String getStoredString() {
        if (isCompleted)
            return "T|1|" + this.name;
        return "T|0|" + this.name;
    }

    @Override
    public String toString() {
        if (this.isCompleted) {
            return "[T][X] " + super.name;
        } else {
            return "[T][ ] " + super.name;
        }
    }
}
