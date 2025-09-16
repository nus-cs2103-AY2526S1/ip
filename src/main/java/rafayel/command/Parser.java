
package rafayel.command;

import rafayel.Rafayel;
import rafayel.RafayelException;

/**
 * Parser deals with making sense of the user command.
 * Parser parse and understands the user's commands and determines the corresponding command type.
 */
public class Parser {

    private static String removeCommand(String input) {
        String[] parts = input.trim().split(" ", 2);
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            return "";
        }
        return parts[1].trim(); // remove command
    }

    /**
         * Parses the input string to determine the corresponding command type.
         *
         * @param input input string to be parsed.
         * @return the corresponding Command enum value.
         */
    public static Command parseCommand(String input) throws RafayelException {

        CommandHandle.CommandType commandType = CommandHandle.CommandType.getCommand(input);

        return switch (commandType) {
        case BYE -> new ByeCommand();
        case LIST -> new ListCommand();
        case MARK -> new MarkCommand(removeCommand(input), true);
        case UNMARK -> new MarkCommand(removeCommand(input), false);
        case TODO -> new TodoCommand(removeCommand(input));
        case DEADLINE -> new DeadlineCommand(removeCommand(input));
        case EVENT -> new EventCommand(removeCommand(input));
        case DELETE -> new DeleteCommand(removeCommand(input));
        case FIND -> new FindCommand(removeCommand(input));
        case REMIND -> new RemindCommand();
        default -> throw new RafayelException(Rafayel.UNKNOWN_COMMAND_ERROR);

        };
    }
}
