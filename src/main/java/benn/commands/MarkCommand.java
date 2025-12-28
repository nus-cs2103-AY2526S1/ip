package benn.commands;

import benn.tasks.TaskManager;
import benn.exceptions.BennException;
import benn.messages.MessageManager;
import benn.patterns.InputPattern;
import benn.tasks.Task;

import java.io.IOException;
import java.util.regex.Matcher;

/**
 * Represents a command that marks a {@link benn.tasks.Task} as completed
 * in the {@link benn.tasks.TaskManager}.
 *
 * <p>This command is created when the user input matches the
 * {@link benn.patterns.InputPattern#MARK_TASK} regex. It parses the index
 * of the task to be marked, updates the task’s status to done, and
 * returns a formatted confirmation message.</p>
 */
public class MarkCommand extends Command{

    /**
     * Constructs a new {@code MarkCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public MarkCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the mark command by parsing the index of the task to mark,
     * updating the {@link benn.tasks.Task} in the {@link benn.tasks.TaskManager}
     * to a done state, and returning a confirmation message.
     *
     * <p>If parsing fails, the index is invalid, or the update fails,
     * an error message is returned instead.</p>
     *
     * @param taskManager the {@link TaskManager} managing the current list of tasks
     * @return a user-facing message indicating success or error
     */
    @Override
    public String execute(TaskManager taskManager) {
        Matcher matcher = InputPattern.MARK_TASK.matcher(input);
        try {
            if (matcher.find()) {
                int index = Integer.parseInt(matcher.group("index"));
                Task task = taskManager.markAsDone(index);
                return MessageManager.retrieveMarkTaskAsDoneMessageFrom(task);
            } else {
                throw new BennException("Parsing error occurred");
            }
        } catch (NumberFormatException | BennException | IOException exception){
            return MessageManager.retrieveErrorMessageFrom(exception);
        }
    }
}
