package benn.commands;

import benn.tasks.TaskManager;
import benn.exceptions.BennException;
import benn.messages.MessageManager;
import benn.patterns.InputPattern;
import benn.tasks.Task;

import java.io.IOException;
import java.util.regex.Matcher;

/**
 * Represents a command that deletes a {@link benn.tasks.Task} from the
 * {@link benn.tasks.TaskManager}.
 *
 * <p>This command is created when the user input matches the
 * {@link benn.patterns.InputPattern#DELETE_TASK} regex. It parses the
 * index of the task to be deleted, removes the corresponding task from
 * the TaskManager, and returns a formatted confirmation message.</p>
 */
public class DeleteCommand extends Command {

    /**
     * Constructs a new {@code DeleteCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public DeleteCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the command by parsing the index of the task to delete,
     * removing that {@link benn.tasks.Task} from the given
     * {@link benn.tasks.TaskManager}, and returning a confirmation message.
     *
     * <p>If parsing fails, the index is invalid, or the task cannot be
     * removed, an error message is returned instead.</p>
     *
     * @param taskManager the {@link benn.tasks.TaskManager} managing the current list of tasks
     * @return a user-facing message indicating success or error
     */
    @Override
    public String execute(TaskManager taskManager) {
        Matcher matcher = InputPattern.DELETE_TASK.matcher(input);
        try {
            if (matcher.find()) {
                int index = Integer.parseInt(matcher.group("index"));
                Task task = taskManager.deleteTaskAt(index);
                return MessageManager.retrieveDeletedTaskMessageFrom(task, taskManager);
            } else {
                throw new BennException("Parsing error occurred");
            }
        } catch (NumberFormatException | BennException | IOException exception){
            return MessageManager.retrieveErrorMessageFrom(exception);
        }
    }
}
