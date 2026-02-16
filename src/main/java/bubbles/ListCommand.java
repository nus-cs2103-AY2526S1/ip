package bubbles;

/**
 * Represents a command to list all the current tasks
 */
public class ListCommand extends Command{
    private String command;

    /**
     * Constructs a {@code ListCommand}
     * This command searches lists all the current tasks
     *
     * @param command the user input for the keyword
     */
    public ListCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the list command which lists all the current tasks
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks.getAll());
    }
}
