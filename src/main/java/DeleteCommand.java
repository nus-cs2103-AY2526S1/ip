public class DeleteCommand extends Command {
    private final int index;

    DeleteCommand(int i) {
        this.index = i;
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay, I'll delete this one from your list!");
        ui.showMessage(taskList.get(this.index).print());
        taskList.delete(this.index);
    }
}
