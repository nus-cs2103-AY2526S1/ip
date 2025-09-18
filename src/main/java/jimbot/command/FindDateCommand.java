package jimbot.command;

import java.time.LocalDate;
import java.util.List;

import jimbot.exception.EmptyListException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Task;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

/**
 * Represents a command to find tasks scheduled on a specific date.
 * If the user input is "today", tasks for the current date are returned.
 * Otherwise, the input is parsed into a date and tasks on that date are returned.
 *
 * @author limjimin-nus
 */
public class FindDateCommand implements Command {
    private final String userInput;

    /**
     * Constructs a new {@code FindDateCommand}.
     *
     * @param input User input string containing "today" or a date.
     */
    public FindDateCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the find-date command by filtering tasks that occur
     * on the given date.
     *
     * @param userList Current task list of the user.
     * @param userStorage Storage handler for persisting tasks.
     * @param user UI component for formatting responses.
     * @return A formatted string containing tasks that occur on the given date.
     * @throws JimbotException If the date format is invalid.
     */
    @Override
    public String execute(TaskList userList, Storage userStorage, UI user) throws JimbotException {
        if (userList.getTaskList().isEmpty()) {
            if (userInput.equals("today")) {
                throw new EmptyListException("do today");
            } else {
                throw new EmptyListException("do that day");
            }
        }

        LocalDate date = LocalDate.now();

        if (!userInput.equals("today")) {
            date = Parser.parseDate(userInput);
        }

        List<Task> tasks = userList.findTasksAtDate(date).getTaskList();

        return user.printListAtDate(tasks,
                date.isEqual(LocalDate.now()) || userInput.equals("today"));
    }
}
