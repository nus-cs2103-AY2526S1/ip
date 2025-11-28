package nailongbot.command;

import nailongbot.exception.JkBotException;
import nailongbot.utils.Storage;
import nailongbot.utils.TaskList;
import nailongbot.utils.Ui;


/**
 * Command to show tasks on a specific date.
 */
public class ShowCommand implements Command {
    private String dateString;

    public ShowCommand(String dateString) {
        this.dateString = dateString;
    }

    public String getDateString() {
        return dateString;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws JkBotException {
        String result = tasks.getTasksOnDate(dateString);
        return Ui.LINE + result + Ui.LINE;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
