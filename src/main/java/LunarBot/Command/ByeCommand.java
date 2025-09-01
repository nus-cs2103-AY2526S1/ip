package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public class ByeCommand extends Command {
    @Override
    public void execute(Ui ui, TaskList taskList) {
        ui.goodbye();
    }
}
