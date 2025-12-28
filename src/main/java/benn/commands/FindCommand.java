package benn.commands;

import benn.exceptions.BennException;
import benn.messages.MessageManager;
import benn.patterns.InputPattern;
import benn.tasks.Task;
import benn.tasks.TaskManager;

import java.util.List;
import java.util.regex.Matcher;

/**
 * Represents a command that searches for tasks containing a given keyword
 * in their description.
 *
 * <p>This command is created when the user input matches the
 * {@link benn.patterns.InputPattern#FIND_ALL_TASKS_CONTAINING_KEYWORD}
 * regex. It extracts the keyword, searches all tasks in the
 * {@link benn.tasks.TaskManager}, and returns a formatted message
 * with the matching results.</p>
 */
public class FindCommand extends Command {

    /**
     * Constructs a new {@code FindCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public FindCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the find command by extracting the keyword from user input,
     * retrieving all {@link benn.tasks.Task} instances whose descriptions
     * contain that keyword, and returning a formatted message of the results.
     *
     * <p>If parsing fails or an error occurs during execution,
     * an error message is returned instead.</p>
     *
     * @param taskManager the {@link TaskManager} managing the current list of tasks
     * @return a formatted message containing all matching tasks, or an error message
     */
    @Override
    public String execute(TaskManager taskManager) {
        Matcher matcher = InputPattern.FIND_ALL_TASKS_CONTAINING_KEYWORD.matcher(input);
        try {
            if (matcher.find()) {
                String keyword = matcher.group("keyword");
                List<Task> tasks = taskManager.findAllTasksContaining(keyword);
                return MessageManager.retrieveFindMessageFrom(tasks);
            } else {
                throw new BennException("Parsing error occurred");
            }
        } catch (NumberFormatException | BennException exception){
            return MessageManager.retrieveErrorMessageFrom(exception);
        }
    }
}
