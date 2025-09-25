package tarawrr;

/**
 * ListCommand Class - Represents a command for listing all my tasks
 */
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.showTaskList(tasks);
    }
}