package zell.task;

/**
 * Represents a ToDo task for the Zell chatbot
 */
public class ToDo extends Task{
    public ToDo(String name) {
        super(name);
    }

    /**
     * Overloads the constructor which we primarily use for creating a ToDo task from the local storage
     *
     * @param name The name of the ToDo task
     * @param isDone Indicates if a ToDo task is done
     */
    public ToDo(String name, boolean isDone) {
        this(name);
        setDone(isDone);
    }

    /**
     * Converts a ToDo task to a string to be stored for local storage.
     *
     * @return The string representation of the ToDo task to be stored.
     */
    @Override
    public String taskToString() {
        return String.format("%s | %b | %s", "T", getDone(), getName());
    }

    /**
     * Overrides the parent's (Task) toString
     *
     * @return The toString of the Todo task
     */
    @Override
    public String toString() {
        return String.format("[%s]%s", "T", super.toString());
    }
}

