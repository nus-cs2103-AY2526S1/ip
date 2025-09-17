package bob.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import bob.exception.InvalidInputException;

/**
 * Task must be inherited from.
 */
public abstract class Task {
    private static final String DATETIME_INPUT_FORMAT = "dd/MM/yyyy HHmm";
    private static final String DATETIME_OUTPUT_FORMAT = "dd MMM yyyy HH:mm";

    protected String description;
    private boolean isDone;

    /**
     * Constructs a Task.
     *
     * @param description description for the task.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /**
     * Creates and returns the correct Task subclass object from an input
     * string.
     *
     * @param input input string given. Should contains the task type.
     * @throws InvalidInputException
     */
    public static Task createFromString(String input) throws InvalidInputException {
        if (input.startsWith("todo")) {
            return Todo.parse(input);
        }
        if (input.startsWith("deadline")) {
            return Deadline.parse(input);
        }
        if (input.startsWith("event")) {
            return Event.parse(input);
        }
        throw new InvalidInputException("Invalid task type: " + input + "\nValid types are: todo, deadline, event");
    }

    /**
     * Parses an input string in the format of "dd/MM/yyyy HHmm".
     * <p>
     * Example of a valid input string: "27/08/2025 1000"
     *
     * @param input the date string to parse.
     */
    public static LocalDateTime parseDateTimeString(String input) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
        return LocalDateTime.parse(input, dateFormat);
    }

    /**
     * Formats a LocalDateTime object into a string.
     * <p>
     * Example of the returned string: "27 Aug 2025 10:00"
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATETIME_OUTPUT_FORMAT);
        return dateTime.format(dateFormat);
    }

    /**
     * Formats a LocalDateTime object into the input string.
     * <p>
     * Example of the returned string: "27/08/2025 1000"
     */
    public static String toInputStringDateTime(LocalDateTime dateTime) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(DATETIME_INPUT_FORMAT);
        return dateTime.format(dateFormat);
    }

    /**
     * Converts the task back to how the user input it.
     *
     * @return the string representation of the task
     */
    abstract String toInputString();

    @Override
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), description);
    }

    /**
     * Gets the status icon based on its `isDone`
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Returns whether the task is done.
     *
     * @return true if the task is done, false otherwise
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public Task markDone() {
        this.isDone = true;
        return this;
    }

    /**
     * Marks the task as not done.
     */
    public Task unmarkDone() {
        this.isDone = false;
        return this;
    }

    /**
     * Checks if the task description contains the given keyword.
     *
     * @param keyword the keyword to search for.
     * @return true if the description contains the keyword, false otherwise
     */
    public Boolean containsDescription(String keyword) {
        return description.contains(keyword);
    }
}
