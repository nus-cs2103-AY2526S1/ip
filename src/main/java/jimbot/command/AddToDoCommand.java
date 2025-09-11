package jimbot.command;

import jimbot.exception.InvalidToDoException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.TaskList;
import jimbot.tasktype.ToDo;
import jimbot.ui.UI;

public class AddToDoCommand implements Command {
    private final String userInput;

    public AddToDoCommand(String input) {
        this.userInput = input;
    }


    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        String description = userInput.substring(4).trim();

        if (description.isEmpty()) {
            throw new InvalidToDoException();
        } else {
            ToDo userToDo = new ToDo(description);
            userList.addToList(userToDo);
            userStorage.update(userList);

            return user.addTask(userToDo, userList.getTaskCount() + 1);
        }
    }
}
