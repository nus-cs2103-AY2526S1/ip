package jimbot.command;

import java.time.LocalDate;
import java.util.List;

import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

public class FindDateCommand implements Command {
    private final String userInput;

    public FindDateCommand(String input) {
        this.userInput = input;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        LocalDate date = LocalDate.now();

        if (!userInput.equals("today")) {
            date = Parser.parseDate(userInput);
        }

        List<Task> tasks = userList.findTasksAtDate(date).getTaskList();

        return user.printListAtDate(tasks,
                date.isEqual(LocalDate.now()) || userInput.equals("today"));
    }
}
