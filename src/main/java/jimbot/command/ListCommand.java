
package jimbot.command;

import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

public class ListCommand implements Command {
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        return user.printList(userList.getTaskList());
    }
}