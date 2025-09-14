package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public abstract class Command {
    public abstract String execute(Ui ui, TaskList taskList);
}
