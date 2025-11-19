package Coffee;

/**
 * Represents a command to add a deadline task to the task list.
 * The command parses the user input for the description and due date/time,
 * creates a {@code Deadline} task, saves it, and displays confirmation.
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final String by;

    /**
     * Constructs a {@code DeadlineCommand} by parsing the given arguments.
     * The arguments are split into description and due date/time using the delimiter {@code /by}.
     *
     * @param args User input string containing the description and due date/time.
     */
    public DeadlineCommand(String args) {
        String[] parts = args.split("/by", 2);
        this.description = parts[0].trim();
        this.by = parts.length > 1 ? parts[1].trim() : "";
    }

    /**
     * Executes the command by creating a {@code Deadline} task and adding it to the task list.
     * Saves the updated list to storage and displays confirmation messages to the user.
     *
     * @param tasks Task list where the new deadline will be added.
     * @param ui User interface for displaying messages.
     * @param storage Storage for saving the updated task list.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Try to create the Deadline (will throw if format is wrong)
            CommandUtil.addDeadlineSaveAndAck(description, by, tasks, ui, storage);
        } catch (Exception e) {
            ui.showMessage("Error: Pls enter the date in the format yyyy-MM-dd HHmm (e.g., 2024-06-10 1800).");
        }
    }
}
