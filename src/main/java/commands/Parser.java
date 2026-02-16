package commands;

import commands.others.FindCommand;
import commands.others.HelpCommand;
import commands.others.ListCommand;
import commands.others.TaskCommand;
import commands.task.DeleteCommand;
import commands.task.ExitCommand;
import commands.task.MarkCommand;
import ineffaexceptions.IneffaException;

/**
 * Creates a command based on user input.
 */
public class Parser {
    /**
     * Parsers user input into a defined Command
     *
     * @param input Input string from user containing full instruction.
     * @return Command based on the first word in input.
     * @throws IneffaException If command given is invalid.
     */
    public static Command parse(String input) throws IneffaException {
        String cmdString = input.split(" ")[0];
        CommandsEnum command = CommandsEnum.fromString(cmdString);
        String removeCmd = String.join("", input.split(cmdString)).trim();

        switch (command) {
        case BYE -> {
            return new ExitCommand();
        }
        case LIST -> {
            return new ListCommand();
        }
        case MARK -> {
            return new MarkCommand(removeCmd, true);
        }
        case UNMARK -> {
            return new MarkCommand(removeCmd, false);
        }
        case TODO, DEADLINE, EVENT -> {
            return new TaskCommand(command, removeCmd);
        }
        case DELETE -> {
            return new DeleteCommand(removeCmd);
        }
        case FIND -> {
            return new FindCommand(removeCmd);
        }
        case HELP -> {
            return new HelpCommand(removeCmd);
        }
        default -> throw new IneffaException("Invalid command");
        }
    }
}
