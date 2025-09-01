package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int i) {
        this.index = i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay, I'll delete this one from your list!");
        ui.showMessage(taskList.get(this.index).print());
        taskList.delete(this.index);
    }
}
