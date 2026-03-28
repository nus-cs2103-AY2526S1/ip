package vicky.tasklist;

/**
 * Represents a Todo task, extending the Task class.
 *
 * @author Rachel
 */
public class Todo extends Task {

    /**
     * Constructor for Todo class, initializes the Todo task with a name.
     * @param name The name of the task.
     */
    public Todo(String name) {
        super(name);
    }

    /**
     * Overloaded constructor for Todo class, initializes the Todo task with a name and completion status.
     * @param name The name of the task.
     * @param isDone The completion status of the Todo task.
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Shows the completion status of the task.
     *
     * @return completion status.
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Returns a storage string of the todo in the format:
     * "Todo | {completion status} | {todo name}"
     *
     * @return A storage string describing the todo with its name and completion status.
     */
    @Override
    public String toStorageString() {
        int done = this.isDone ? 0 : 1;
        return String.format("Todo | %d | %s", done, this.name);
    }

    /**
     * Returns a string representation of the deadline in the format:
     * "[T] [{completion status}] {todo name}"
     *
     * @return A string describing the todo with its name and completion status.
     */
    @Override
    public String toString() {
        char p = this.isDone ? 'X' : ' ';
        return String.format("[T] [%c] %s", p, this.name);
    }
}
