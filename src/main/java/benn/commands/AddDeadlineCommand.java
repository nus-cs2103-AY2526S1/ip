package benn.commands;

import benn.tasks.TaskManager;
import benn.exceptions.BennException;
import benn.messages.MessageManager;
import benn.patterns.InputPattern;
import benn.tasks.Deadline;

import java.io.IOException;
import java.util.regex.Matcher;

/**
 * Represents a command that adds a {@link benn.tasks.Deadline} task
 * to the {@link benn.tasks.TaskManager}.
 *
 * <p>This command is created when the user input matches the
 * {@link benn.patterns.InputPattern#ADD_DEADLINE} regex. It parses
 * the task description and due date/time from the input, creates
 * a new Deadline task, stores it in the TaskManager, and returns
 * a formatted confirmation message.</p>
 */
public class AddDeadlineCommand extends Command {

    /**
     * Constructs a new {@code AddDeadlineCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public AddDeadlineCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the command by parsing the description and date/time due
     * from the user input, creating a new {@link benn.tasks.Deadline} task,
     * adding it to the given {@link benn.tasks.TaskManager}, and returning
     * a formatted message for the user.
     *
     * <p>If parsing fails or the task cannot be added, an error message
     * is returned instead.</p>
     *
     * @param taskManager the {@link benn.tasks.TaskManager} managing the current list of tasks
     * @return a user-facing message indicating success or error
     */
    @Override
    public String execute(TaskManager taskManager) {
        Matcher matcher = InputPattern.ADD_DEADLINE.matcher(input);
        try {
            if (matcher.find()) {
                String description = matcher.group("description");
                String dateTimeDue = matcher.group("dateTimeDue");
                Deadline deadline = taskManager.addDeadline(description, dateTimeDue);
                return MessageManager.retrieveTaskMessageFrom(deadline, taskManager);
            } else {
                throw new BennException("Parsing error occurred");
            }
        } catch (BennException | IOException exception) {
            return MessageManager.retrieveErrorMessageFrom(exception);
        }
    }
}

