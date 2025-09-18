package alune.tasks;

/**
 * This class inherits from Task for basic tasks.
 * 
 * @author nghnaomi
 */
public class ToDoTask extends Task {
    public ToDoTask(String name) {
        super(name);
    }

    public ToDoTask(Task other) {
        super(other);
    }

    @Override
    public Task cloneTask() {
        return new ToDoTask(this);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
