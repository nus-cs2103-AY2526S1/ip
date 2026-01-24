package chathyt.command;

/**
 *  Represents a command to show goodbye message and exit the program.
 */

import java.util.ArrayList;
import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;

public class ByeCommand implements Command {

    @Override
    public String execute(ArrayList<Task> tasks, Storage storage, Ui ui) {
        return Ui.sayGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}