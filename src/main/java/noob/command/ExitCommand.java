package noob.command;

import noob.gui.handler.DisplayHandler;
import noob.storage.Storage;
import noob.task.TaskList;
import noob.ui.Ui;

public class ExitCommand extends Command {

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) {
        return DisplayHandler.EXIT_COMMAND;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof ExitCommand;
    }
}
