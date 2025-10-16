package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

/***
 * abstract class Command
 * 2 methods, run to execute and isExit to quit the programme
 */
public abstract class Command {
    public abstract void run(ArrayList<Task> tasks, Ui ui, Storage storage);

    public boolean isExit() {
        return false;
    } // only overridden by Junny.Command.ByeCommand
}
