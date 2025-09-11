package jimbot.command;

import jimbot.exception.NoSuchTaskException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

public class MarkCommand implements Command {
    private final String userInput;

    public MarkCommand(String input) {
        this.userInput = input;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws NoSuchTaskException {
        int index = Parser.parseIndex(userInput, "mark", userList.getTaskCount());

        Task task = userList.getTask(index);
        task.markAsDone();
        userStorage.update(userList);
        return user.markRes(userList, index);
    }
}
