package peanutbutter.parser;

import peanutbutter.commands.Command;
import peanutbutter.commands.DeadlineCommand;
import peanutbutter.commands.DeleteCommand;
import peanutbutter.commands.EventCommand;
import peanutbutter.commands.ExitCommand;
import peanutbutter.commands.FindCommand;
import peanutbutter.commands.ListCommand;
import peanutbutter.commands.MarkCommand;
import peanutbutter.commands.ReminderCommand;
import peanutbutter.commands.TodoCommand;
import peanutbutter.commands.UnmarkCommand;
import peanutbutter.commands.WelcomeCommand;
import peanutbutter.exceptions.JuinException;

/**
 * Parser class responsible for interpreting user input
 * and converting it into Command objects.
 * <p>
 * The parser trims whitespace, splits the input into a command and arguments,
 * and maps the command keyword to a specific Command implementation.
 * It throws a {@link JuinException} if the input is empty or
 * the command is unrecognized.
 */
public class Parser {

    /**
     * Parses the given user input string and returns
     * the corresponding {@link Command} object.
     *
     * @param input the raw user input string
     * @return a Command object corresponding to the parsed input
     * @throws JuinException if the input is empty or the command is unknown
     */
    public static Command parse(String input) throws JuinException {
        input = input.trim();
        if (input.isEmpty()) {
            throw new JuinException("Input cannot be empty");
        }

        String[] parts = input.split(" ", 2);
        String command = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (command) {
        case "welcome": return new WelcomeCommand();
        case "bye": return new ExitCommand();
        case "list": return new ListCommand();
        case "reminder": return new ReminderCommand();
        case "mark": assert !args.isEmpty() : "command must have arguments";
            return new MarkCommand(args);
        case "unmark": assert !args.isEmpty() : "command must have arguments";
            return new UnmarkCommand(args);
        case "delete": assert !args.isEmpty() : "command must have arguments";
            return new DeleteCommand(args);
        case "todo": assert !args.isEmpty() : "command must have arguments";
            return new TodoCommand(args);
        case "deadline": assert !args.isEmpty() : "command must have arguments";
            return new DeadlineCommand(args);
        case "event": assert !args.isEmpty() : "command must have arguments";
            return new EventCommand(args);
        case "find": assert !args.isEmpty() : "command must have arguments";
            return new FindCommand(args);
        default: throw new JuinException("Sorry, I don't understand that command.");
        }
    }
}
