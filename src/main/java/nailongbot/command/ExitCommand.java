package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Command to exit the application and save tasks.
 */
public class ExitCommand implements Command {
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        storage.saveTasks(tasks.getTasks());
        return Ui.CLOSING;
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
