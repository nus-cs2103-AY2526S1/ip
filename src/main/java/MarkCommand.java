public class MarkCommand extends Command {
    private final int index;

    MarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay, I'll mark that one off your list!");
        taskList.get(this.index).setCompleted(true);
        ui.showMessage(taskList.get(this.index).print());
    }
}
