package mon.task;

/**
 * Represents a simple task without any specific date or time.
 */
public class Todo extends Task {
    /**
     * Creates a new simple task with the specified name and status.
     * 
     * @param taskName the name of the task
     * @param status the completion status of the task
     */
    public Todo(String taskName, boolean status) {
        super(taskName, status);
    }

    /**
     * Creates a new incomplete simple task with the specified name.
     * 
     * @param taskName the name of the task
     */
    public Todo(String taskName) {
        this(taskName, false);
    }

    /**
     * Creates a simple task from a string representation.
     * 
     * @param taskString the string representation of the task
     * @return the simple task object
     */
    public static Todo toTodoTask(String taskString) {
        String[] parts = taskString.split(" \\| ");
        return new Todo(parts[2], parts[1].equals("1"));
    }

    @Override
    public String toFileString() {
        return "T | " + super.toFileString();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
