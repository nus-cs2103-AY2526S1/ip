package jarvis.commands;

import java.io.IOException;

import jarvis.storage.Storage;
import jarvis.tasks.TaskList;
import jarvis.ui.DarrenAssistantException;
import jarvis.ui.Ui;

public abstract class Command {
    protected boolean isExit = false;
    public boolean isExit() {
        return isExit;
    }

    /*
    * Centralised doing work here
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DarrenAssistantException, IOException;

    public abstract String getString();
}
