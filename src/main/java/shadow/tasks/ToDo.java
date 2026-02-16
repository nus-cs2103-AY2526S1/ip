package shadow.tasks;

/**
 * Represents a ToDo task, a specific type of task that only includes
 * a description without any associated time constraints.
 * <p>
 * A ToDo task is a subclass of {@link Task}. It inherits the ability to
 * mark and unmark tasks, as well as the ability to check if its name contains
 * specified substrings. Additionally, it provides a string representation
 * that distinguishes it as a ToDo task.
 */
public class ToDo extends Task {
    public static final String ERROR_MESSAGE = "Usage: todo <taskName>";

    /**
     * Constructs a new ToDo task with the specified name.
     *
     * @param name the name or description of the ToDo task
     */
    public ToDo(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    /**
     * Creates a new {@link ToDo} task from the given input string.
     * <p>
     * Trims the input and constructs a {@code ToDo} object using it as the task name.
     * If the input is {@code null}, an {@link IllegalArgumentException} is thrown to indicate incorrect usage.
     * Upon successful creation, the task is printed to the console.
     * </p>
     *
     * @param input the raw user input containing the task description
     * @return a new {@code ToDo} instance representing the task
     * @throws IllegalArgumentException if the input is {@code null}
     */
    public static ToDo of(String input) {
        if (input == null) {
            throw new IllegalArgumentException(ToDo.ERROR_MESSAGE);
        }
        return new ToDo(input.trim());
    }

}
