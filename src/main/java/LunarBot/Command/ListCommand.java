package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class ListCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.showMessage("Printing tasks!");
        for (int i = 1; i < taskList.size() + 1; i++) {
            ui.showMessage(i + ": " + taskList.get(i-1).print());
        }
    }
}
