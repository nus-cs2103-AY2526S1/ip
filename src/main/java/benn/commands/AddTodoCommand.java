package benn.commands;

import benn.tasks.TaskManager;
import benn.exceptions.BennException;
import benn.messages.MessageManager;
import benn.patterns.InputPattern;
import benn.tasks.Todo;

import java.io.IOException;
import java.util.regex.Matcher;

/**
 * Represents a command that adds a {@link benn.tasks.Todo} task
 * to the {@link benn.tasks.TaskManager}.
 *
 * <p>This command is created when the user input matches the
 * {@link benn.patterns.InputPattern#ADD_TODO} regex. It parses
 * the task description from the input, creates a new Todo task,
 * stores it in the TaskManager, and returns a formatted confirmation
 * message.</p>
 */
public class AddTodoCommand extends Command {

    /**
     * Constructs a new {@code AddTodoCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public AddTodoCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the command by parsing the description from the user input,
     * creating a new {@link benn.tasks.Todo} task, adding it to the given
     * {@link benn.tasks.TaskManager}, and returning a formatted message
     * for the user.
     *
     * <p>If parsing fails or the task cannot be added, an error message
     * is returned instead.</p>
     *
     * @param taskManager the {@link benn.tasks.TaskManager} managing the current list of tasks
     * @return a user-facing message indicating success or error
     */
    @Override
    public String execute(TaskManager taskManager) {
        Matcher matcher = InputPattern.ADD_TODO.matcher(input);
        try {
            if (matcher.find()) {
                String description = matcher.group("description");
                Todo todo = taskManager.addTodo(description);
                return MessageManager.retrieveTaskMessageFrom(todo, taskManager);
            } else {
                throw new BennException("Parsing error occurred");
            }
        } catch (BennException | IOException exception) {
            return MessageManager.retrieveErrorMessageFrom(exception);
        }
    }
}
