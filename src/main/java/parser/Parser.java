package parser;

import commands.*;
import exceptions.TheseException;
import app.These;

/**
 * Parses raw user input into a concrete {@link Command} instance.
 * <p>
 * Responsibilities:
 * - Tokenize the input to obtain the command keyword (first token).</li>
 * - Dispatch to the corresponding {@code Command} subtype.</li>
 * - Surface unknown commands as a {@link TheseException}.</li>
 *
 * Notes:
 * - Arguments after the command keyword (if any) are not consumed here; individual
 *       {@code Command} classes are expected to parse/use {@code these.getUi().getInput()}
 *       or similar mechanisms as defined in your codebase.</li>
 * - Supported commands: {@code bye}, {@code list}, {@code delete}, {@code mark},
 *       {@code unmark}, {@code todo}, {@code deadline}, {@code event}, {@code clear},
 *       {@code find}, {@code help}.
 */
public class Parser {

    /**
     * Convert a line of user input into a {@link Command}.
     *
     * @param input raw line entered by the user; the first whitespace-separated token is the command keyword
     * @param these application façade/context passed to command constructors
     * @return a {@link Command} ready to {@code execute()}
     * @throws TheseException if the keyword is not recognised
     */
    public static Command parse(String input, These these) throws TheseException {
        String[] parts = input.split(" ", 2);
        String cmd = parts[0];

        switch (cmd) {
            case "bye" -> {
                return new ExitCommand(these);
            }
            case "list" -> {
                return new ListCommand(these);
            }
            case "delete" -> {
                return new DeleteCommand(these);
            }
            case "mark" -> {
                return new MarkCommand(these);
            }
            case "unmark" -> {
                return new UnmarkCommand(these);
            }
            case "todo" -> {
                return new TodoCommand(these);
            }
            case "deadline" -> {
                return new DeadlineCommand(these);
            }
            case "event" -> {
                return new EventCommand(these);
            }
            case "clear" -> {
                return new ClearCommand(these);
            }
            case "find" -> {
                return new FindCommand(these);
            }
            case "help" -> {
                return new HelpCommand(these);
            }
            default -> throw new TheseException("Unknown command: " + cmd);

        }
    }
}
