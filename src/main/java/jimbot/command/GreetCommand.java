package jimbot.command;

import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;

public class GreetCommand implements Command {
    private final String name;

    public GreetCommand(String name) {
        this.name = name;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        return user.greet(name);
    }
}
