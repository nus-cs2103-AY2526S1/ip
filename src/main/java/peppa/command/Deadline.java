package peppa.command;

import peppa.task.TaskList;
import peppa.storage.Storage;

/**
 * Adds a deadline task.
 * The input is expected in the form: "deadline <description> /by <time>".
 */
public class Deadline implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String input;

    /**
     * Constructs a Deadline command.
     *
     * @param tasks   task list to operate on.
     * @param storage storage used to persist changes.
     * @param input   raw user input string (will be parsed).
     */
    public Deadline(TaskList tasks, Storage storage, String input) {
        this.tasks = tasks;
        this.storage = storage;
        this.input = input;
    }

    /**
     * Parses the description and '/by' time and adds the deadline. Returns an
     * error message if the input is malformed.
     *
     * @return result or error message for the user.
     */
    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'deadline' requires a description and '/by'. Usage: deadline <description> /by <time>";
            }
            String result = tasks.addTask(input);
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return "Error processing 'deadline' command: " + e.getMessage();
        }
    }
}
