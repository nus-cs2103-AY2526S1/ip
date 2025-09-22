package moon.parser.usercommand;

import moon.commands.AddDeadlineCommand;
import moon.commands.enums.Command;
import moon.models.Deadline;
import moon.models.MoonDateTime;
import moon.parser.exceptions.DateTimeException;
import moon.parser.exceptions.ParseException;
import moon.parser.util.DateTimeChecker;
import moon.parser.util.DateTimeParser;
import moon.parser.util.ExtractString;
import moon.parser.util.ParseChecker;

/**
 * Parser for the {@link Command#DEADLINE} command.
 * <p>
 * Expected format:
 * <pre>
 *   deadline {description} /by {deadline time}
 * </pre>
 * Example:
 * <pre>
 *   deadline return book /by 25/12/2025 1800
 * </pre>
 */
public class AddDeadlineCommandParser implements CommandParser<AddDeadlineCommand> {
    private static final Command COMMAND = Command.DEADLINE;
    private static final String PREFIX_BY = "by";
    private static final boolean IS_TASK_NAME = true;
    private static final boolean IS_FROM_STORAGE = false;

    /**
     * Parses a user input string into an {@link AddDeadlineCommand}.
     *
     * @param input the raw user input
     * @return an {@link AddDeadlineCommand} containing the new {@link Deadline}
     * @throws ParseException if the input is malformed
     *                        (e.g. missing description, missing "/by" keyword, or invalid date/time format)
     */
    @Override
    public AddDeadlineCommand parse(String input) throws ParseException {
        String[] inputList = input.trim().split("\\s+/");

        // All FormatCheck methods throw a ParseException if the check is false, return nothing if true
        // Here it is checking if the event name parameter is empty,
        // and that there are 3 parts separated by "/"
        ParseChecker.isParameterEmpty(inputList[0], COMMAND, IS_TASK_NAME);
        ParseChecker.isCommandFormatValid(inputList, COMMAND);

        // Extract out the name and the keyword "by" to check
        String deadlineName = ExtractString.extract(inputList[0], COMMAND.getKeyword());

        isParameterValid(inputList[1]);

        // Extract the by date/time
        MoonDateTime deadlineTime = parseDateTime(inputList[1]);
        assert deadlineTime != null : "DateTimeParser should always return a MoonDateTime or throw";
        DateTimeChecker.isNotBeforeToday(deadlineTime);

        Deadline newDeadline = new Deadline(deadlineName, deadlineTime);
        return new AddDeadlineCommand(newDeadline);
    }

    /**
     * Parses a prefixed date/time token (e.g., "by 12/12/2025 1800") into a {@link MoonDateTime}.
     * Removes the given prefix and delegates parsing to {@link DateTimeParser}.
     *
     * @param s       the raw token containing the prefix and date/time
     * @return parsed {@link MoonDateTime}
     * @throws DateTimeException if the date/time is missing or invalid
     */
    private MoonDateTime parseDateTime(String s) throws DateTimeException {
        String rawTime = ExtractString.extract(s, PREFIX_BY);
        return DateTimeParser.parse(rawTime, IS_FROM_STORAGE);
    }

    /**
     * Validates that a parameter starts with the expected prefix and has a non-empty value.
     *
     * @param s      the raw token (e.g., "by 12/12/2025 1800")
     * @throws ParseException if the prefix is wrong or the value is empty
     */
    private void isParameterValid(String s) throws ParseException {
        ParseChecker.isKeywordValid(s.split("\\s+")[0], PREFIX_BY, COMMAND);
        ParseChecker.isParameterEmpty(s, COMMAND, !IS_TASK_NAME);
    }
}
