package peppa.command;

import peppa.task.TaskList;

/**
 * Finds tasks containing a keyword.
 * The input is expected in the form: "find <keyword>".
 */
public class Find implements Command {
    private final TaskList tasks;
    private final String input;

    /**
     * Constructs a Find command.
     *
     * @param tasks task list to search.
     * @param input raw user input string (will be parsed).
     */
    public Find(TaskList tasks, String input) {
        this.tasks = tasks;
        this.input = input;
    }

    /**
     * Parses the keyword and returns matching tasks. Returns an error message
     * if the input is malformed.
     *
     * @return matching tasks or an error message.
     */
    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'find' requires a search keyword. Usage: find <keyword>";
            }
            String toFind = parts[1].trim();
            return tasks.findTask(toFind);
        } catch (Exception e) {
            return "Error processing 'find' command: " + e.getMessage();
        }
    }
}
