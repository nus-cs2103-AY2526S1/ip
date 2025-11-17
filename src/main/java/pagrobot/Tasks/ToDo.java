package pagrobot.tasks;

/**
 * Represents a ToDo task.
 */
public class ToDo extends Task {

    /**
     * Creates a new ToDo task with the given name, not marked as done.
     *
     * @param name Name of the task.
     */
    public ToDo(String name) {
        this(name, false);
    }

    /**
     * Creates a new ToDo task.
     *
     * @param name Name of the task.
     * @param isDone Whether the task is completed.
     */
    protected ToDo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the string representation of this ToDo task.
     *
     * @return String format of ToDo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Creates a ToDo task from a memory string.
     *
     * @param input Encoded string from save file.
     * @return A ToDo task object.
     */
    public static Task fromMemory(String input) {
        String[] parts = input.split("\\|");
        return new ToDo(parts[2], parts[1].equals("1"));
    }

    /**
     * Returns the encoded string representation of this ToDo task for storage.
     *
     * @return Encoded string.
     */
    @Override
    public String toMemory() {
        return String.format("T|%d|%s", super.isDone() ? 1 : 0, super.getName());
    }

    public static String inputArgument() {
        return "todo <task name>";
    }
}
