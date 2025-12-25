package leo;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks task as done in the ArrayList wrapped by the TaskList,
     * which generates the string for that task.
     * Ui prints this string.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert this.index > 0 : "Index must be positive";
        try {
            tasks.markDone(index);
            storage.save(tasks);
            return ui.showMarked(tasks.elem(index));
        } catch (Exception err) {
            return ui.showError(err);
        }
    }
}
