package LunarBot.Command;

import LunarBot.TaskList;
import LunarBot.Ui;

public abstract class Command {
    /**
     * Executes the command
     *
     * @param ui ui that command is being executed on
     * @param taskList list of tasks to perform the task on
     */
    public abstract String execute(Ui ui, TaskList taskList);
}
