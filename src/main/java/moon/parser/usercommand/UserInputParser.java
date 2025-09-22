package moon.parser.usercommand;

import moon.commands.BaseCommand;
import moon.commands.enums.Command;
import moon.commands.exceptions.InvalidCommandException;
import moon.parser.exceptions.ParseException;

/**
 * Central parser that routes user input to the appropriate {@link CommandParser}.
 * <p>
 * This class:
 * <ul>
 *   <li>Extracts the command keyword from user input</li>
 *   <li>Matches it against the list of supported {@link Command} values</li>
 *   <li>Delegates parsing of arguments to the corresponding command parser</li>
 * </ul>
 */
public class UserInputParser {

    /**
     * Parses raw user input into an executable {@link BaseCommand}.
     *
     * @param input the full user input string (e.g. {@code "deadline return book /by 6pm"})
     * @return a {@link BaseCommand} representing the action to execute
     * @throws ParseException if the command is unrecognised or arguments are invalid
     */
    public static BaseCommand parse(String input) throws ParseException {
        Command command = findMatchingCommand(input);

        // Switch-case ensures clear routing to the correct parser
        CommandParser<? extends BaseCommand> p = switch (command) {
        case LIST -> new ListCommandParser();
        case MARK -> new MarkCommandParser();
        case UNMARK -> new UnmarkCommandParser();
        case TODO -> new AddTodoCommandParser();
        case DEADLINE -> new AddDeadlineCommandParser();
        case EVENT -> new AddEventCommandParser();
        case DELETE -> new DeleteCommandParser();
        case FIND -> new FindTaskCommandParser();
        };
        return p.parse(input);
    }

    /**
     * Finds the {@link Command} matching the keyword in the given user input.
     * <p>
     * The keyword is assumed to be the first token (before the first space).
     *
     * @param input the full user input string
     * @return the matching {@link Command}
     * @throws InvalidCommandException if no matching command keyword is found
     */
    public static Command findMatchingCommand(String input) throws InvalidCommandException {
        String inputKeyword = input.split(" ")[0];

        return Command.getCommandStream()
                .parallel() // parallel for potential speedup when scanning all enums
                .filter(c -> c.getKeyword().equalsIgnoreCase(inputKeyword))
                .findFirst()
                .orElseThrow(() -> new InvalidCommandException(
                        inputKeyword,
                        "Meow! I couldn't find any matching command."
                ));
    }
}
