package minhgpt.task;

import java.text.ParseException;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;

/**
 * Base class for a task.
 */
public abstract class Task {
    // NOTE: PRIVATE

    /** Map task regex String to constructor of the corresponding task. */
    private static final HashMap<String, Function<String, Task>> registry = new HashMap<>();
    /** Name of task. */
    private String name;
    /** If status is true, task is done. Otherwise, task is not done. */
    private boolean status;

    // NOTE: PROTECTED

    /** Date format when tasks are saved to disk. */
    protected static final DateTimeFormatter DATE_SAVE_FORMAT =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");
    /** Date format when tasks are displayed. */
    protected static final DateTimeFormatter DATE_OUTPUT_FORMAT =
            DateTimeFormatter.ofPattern("MMM dd yyyy");

    /**
     * Put mapping into 'registry'.
     *
     * @param regex Regex that the task matches.
     * @param supplier Constructor for the task.
     */
    protected static void register(String regex, Function<String, Task> supplier) {
        registry.put(regex, supplier);
    }

    // NOTE: PUBLIC

    /**
     * Initialise the mapping in 'registry'.
     */
    public static void initialise() {
        new TaskTodo("todo task1");
        new TaskDeadline("deadline task1 /by 1970-01-01");
        new TaskEvent("event task1 /from 1970-01-01 /to 1970-01-01");
    }

    /**
     * Factory method for creating a task.
     *
     * @param input Input from user to create a task.
     * @throws ParseException When the input string does not match any known input patterns.
     */
    public static Task parseTask(String input) throws ParseException {
        try {
            for (String regex : registry.keySet()) {
                if (input.matches(regex)) {
                    return registry.get(regex).apply(input);
                }
            }
        } catch (DateTimeParseException e) {
            throw new ParseException("( ˶°ㅁ°) Please input date in the format: yyyy-mm-dd", 0);
        }

        // If control reaches here, there is no matching task type
        throw new ParseException("( ˶°ㅁ°) That is not a valid way to input a task!", 0);
    }

    /**
     * Construct a basic task.
     * 
     * @param name Name of task to be created.
     */
    public Task(String name) {
        this.name = name;
        this.status = false;
    }

    /**
     * Mark the task as done.
     */
    public void markAsDone() {
        this.status = true;
    }

    /**
     * Mark the task as undone.
     */
    public void markAsUndone() {
        this.status = false;
    }

    /**
     * Return true if the task name matches 'query'. False otherwise.
     */
    public boolean match(String query) {
        return name.toUpperCase().contains(query.toUpperCase());
    }

    /**
     * @return Input commands to re-create this task.
     */
    public ArrayList<String> toCommands() {
        ArrayList<String> commands = new ArrayList<>();
        commands.add(name);
        if (status) {
            commands.add("mark");
        }
        return commands;
    }

    /**
     * @return Simple String representation of a task.
     */
    @Override
    public String toString() {
        return String.format("[%c] %s", this.status ? 'X' : ' ', this.name);
    }
}
