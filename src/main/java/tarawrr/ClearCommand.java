package tarawrr;

public class ClearCommand extends Command{
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TarawrrException {
        tasks.clear();
        storage.save(tasks);
        return ui.showClearMessage();
    }
}
