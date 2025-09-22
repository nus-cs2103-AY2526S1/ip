package broccoli.Command;

// Command.java

import broccoli.Storage;
import broccoli.TaskList;
import broccoli.Ui;

public abstract class Command {
    public abstract String execute(TaskList taskList, Ui ui, Storage storage);
}
