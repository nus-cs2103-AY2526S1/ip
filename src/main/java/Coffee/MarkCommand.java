package Coffee;

/**
 * Represents a command to mark a task as done in the task list.
 */
public class MarkCommand extends Command {
    private final int index1Based;

    /**
     * Constructs a {@code MarkCommand} with the given task index.
     *
     * @param args User input string containing the index of the task to mark.
     * @throws IllegalArgumentException If the argument is empty, not numeric, or not positive.
     */
    public MarkCommand(String args) {
        if (args == null || args.trim().isEmpty()) {
            throw new IllegalArgumentException("The mark command format is wrong. Use: mark <index>");
        }
        try {
            this.index1Based = Integer.parseInt(args.trim());
            if (index1Based <= 0) {
                throw new IllegalArgumentException("Index must be a positive integer.");
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Index must be a positive integer.");
        }
    }

    /**
     * Executes the command by marking the specified task as done.
     * Saves the updated list to storage and displays confirmation messages to the user.
     *
     * @param tasks Task list containing the task to be marked.
     * @param ui User interface for displaying messages.
     * @param storage Storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (index1Based > tasks.size()) {
            ui.showMessage("Invalid task index: " + index1Based);
            return;
        }
        tasks.markAsDone(index1Based);
        storage.save(tasks.view());
        ui.showMessage("marked task " + index1Based);
    }
}
