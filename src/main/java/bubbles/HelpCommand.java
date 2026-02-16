package bubbles;

public class HelpCommand extends Command{
    private String command;

    public HelpCommand(String command) {
        super(command);
        this.command = command;
    }

    /**
     * Executes the help command and displays an help message.
     *
     * @param tasks List of all the tasks.
     * @param ui The UI.
     * @param storage The list of tasks stored in the hard disk.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showHelp();
    }
}
