package jimbot.command;

import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

public class DeleteCommand implements Command {
    private final String userInput;

    public DeleteCommand(String input) {
        this.userInput = input;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        int taskCount = userList.getTaskCount();
        int index = Parser.parseIndex(userInput, "delete", taskCount);

        Task task = userList.getTask(index);
        userList.deleteFromList(userList.getTask(index));
        userStorage.update(userList);

        return user.deleteTask(task, taskCount - 1);
    }
}
