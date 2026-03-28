package choicebot.parser;

import choicebot.ChoiceBotException;
import choicebot.command.Command;
import choicebot.command.DeadlineCommand;
import choicebot.command.DeleteCommand;
import choicebot.command.EventCommand;
import choicebot.command.ExitCommand;
import choicebot.command.FindCommand;
import choicebot.command.ListCommand;
import choicebot.command.MarkCommand;
import choicebot.command.SortCommand;
import choicebot.command.TodoCommand;
import choicebot.command.UnmarkCommand;


/**
 * Parser class interprets user input and converts it to an executable Command.
 */
public class Parser {
    /**
     * Parses the given user input into a Command.
     *
     * <p>
     * The first word in the user input is treated as the command keyword,
     * and the rest of the input is passed to the command as its description.
     * </p>
     *
     * @param command The actual input from the user.
     * @return a Command object representing the parsed input.
     * @throws ChoiceBotException if the user input does not match any valid command.
     */
    public static Command parse(String command) throws ChoiceBotException {
        String[] commandParts = command.split(" ", 2);
        String commandWord = commandParts[0];
        String commandDescription = commandParts.length > 1 ? commandParts[1] : "";

        switch (commandWord) {
        case ("bye"):
            return new ExitCommand();
        case ("list"):
            return new ListCommand(commandDescription);
        case ("mark"):
            return new MarkCommand(commandDescription);
        case ("unmark"):
            return new UnmarkCommand(commandDescription);
        case ("delete"):
            return new DeleteCommand(commandDescription);
        case ("todo"):
            return new TodoCommand(commandDescription);
        case ("event"):
            return new EventCommand(commandDescription);
        case ("deadline"):
            return new DeadlineCommand(commandDescription);
        case ("find"):
            return new FindCommand(commandDescription);
        case ("sort"):
            return new SortCommand(commandDescription);
        default:
            throw new ChoiceBotException("Invalid input. Please try again.");
        }
    }
}
