package timmy;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.TimmyInvalidParamException;

/**
 * Collection of static methods used to parse user input commands.
 */
public class Parser {
    /**
     * Parses a generic command with format [COMMAND] [PARAMETERS]
     * by splitting the two into separate strings.
     *
     * @param   command String containing the full user input command.
     * @return          Array of 2 Strings: {COMMAND, PARAMETERS}
     */
    public static String[] parseCommand(String command) {
        return command.split(" ", 2);
    }

    /**
     * Parses the parameters given for the mark command. Parameter must be a
     * single integer that corresponds to a valid index within the saved Task List.
     *
     * @param   input Input parameters given by the user for the mark command.
     * @return  the index specified by the user.
     * @throws  TimmyInvalidParamException if the given input is not a valid
     *          index within the saved Task List.
     */
    public static int parseMark(String input) throws TimmyInvalidParamException {
        int index;
        try {
            index = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new TimmyInvalidParamException();
        }
        return index;
    }

    /**
     * Parses the parameters given for the deadline command. Parameters must
     * follow the format [DESCRIPTION] /by [DATE]
     *
     * @param   input Input parameters given by the user for the deadline command.
     * @return  An array of 2 strings: {DESCRIPTION, DATE}
     * @throws  TimmyInvalidParamException if the given input does not follow
     *          the format specified above.
     */
    public static String[] parseDeadline(String input) throws TimmyInvalidParamException {
        Pattern pattern = Pattern.compile("(.*?) /by (.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            assert matcher.groupCount() == 2;
            return new String[]{matcher.group(1), matcher.group(2)};
        } else {
            throw new TimmyInvalidParamException();
        }
    }

    /**
     * Parses the parameters given for the event command. Parameters must
     * follow the format [DESCRIPTION] /from [START_DATE] /to [END_DATE]
     *
     * @param   input Input parameters given by the user for the event command.
     * @return  An array of 3 strings: {DESCRIPTION, START_DATE, END_DATE}
     * @throws  TimmyInvalidParamException if the given input does not follow
     *          the format specified above.
     */
    public static String[] parseEvent(String input) throws TimmyInvalidParamException {
        Pattern pattern = Pattern.compile("(.*?) /from (.*?) /to (.*)");
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            assert matcher.groupCount() == 3;
            return new String[]{matcher.group(1), matcher.group(2), matcher.group(3)};
        } else {
            throw new TimmyInvalidParamException();
        }
    }
}
