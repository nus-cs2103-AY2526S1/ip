package burgerburglar;

/**
 * Represents a command to delete a task from the task list.
 * The task to be deleted is specified by its index in the list.
 */
public class DeleteCommand extends Command {
    private final String args;

    /**
     * Constructs a DeleteCommand with the specified argument.
     *
     * @param args the argument specifying the task index to delete; must not be null or blank
     * @throws AssertionError if the args is null or blank
     */
    public DeleteCommand(String args) {
        assert args != null && !args.isBlank() : "Arguments for DeleteCommand cannot be null or blank";
        this.args = args;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BurgerException {
        assert tasks != null : "TaskList cannot be null in execute";
        assert storage != null : "Storage cannot be null in execute";

        try {
            int index = Integer.parseInt(args.trim());
            Task removed = tasks.deleteTask(index);
            storage.save(tasks);
            return formatDeleteMessage(removed, tasks.size());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new BurgerException("BURGER ERROR: Invalid task number for delete.");
        }
    }

    /**
     * Formats the delete confirmation message for the user.
     *
     * @param removedTask The task that was removed
     * @param taskCount   The current number of tasks remaining
     * @return A formatted string message
     */
    private String formatDeleteMessage(Task removedTask, int taskCount) {
        return String.format(
                "BURGER HAS REMOVED THIS TASK:\n  %s\nNOW YOU HAVE %d TASK(S) IN THE LIST.\n",
                removedTask, taskCount
        );
    }
}
