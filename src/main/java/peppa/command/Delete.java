package peppa.command;

import peppa.task.TaskList;

/**
 * Deletes a task by its one-based index.
 * The input is expected in the form: "delete <task_number>".
 */
public class Delete implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final String input;

    /**
     * Constructs a Delete command.
     *
     * @param tasks   task list to operate on.
     * @param storage storage used to persist changes.
     * @param input   raw user input string (will be parsed).
     */
    public Delete(TaskList tasks, Storage storage, String input) {
        this.tasks = tasks;
        this.storage = storage;
        this.input = input;
    }

    /**
     * Parses the index from the input, validates it, deletes the task and
     * saves the updated list. Returns an error message if the input is malformed.
     *
     * @return result or error message for the user.
     */
    @Override
    public String execute() {
        try {
            String[] parts = input.trim().split("\\s+", 2);
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                return "Error: 'delete' requires an index. Usage: delete <task_number>";
            }
            int idx;
            try {
                idx = Integer.parseInt(parts[1].trim()) - 1;
            } catch (NumberFormatException e) {
                return "Error: invalid index for 'delete'. Please provide a number.";
            }
            if (idx < 0) {
                return "Error: task index must be positive.";
            }
            String result = tasks.deleteTask(idx);
            storage.save(tasks);
            return result;
        } catch (Exception e) {
            return "Error processing 'delete' command: " + e.getMessage();
        }
    }
}
