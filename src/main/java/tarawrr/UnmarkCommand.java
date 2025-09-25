package tarawrr;

/**
 * UnmarkCommand Class - Represents a command for marking a task as incomplete.
 */
public class UnmarkCommand extends Command {
    private int index;

    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TarawrrException {
        tasks.unmarkTask(this.index);
        try {
            storage.save(tasks);
        } catch (TarawrrException e) {
            throw new RuntimeException(e);
        }
        return ui.showUnmarkedTask(tasks.getTasks().get(this.index - 1));
    }
}
