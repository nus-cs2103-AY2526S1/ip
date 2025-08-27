public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, Storage storage) {
        ui.showTasks(storage.getTaskList().asList());
    }
}


