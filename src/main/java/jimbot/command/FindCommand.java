package jimbot.command;

import java.util.List;

import jimbot.exception.NoSuchTaskException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

public class FindCommand implements Command {
    private final String userInput;

    public FindCommand(String input) {
        this.userInput = input;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws NoSuchTaskException {
        String description = userInput.toLowerCase()
                .substring(4)
                .trim();
        List<Task> tasks = userList.findTasks(description).getTaskList();

        return user.printList(tasks);
    }
}
