package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class ByeCommand extends Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        return ui.goodbye();
    }
}
