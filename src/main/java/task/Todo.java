package task;

/**
 * Represents a Todo task in the Baymax application.
 * A Todo is a type of Task that has no associated date or time.
 * Inherits common task behavior from the {@link Task} class.
 */
public class Todo extends Task {

    /**
     * Constructs a new Todo task with the given name and type.
     *
     * @param taskName the name/description of the Todo task
     * @param taskType the type of the task (should be {@link TaskType#TODO})
     */
    public Todo(String taskName, TaskType taskType) {
        super(taskName, taskType);
    }

    /**
     * Constructs a new Todo task with the given name, type, and completion status.
     *
     * @param taskName the name/description of the Todo task
     * @param taskType the type of the task (should be {@link TaskType#TODO})
     * @param isDone   whether the task is marked as completed
     */
    public Todo(String taskName, TaskType taskType, boolean isDone) {
        super(taskName, taskType, isDone);
    }

    /**
     * Returns a string representation of the Todo task.
     *
     * @return a string in the format "[T][status] taskName"
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
