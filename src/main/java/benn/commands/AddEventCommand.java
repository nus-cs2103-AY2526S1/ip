package benn.commands;

import benn.tasks.TaskManager;
import benn.exceptions.BennException;
import benn.messages.MessageManager;
import benn.patterns.InputPattern;
import benn.tasks.Event;

import java.io.IOException;
import java.util.regex.Matcher;

/**
 * Represents a command that adds an {@link benn.tasks.Event} task
 * to the {@link benn.tasks.TaskManager}.
 *
 * <p>This command is created when the user input matches the
 * {@link benn.patterns.InputPattern#ADD_EVENT} regex. It parses
 * the task description, start date/time, and end date/time from
 * the input, creates a new Event task, stores it in the TaskManager,
 * and returns a formatted confirmation message.</p>
 */
public class AddEventCommand extends Command {

    /**
     * Constructs a new {@code AddEventCommand} with the raw user input.
     *
     * @param input the raw user input string that triggered this command
     */
    public AddEventCommand(String input) {
        this.input = input;
        this.shouldExit = false;
    }

    /**
     * Executes the command by parsing the description, start date/time,
     * and end date/time from the user input, creating a new
     * {@link benn.tasks.Event} task, adding it to the given
     * {@link benn.tasks.TaskManager}, and returning a formatted
     * message for the user.
     *
     * <p>If parsing fails or the task cannot be added, an error message
     * is returned instead.</p>
     *
     * @param taskManager the {@link benn.tasks.TaskManager} managing the current list of tasks
     * @return a user-facing message indicating success or error
     */
    @Override
    public String execute(TaskManager taskManager) {
        Matcher matcher = InputPattern.ADD_EVENT.matcher(input);
        try {
            if (matcher.find()) {
                String description = matcher.group("description");
                String startDateTime = matcher.group("startDateTime");
                String endDateTime = matcher.group("endDateTime");
                Event event = taskManager.addEvent(description, startDateTime, endDateTime);
                return MessageManager.retrieveTaskMessageFrom(event, taskManager);
            } else {
                throw new BennException("Parsing error occurred");
            }
        } catch (BennException | IOException exception) {
            return MessageManager.retrieveErrorMessageFrom(exception);
        }
    }
}
