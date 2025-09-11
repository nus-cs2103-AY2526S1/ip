package jimbot.command;

import java.time.LocalDateTime;

import jimbot.exception.InvalidDeadlineException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Deadline;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

public class AddDeadlineCommand implements Command {
    private final String userInput;

    public AddDeadlineCommand(String input) {
        this.userInput = input;
    }

    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (!userInput.contains("/by")) {
            throw new InvalidDeadlineException();
        }

        String[] deadline = userInput.substring(9)
                .trim()
                .split("/by", 2);
        String description = deadline[0].trim();
        String by = deadline[1].trim();

        if (by.isEmpty() || description.isEmpty()) {
            throw new InvalidDeadlineException();
        }

        LocalDateTime dateTime = Parser.parseDateTime(by);
        Deadline userDeadline = new Deadline(description, dateTime);
        userList.addToList(userDeadline);
        userStorage.update(userList);

        return user.addTask(userDeadline, userList.getTaskCount() + 1);
    }
}
