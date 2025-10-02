package focus;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses raw user input into executable FocusCommand objects.
 */
public class InputParser {

    /** Throw an error for an empty description of a known command. */
    private static void emptyCommandError(String cmd) throws FocusException {
        throw new FocusException(String.format(
                "     OOPS!!! The description of a %s command cannot be empty.", cmd));
    }

    /**
     * Parses the given user input into a command.
     *
     * @param input Raw line entered by the user.
     * @param taskListSize Size of the task list
     * @return A FocusCommand corresponding to the input.
     * @throws FocusException If the input cannot be parsed into a valid command.
     */
    public static FocusCommand parse(String input, int taskListSize) throws FocusException {

        if (input == null || input.trim().isEmpty()) {
            throw new FocusException("     Empty command. Please type in a command for me to focus on!");
        }

        String[] headTail = input.trim().split("\\s+", 2);
        String cmd = headTail[0].toLowerCase();
        String args = (headTail.length > 1) ? headTail[1].trim() : "";

        switch (cmd) {

        case "list": return new ListCommand();
        case "todo":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            return new TodoCommand(args);
        case "deadline":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            return parseDeadline(args);
        case "event":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            return parseEvent(args);
        case "mark":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            List<Integer> indexes = parseIndexes(args, taskListSize); // args can be "3" or "1 2 3"
            assert indexes.stream().allMatch(i -> i > 0) : "Indexes must be 1-based positive integers";
            int[] varargs = indexes.stream().mapToInt(Integer::intValue).toArray();
            return new MarkCommand(varargs);
        case "unmark":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            indexes = parseIndexes(args, taskListSize); // args can be "3" or "1 2 3"
            assert indexes.stream().allMatch(i -> i > 0) : "Indexes must be 1-based positive integers";
            varargs = indexes.stream().mapToInt(Integer::intValue).toArray();
            return new UnmarkCommand(varargs);
        case "delete":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            return new DeleteCommand(parseIndex(args, taskListSize));
        case "find":
            if (args.isEmpty()) {
                throw new FocusException("     Usage: find <keyword>\n");
            }
            return new FindCommand(args);
        case "bye":
            return new ByeCommand();
        case "tag":
            if (args.isEmpty()) {
                emptyCommandError(cmd);
            }
            return parseTag(args, taskListSize);
        default:
            throw new FocusException("OOPS!!! I'm sorry, but I don't know what that means :-(\n    ");
        }
    }


    /**
     * Parses a Deadline command in the form:
     * deadline [desc] /by yyyy-MM-dd.
     * Note: Used ChatGPT here to modify parseDeadline to handle local date time formats
     *
     * @param args Argument portion after the deadline keyword.
     * @return A command that adds the deadline.
     * @throws FocusException If the arguments are missing or malformed.
     */
    private static FocusCommand parseDeadline(String args) throws FocusException {

        final String deadlineBy = "/by";
        int byIdx = args.indexOf(deadlineBy);
        if (byIdx < 0) {
            throw new FocusException("     Usage: deadline <description> /by yyyy-MM-dd HHmm");
        }

        String desc = args.substring(0, byIdx).trim();
        String byRaw = args.substring(byIdx + deadlineBy.length()).trim();

        if (desc.isEmpty() || byRaw.isEmpty()) {
            throw new FocusException("     Usage: deadline <description> /by yyyy-MM-dd HHmm");
        }

        return new DeadlineCommand(desc, byRaw);

    }


