package moon.parser.usercommand;

import moon.commands.BaseCommand;
import moon.parser.exceptions.ParseException;

/**
 * Generic interface for all user command parsers.
 * <p>
 * Each parser is responsible for converting raw user input into
 * a specific {@link BaseCommand} instance.
 *
 * @param <T> the type of {@link BaseCommand} produced by this parser
 */
public interface CommandParser<T extends BaseCommand> {

    /**
     * Parses the given raw user input into a command object.
     *
     * @param input the user input string
     * @return a command instance ready for execution
     * @throws ParseException if the input format is invalid or incomplete
     */
    T parse(String input) throws ParseException;
}
