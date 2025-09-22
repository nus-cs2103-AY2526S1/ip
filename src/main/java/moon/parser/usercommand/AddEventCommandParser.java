package moon.parser.usercommand;

import moon.commands.AddEventCommand;
import moon.commands.enums.Command;
import moon.models.Event;
import moon.models.MoonDateTime;
import moon.parser.exceptions.DateTimeException;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeChecker;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.ParseChecker;

/**
 * Parser for the {@link Command#EVENT} command.
 * <p>
 * Expected format:
 * <pre>
 *   event {description} /from {start time} /to {end time}
 * </pre>
 * Example:
 * <pre>
 *   event project meeting /from 12/12/2025 1800 /to 12/12/2025 2000
 * </pre>
 */
public class AddEventCommandParser implements CommandParser<AddEventCommand> {
    private static final Command COMMAND = Command.EVENT;
    private static final String PREFIX_FROM = "from";
    private static final String PREFIX_TO = "to";
    private static final boolean IS_TASK_NAME = true;
    private static final boolean IS_FROM_STORAGE = false;

    /**
     * Parses a user input string into an {@link AddEventCommand}.
     *
     * @param input the raw user input
     * @return an {@link AddEventCommand} containing the new {@link Event}
     * @throws ParseException if the input is malformed (e.g. missing keywords, empty fields, or invalid date/time)
     */
    @Override
    public AddEventCommand parse(String input) throws ParseException {
        String[] inputList = input.trim().split("\\s+/");

        // All FormatCheck methods throw a ParseException if the check is false, return nothing if true
        // Here it is checking if the event name parameter is empty,
        // and that there are 2 parts separated by "/"
        ParseChecker.isParameterEmpty(inputList[0], COMMAND, IS_TASK_NAME);
        ParseChecker.isCommandFormatValid(inputList, COMMAND);

        String eventName = ExtractString.extract(inputList[0], COMMAND.getKeyword());

        isParameterValid(inputList[1], PREFIX_FROM);
        isParameterValid(inputList[2], PREFIX_TO);

        // Extract the from and to date/time
        MoonDateTime fromTime = parseDateTime(inputList[1], PREFIX_FROM);
        MoonDateTime toTime = parseDateTime(inputList[2], PREFIX_TO);
        assert fromTime != null && toTime != null : "DateTimeParser should always return a MoonDateTime or throw";
        DateTimeChecker.isValidEventRange(fromTime, toTime);

        Event newEvent = new Event(eventName, fromTime, toTime);
        return new AddEventCommand(newEvent);
    }

    /**
     * Parses a prefixed date/time token (e.g., "from 12/12/2025 1800") into a {@link MoonDateTime}.
     * Removes the given prefix and delegates parsing to {@link DateTimeParser}.
     *
     * @param s       the raw token containing the prefix and date/time
     * @param exclude the prefix to remove (e.g., "from" or "to")
     * @return parsed {@link MoonDateTime}
     * @throws DateTimeException if the date/time is missing or invalid
     */
    private MoonDateTime parseDateTime(String s, String exclude) throws DateTimeException {
        String rawTime = ExtractString.extract(s, exclude);
        return DateTimeParser.parse(rawTime, IS_FROM_STORAGE);
    }

    /**
     * Validates that a parameter starts with the expected prefix and has a non-empty value.
     *
     * @param s      the raw token (e.g., "from 12/12/2025 1800")
     * @param prefix the expected prefix (e.g., "from" or "to")
     * @throws ParseException if the prefix is wrong or the value is empty
     */
    private void isParameterValid(String s, String prefix) throws ParseException {
        ParseChecker.isKeywordValid(s.split("\\s+")[0], prefix, COMMAND);
        ParseChecker.isParameterEmpty(s, COMMAND, !IS_TASK_NAME);
    }
}
