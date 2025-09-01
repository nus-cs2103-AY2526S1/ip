public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.goodbye();
    }
}
