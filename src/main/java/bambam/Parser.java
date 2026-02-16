package bambam;

import bambam.command.ByeCommand;
import bambam.command.Command;
import bambam.command.DeadlineCommand;
import bambam.command.DeleteCommand;
import bambam.command.EventCommand;
import bambam.command.FindCommand;
import bambam.command.HelpCommand;
import bambam.command.ListCommand;
import bambam.command.MarkCommand;
import bambam.command.ToDoCommand;
import bambam.command.UnmarkCommand;

/**
 * Parses user input into executable commands.
 */
public class Parser {

    /**
     * Converts a fullCommand string into a Command object.
     * @param fullCommand The input string from users.
     * @return The Command object to execute.
     * @throws BambamException If there is an error related to the passing of input or the chatbot.
     */
    public Command parse(String fullCommand) throws BambamException {
        assert (fullCommand != null && !fullCommand.isEmpty()) :
                "Input command must not be null or empty";

        fullCommand = fullCommand.trim(); // ChatGPT asked to include this line to trim whitespace
        String[] commands = fullCommand.split(" ", 2);

        String action = commands[0];

        // ChatGPT enhanced by shifting the checks into 2 other methods
        switch (action) {
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parseTaskNumber(commands, "mark"));
        case "unmark":
            return new UnmarkCommand(parseTaskNumber(commands, "unmark"));
        case "todo":
            ensureHasArgs(commands, "todo");
            return new ToDoCommand(commands[1]);
        case "deadline":
            ensureHasArgs(commands, "deadline");
            return new DeadlineCommand(commands[1]);
        case "event":
            ensureHasArgs(commands, "event");
            return new EventCommand(commands[1]);
        case "delete":
            return new DeleteCommand(parseTaskNumber(commands, "delete"));
        case "find":
            ensureHasArgs(commands, "find");
            return new FindCommand(commands[1]);
        case "help":
            return new HelpCommand();
        case "bye":
            return new ByeCommand();
        default:
            throw new BambamException("Sorry I don't get what you're saying, " +
                    "Type 'help' to see available commands");
        }
    }

    /**
     * Ensures that the given command has arguments, otherwise throws an exception.
     */
    private void ensureHasArgs(String[] commands, String commandName) throws BambamException {
        if (commands.length < 2 || commands[1].trim().isEmpty()) {
            throw new BambamException("Oopsies, description of " + commandName + " can't be empty");
        }
    }

    /**
     * Parses a task number argument safely.
     */
    private int parseTaskNumber(String[] commands, String commandName) throws BambamException {
        if (commands.length < 2) {
            throw new BambamException("Oopsies, please provide the task number to " + commandName);
        }
        try {
            return Integer.parseInt(commands[1].trim());
        } catch (NumberFormatException e) {
            throw new BambamException("Oopsies, task number for '" + commandName + "' must be a valid integer");
        }
    }
}
