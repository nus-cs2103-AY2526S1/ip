package marcus;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    private static Pattern markStatusPattern = Pattern.compile("^(mark) (\\d+)$");
    private static Pattern unmarkStatusPattern = Pattern.compile("^(unmark) (\\d+)$");
    private static Pattern toDoPattern = Pattern.compile("^(todo)\\s*(.*)$");
    private static String datePattern = "\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";
    private static Pattern deadlinePattern = Pattern.compile("^(deadline) (.+) (/by) (" + datePattern + ")$");
    private static Pattern eventPattern = Pattern.compile("^(event) (.+) (/from) (.+) (/to) (.+)$");
    private static Pattern deletePattern = Pattern.compile("^(delete) (\\d+)$");
    private static Pattern findPattern = Pattern.compile("^(find)\\s*(.*)$");

    private static class CommandPattern {
        final Pattern pattern;
        final String command;
        final int[] groups;

        CommandPattern(Pattern pattern, String command, int... groups) {
            this.pattern = pattern;
            this.command = command;
            this.groups = groups;
        }
    }

    private static final List<CommandPattern> COMMAND_PATTERNS = List.of(
            new CommandPattern(markStatusPattern, "mark", 2),
            new CommandPattern(unmarkStatusPattern, "unmark", 2),
            new CommandPattern(toDoPattern, "toDo", 2),
            new CommandPattern(deadlinePattern, "deadline", 2, 4),
            new CommandPattern(eventPattern, "event", 2, 4, 6),
            new CommandPattern(deletePattern, "delete", 2),
            new CommandPattern(findPattern, "find", 2)
    );

    /**
     * Parses user input and processes the important information for each command.
     *
     * @param userInput The user's input to the chatbot, Marcus.
     * @return An ArrayList, where the zero index stores type of command, and the remaining store the parsed parameters.
     */
    public static ArrayList<String> parseCommand(String userInput) {
        ArrayList<String> ret = new ArrayList<>();

        // handle simple commands
        if (userInput.equals("bye") || userInput.equals("list") || userInput.equals("help")) {
            ret.add(userInput);
            return ret;
        }

        // handle regex-based commands
        for (CommandPattern cp : COMMAND_PATTERNS) {
            Matcher matcher = cp.pattern.matcher(userInput);
            if (!matcher.matches()) {
                continue;
            }

            ret.add(cp.command); // case where input matches pattern
            for (int groupIndex : cp.groups) {
                ret.add(matcher.group(groupIndex));
            }
            return ret;
        }

        // fallback
        ret.add("Invalid Command");
        return ret;
    }
}
