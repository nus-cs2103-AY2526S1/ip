package jimbot.command;

import java.time.LocalDateTime;

import jimbot.exception.InvalidDeadlineException;
import jimbot.exception.JimbotException;
import jimbot.storage.Storage;
import jimbot.tasktype.Deadline;
import jimbot.tasktype.TaskList;
import jimbot.ui.UI;
import jimbot.util.Parser;

/**
 * Represents a command that adds a deadline task to the task list.
 * A deadline task has a description and a due date/time, specified
 * with the {@code /by} keyword in the user input.
 *
 * Example input: {@code deadline submit report /by 2025-09-15 18:00}
 *
 * @author limjimin-nus
 */
public class AddDeadlineCommand implements Command {
    private final String userInput;

    /**
     * Constructs an AddDeadlineCommand with the specified user input.
     *
     * @param input The raw user string input.
     */
    public AddDeadlineCommand(String input) {
        this.userInput = input;
    }

    /**
     * Executes the command by creating and adding a new deadline task.
     * The task is stored in the task list and persisted to storage.
     *
     * @param userList Task list to which the new deadline is added.
     * @param userStorage Storage manager to update with the new task.
     * @param user UI manager to generate the response message.
     * @return Response message confirming the addition of the deadline task.
     * @throws JimbotException If the input is missing {@code /by},
     *                         has empty description or date,
     *                         or contains an invalid date/time.
     */
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

        return user.addTask(userDeadline, userList.getTaskCount());
    }
}
