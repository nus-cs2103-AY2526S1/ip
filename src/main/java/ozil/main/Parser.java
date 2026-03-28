
package ozil.main;

import ozil.command.AddDeadlineTaskCommand;
import ozil.command.AddEventTaskCommand;
import ozil.command.AddTodoTaskCommand;
import ozil.command.Command;
import ozil.command.DeleteTaskCommand;
import ozil.command.FindTaskCommand;
import ozil.command.ListTasksByTimeCommand;
import ozil.command.ListTasksCommand;
import ozil.command.MarkTaskCommand;
import ozil.command.TerminatingCommand;
import ozil.command.UnmarkTaskCommand;
import ozil.exception.ErrorMessages;
import ozil.exception.OzilException;

/**
 * The main handler for user input. It throws some exceptions when user is missing input,
 * and deals with function calls for the different inputs.
 */
public class Parser {
    private static boolean doesInputStartWith(String input, String command) {
        return input.toLowerCase().startsWith(command);
    }

    /**
     * Handles input given by user
     * @param userInput input given by user
     * @return A command that can be run to do the action the user wants
     * @throws OzilException
     */
    public static Command handleInput(String userInput) throws OzilException {
        String input = userInput.trim();

        if (doesInputStartWith(input, "bye") || doesInputStartWith(input, "exit")) {
            return new TerminatingCommand(input);
        } else if (doesInputStartWith(input, "mark")) {
            return new MarkTaskCommand(input);
        } else if (doesInputStartWith(input, "unmark")) {
            return new UnmarkTaskCommand(input);
        } else if (doesInputStartWith(input, "delete")) {
            return new DeleteTaskCommand(input);
        } else if (doesInputStartWith(input, "todo")) {
            return new AddTodoTaskCommand(input);
        } else if (doesInputStartWith(input, "deadline")) {
            return new AddDeadlineTaskCommand(input);
        } else if (doesInputStartWith(input, "event")) {
            return new AddEventTaskCommand(input);
        } else if (doesInputStartWith(input, "list")) {
            return new ListTasksCommand(input);
        } else if (doesInputStartWith(input, "latest")) {
            return new ListTasksByTimeCommand(input);
        } else if (doesInputStartWith(input, "find")) {
            return new FindTaskCommand(input);
        } else {
            throw new OzilException(ErrorMessages.nonsenseError());
        }
    }
}