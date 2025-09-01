package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class MarkCommand extends Command {
    private final int index;

    public MarkCommand(int i) {
        this.index = i;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Okay, I'll mark that one off your list!");
        taskList.get(this.index).setCompleted(true);
        ui.showMessage(taskList.get(this.index).print());
    }
}
