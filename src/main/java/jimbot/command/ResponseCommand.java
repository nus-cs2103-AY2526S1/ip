package jimbot.command;

import jimbot.exception.NoSuchTaskException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

public class ResponseCommand implements Command {
    private final String userInput;

    public ResponseCommand(String userInput) {
        this.userInput = userInput;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws NoSuchTaskException {
        return user.respond(userInput);
    }
}
