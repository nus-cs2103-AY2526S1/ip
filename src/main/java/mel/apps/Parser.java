package mel.apps;

import static java.lang.Integer.parseInt;

import mel.commands.ByeCommand;
import mel.commands.Command;
import mel.commands.DeleteCommand;
import mel.commands.FindCommand;
import mel.commands.ListCommand;
import mel.commands.MarkCommand;
import mel.commands.TaskCommand;
import mel.commands.UnmarkCommand;
import mel.commands.UpdateCommand;

import mel.exceptions.MelException;

/**
 * Parser parses the input from the user
 */
public class Parser {

    /**
     * Reads the input from the user and returns the corresponding command.
     *
     * @param input
     * @return Command
     * @throws MelException
     */
    public static Command parse(String input) throws MelException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String argument = words.length > 1 ? words[1].trim() : "";
        switch (command) {
        case "bye":
            return new ByeCommand(argument);
        case "list":
            return new ListCommand(argument);
        case "mark":
            return new MarkCommand(argument);
        case "unmark":
            return new UnmarkCommand(argument);
        case "todo":
            return new TaskCommand(argument, "T");
        case "deadline":
            return new TaskCommand(argument, "D");
        case "event":
            return new TaskCommand(argument, "E");
        case "delete":
            return new DeleteCommand(argument);
        case "find":
            return new FindCommand(argument);
        case "update":
            return new UpdateCommand(argument);
        default:
            throw new MelException("Please use the following commands: list, " +
                    "mark, unmark, todo, deadline, event, delete, find, update, bye.");

        }

    }

    /**
     * Handles the index for the argument to ensure it receives a number instead of no argument
     * or other arguments
     *
     * @param argument
     * @return int
     * @throws MelException
     */
    public static int handleIndex(String argument) throws MelException {
        if (argument.isEmpty()) {
            throw new MelException.NoArgumentFoundException("index");

        }

        int index;
        try {
            index = parseInt(argument) - 1;

        } catch (NumberFormatException e) {
            throw new MelException.InvalidIndexException("Please input a number instead :)");

        }


        return index;

    }


}
