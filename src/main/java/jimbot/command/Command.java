package jimbot.command;

import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

/**
 * Represents an executable command in Jimbot.
 * Each concrete command class implements this interface to define its behavior.
 */
public interface Command {
    String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException;
}
