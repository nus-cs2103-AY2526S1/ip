package peppa.command;

import peppa.task.TaskList;
import peppa.storage.Storage;

/**
 * Adds a todo task.
 * The input is expected in the form: "todo <description>".
 */
public class Todo implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String input;

    /**
     * Constructs a Todo command.
     *
     * @param tasks   task list to operate on.
     * @param storage storage used to persist changes.
     * @param input   raw user input string (will be parsed).
     */
    public Todo(TaskList tasks, Storage storage, String input) {
        this.tasks = tasks;
        this.storage = storage;
        this.input = input;
    }

    /**
     * Parses the description and adds a todo. Returns an error message if the
     * input is malformed.
     *
     * @return result or error message for the user.
     */
    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'todo' requires a description. Usage: todo <description>";
            }
            String result = tasks.addTask(input);
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return "Error processing 'todo' command: " + e.getMessage();
        }
    }
}
