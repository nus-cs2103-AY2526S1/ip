package apleasebot.parser;

import apleasebot.commands.ByeCommand;
import apleasebot.commands.Command;
import apleasebot.commands.DeadlineCommand;
import apleasebot.commands.DeleteCommand;
import apleasebot.commands.EventCommand;
import apleasebot.commands.FindCommand;
import apleasebot.commands.HelpCommand;
import apleasebot.commands.ListCommand;
import apleasebot.commands.MarkCommand;
import apleasebot.commands.ToDoCommand;
import apleasebot.commands.UnmarkCommand;
import apleasebot.exceptions.APleaseBotException;
import apleasebot.exceptions.UnknownCommandException;
import apleasebot.ui.Storage;

/**
 * Class to encapsulate the logic for recognising user inputs and passing along the input to the appropriate
 * command objects
 */
public class Parser {
    /**
     * Function that recognises the command
     * Functions that accept no argument are more specific while functions that require arguments just need to
     * begin with the keyword
     * @param input User input
     * @param data Storage file
     * @return A command object to be executed in the main file
     * @throws APleaseBotException Generic exception that can be thrown related to the command creation and parsing
     */
    public static Command parse(String input, Storage data) throws APleaseBotException {
        /* Deliberate choice not to use switch case for first 3 if-blocks to keep the method standardised */
        if (input.equals("bye")) {
            return new ByeCommand(data);
        }
        if (input.equals("help")) {
            return new HelpCommand();
        }
        if (input.equals("list")) {
            return new ListCommand();
        }
        if (input.startsWith("mark")) {
            return new MarkCommand(input);
        }
        if (input.startsWith("unmark")) {
            return new UnmarkCommand(input);
        }
        if (input.startsWith("todo")) {
            return new ToDoCommand(input);
        }
        if (input.startsWith("deadline")) {
            return new DeadlineCommand(input);
        }
        if (input.startsWith("event")) {
            return new EventCommand(input);
        }
        if (input.startsWith("delete")) {
            return new DeleteCommand(input);
        }
        if (input.startsWith("find")) {
            return new FindCommand(input);
        }
        throw new UnknownCommandException(input);
    }
}
