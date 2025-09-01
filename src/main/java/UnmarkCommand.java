public class UnmarkCommand extends Command {
    private final int index;

    UnmarkCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay, I'll unmark that!");
        taskList.get(this.index).setCompleted(false);
        ui.showMessage(taskList.get(this.index).print());
    }
}
