package izayoi.task;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

import izayoi.Commandifiable;
import izayoi.exception.EmptyArgumentException;
import izayoi.exception.InvalidFormatException;
import izayoi.exception.IzayoiException;
import izayoi.input.InputReader;
import izayoi.input.TaskDescriptor;

/**
 * Represents a task to be completed by the user
 */
public abstract class Task implements Commandifiable, Actionable {
    public static final DateTimeFormatter DATETIME_FORMAT = DateTimeFormatter.ofPattern("MMM d yyyy");
    private boolean isCompleted = false;
    private final Map<String, String> arguments;
    private final String message;

    /**
     * Initializes a new uncompleted task
     *
     * @param input the InputManager reading the task description
     * @throws IzayoiException if the input is invalid
     */
    public Task(TaskDescriptor input) throws IzayoiException {
        this.arguments = input.getTask();
        this.message = arguments.get("message");
        if (this.message.trim().isBlank()) {
            throw new EmptyArgumentException("Did you forget to tell me the task you wanted to do?");
        }
    }

    @Override
    public boolean isCompleted() {
        return this.isCompleted;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public void markAsDone() {
        this.isCompleted = true;
    }

    @Override
    public void markAsNotDone() {
        this.isCompleted = false;
    }

    /**
     * Reads an argument value
     *
     * @param name the argument name
     * @return the value of the argument
     */
    protected String getArgument(String name) {
        String m = arguments.get(name);
        return m == null ? "unspecified" : m.trim();
    }

    /**
     * Factory method to create tasks based on specified type
     *
     * @param input the InputManager reading the task description
     * @return the created task
     * @throws IzayoiException if the task creation fails
     */
    public static Task createTask(InputReader input) throws IzayoiException {
        try {
            return switch (input.getCommandType()) {
            case TODO -> new ToDo(input);
            case DEADLINE -> new Deadline(input);
            case EVENT -> new Event(input);
            case TIMED -> new Timed(input);
            default -> throw new InvalidFormatException("Completely incoherent task description.");
            };
        } catch (DateTimeParseException e) {
            throw new InvalidFormatException("Incomprehensible date description.");
        }
    }

    /**
     * Returns the type of this task as a String
     * @return a single letter representing the type of task
     */
    public abstract String getType();

    @Override
    public String toString() {
        return String.format("[%s][%s] %s", getType(), isCompleted ? "X" : " ", message);
    }
}
