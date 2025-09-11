package jimbot.command;

import jimbot.exception.NoSuchTaskException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

public class HelpCommand implements Command {
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws NoSuchTaskException {
        return user.commandList();
    }
}
