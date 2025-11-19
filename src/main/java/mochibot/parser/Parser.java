package mochibot.parser;

import mochibot.MochiBotException;
import mochibot.command.Command;


/**
 * The {@code Parser} class is responsible for interpreting user input commands
 * and converting them into executable {@link Command} objects.
 * <p>
 * This class handles different command types such as {@code todo}, {@code deadline},
 * {@code event}, {@code mark}, {@code unmark}, {@code delete}, {@code find},
 * {@code list}, and {@code bye}. Invalid inputs will result in
 * {@link mochibot.MochiBotException} being thrown.
 * </p>
 */
public class Parser {

    /**
     * The {@code CommandType} enumeration represents all the commands
     * that a user can call in the application.
     */
    public enum CommandType {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, BYE
    }

    private static CommandType parseCommand(String command) throws MochiBotException {
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new MochiBotException.InvalidCommandException();
        }
    }

    /**
     * Parses a full user input command string and returns the corresponding {@link Command}.
     *
     * @param fullCommand the complete command string entered by the user
     * @return the corresponding {@link Command} object
     * @throws MochiBotException if parsing fails for the given command or its arguments
     */
    public static Command parse(String fullCommand) throws MochiBotException {
        String[] inputs = fullCommand.trim().replaceAll("\\s+", " ").split(" ");
        CommandType command = parseCommand(inputs[0]);

        return switch (command) {
        case LIST -> ListParser.parse(inputs);
        case MARK -> MarkParser.parse(inputs);
        case UNMARK -> UnmarkParser.parse(inputs);
        case TODO -> TodoParser.parse(inputs);
        case DEADLINE -> DeadlineParser.parse(inputs);
        case EVENT -> EventParser.parse(inputs);
        case DELETE -> DeleteParser.parse(inputs);
        case FIND -> FindParser.parse(inputs);
        case BYE -> ByeParser.parse(inputs);
        };
    }
}
