package shaniqua.taskcore.tasks;

public class Todo extends Task {
    /**
     * Constructs new Todo object
     * @param name of task
     * @throws InvalidTaskDataException
     */
    public Todo(String name) throws InvalidTaskDataException {
        super(name);
    }

    /**
     * returns String representation of task.
     * @return String representation of task
     */
    public String toString() {
        return "[T] " + super.toString();
    }
}