    /**
     * Parses an Event command in the form:
     * event [desc] /from [start] /to [end].
     * Note: Used ChatGPT here to modify parseEvent to handle local date time formats
     *
     * @param args Argument portion after the {event} keyword.
     * @return A command that adds the event.
     * @throws FocusException If the arguments are missing or malformed.
     */
    private static FocusCommand parseEvent(String args) throws FocusException {

        final String eventFrom = "/from";
        final String eventTo = "/to";

        int fromIdx = args.indexOf(eventFrom);
        int toIdx = args.indexOf(eventTo);
        if (fromIdx < 0 || toIdx < 0 || toIdx <= fromIdx) {
            throw new FocusException("     Usage: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }

        String desc = args.substring(0, fromIdx).trim();
        if (desc.isEmpty()) {
            emptyCommandError("event");
        }

        String afterFrom = args.substring(fromIdx + eventFrom.length()).trim();
        int relTo = afterFrom.indexOf(eventTo);
        if (relTo < 0) {
            throw new FocusException("     Usage: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }

        String startRaw = afterFrom.substring(0, relTo).trim();
        String endRaw = args.substring(toIdx + eventTo.length()).trim();

        if (startRaw.isEmpty() || endRaw.isEmpty()) {
            throw new FocusException("     Usage: event <description> /from yyyy-MM-dd HHmm /to yyyy-MM-dd HHmm");
        }

        return new EventCommand(desc, startRaw, endRaw);

    }

    /**
     * Parses a one-based index from text.
     *
     * @param s Text that should contain a positive integer.
     * @param taskListSize The size of the task list.
     * @return Parsed one-based index.
     * @throws FocusException If the text is empty or not a number.
     */
    private static int parseIndex(String s, int taskListSize) throws FocusException {

        if (s.isEmpty()) {
            throw new FocusException("     Index required.");
        }

        int index;

        try {
            index = Integer.parseInt(s.trim());
            if (index <= 0) {
                throw new FocusException("Invalid input. Index has to be a positive integer!");
            } else if ((index - 1) >= taskListSize) {
                throw new FocusException("Invalid input. Index exceeds current list size!");
            }
            return index;
        } catch (NumberFormatException e) {
            throw new FocusException("     Invalid index: " + s);
        }

    }

    /**
     * Parses a one-based single or multi-index from text.
     *
     * @param s Text that should contain one or more positive integers.
     * @param taskListSize The size of the task list.
     * @return Parsed one-based index or multi-index (e.g. "1" or "1 2 3") stored in Integer list.
     * @throws FocusException If the text is empty or not a positive integer number.
     */
    private static List<Integer> parseIndexes(String s, int taskListSize) throws FocusException {
        if (s == null || s.isBlank()) {
            throw new FocusException("     Index required.");
        }
        String[] stringList = s.trim().split("\\s+"); // supports "1 2 3" (any whitespace)
        List<Integer> toRet = new ArrayList<>(stringList.length);
        for (String t : stringList) {
            try {
                Integer checkInteger = Integer.parseInt(t);
                if (checkInteger <= 0) {
                    throw new FocusException("Invalid input. Indices have to be positive integers!");
                } else if ((checkInteger - 1) >= taskListSize) {
                    throw new FocusException(
                            String.format("Invalid input. The following input index exceeds current list size: " + t));
                }
                toRet.add(checkInteger);
            } catch (NumberFormatException e) {
                throw new FocusException("     Indices must be positive integers (got: \"" + t + "\").");
            }
        }
        return toRet;
    }

    /**
     * Parses a Tag command in the form:
     *   tag [index] [desc]
     * where desc must be a single token starting with '#', e.g. "#hello".
     *
     * Examples:
     *   tag 1 #work
     *
     * @param args The argument portion after the "tag" keyword, e.g. "1 #hello".
     * @param taskListSize The size of the task list.
     * @return A TagCommand carrying (index, "#tag").
     * @throws FocusException If the arguments are missing or malformed.
     */
    private static FocusCommand parseTag(String args, int taskListSize) throws FocusException {
        // Basic presence check
        if (args == null || args.trim().isEmpty()) {
            throw new FocusException("     Usage: tag <Task index> #tag");
        }

        // Split on whitespace
        final String[] toks = args.trim().split("\\s+");
        if (toks.length != 2) {
            throw new FocusException("     Usage: tag <Task index> #tag (exactly one tag)");
        }

        // 1) Parse index
        final String indexStr = toks[0];
        final int index;

        try {
            index = Integer.parseInt(indexStr);
            if (index <= 0) {
                throw new FocusException("Invalid input. Index has to be a positive integer!");
            } else if ((index - 1) >= taskListSize) {
                throw new FocusException("Invalid input. Index exceeds current list size!");
            }
        } catch (NumberFormatException e) {
            throw new FocusException("     Invalid index: " + indexStr);
        }

        // 2) Parse tag token (must start with '#', single token)
        final String tagToken = toks[1];

        if (!tagToken.startsWith("#")) {
            throw new FocusException("     Tag must start with '#', e.g., #hello");
        }

        final String tagBody = tagToken.substring(1).trim();
        if (tagBody.isEmpty()) {
            throw new FocusException("     Tag cannot be empty. Try: #hello");
        }

        // Allow letters, digits, underscore and hyphen; 1..20 chars
        if (!tagBody.matches("[A-Za-z0-9_-]{1,20}")) {
            throw new FocusException("     Invalid tag: " + tagToken
                    + " (allowed: letters, digits, '_' or '-', 1–20 chars)");
        }

        return new TagCommand(index - 1, tagBody);

    }

}
