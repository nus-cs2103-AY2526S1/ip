package chathyt.command;

import java.util.ArrayList;
import chathyt.task.Task;
import chathyt.storage.Storage;
import chathyt.ui.Ui;

public interface Command {
    String execute(ArrayList<Task> tasks, Storage storage, Ui ui);
    boolean isExit();
}
