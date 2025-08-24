package lynx.task;

/**
 * Represents a basic task with a <code>TaskType</code>, <code>Status</code>, name and id for tracking.
 * <p>
 * <code>Status</code> is <code>INCOMPLETE</code> by default, and id is assigned by the constructor.
 */
public class TodoTask extends Task {

    public TodoTask(String name) {
        super(name, TaskType.TODO);
    }

}