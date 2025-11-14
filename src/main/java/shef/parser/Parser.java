package shef.parser;


import shef.command.AddDeadlineCommand;
import shef.command.AddEventCommand;
import shef.command.AddTodoCommand;
import shef.command.Command;
import shef.command.DeleteCommand;
import shef.command.ExitCommand;
import shef.command.FindCommand;
import shef.command.HelpCommand;
import shef.command.InvalidCommand;
import shef.command.ListCommand;
import shef.command.MarkCommand;
import shef.command.UnmarkCommand;

/**
 * Contains method(s) that parse commands sent by the user.
 */
public class Parser {
    /**
     * Parses user command.
     *
     * @param input Full command sent by user.
     * @return Command object that represents the command to be executed.
     */
    public static Command parse(String input) {
        String[] parts = splitInput(input);
        String cmd = parts[0];
        String args = parts[1];

        switch (cmd) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "todo":
            return new AddTodoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "find":
            return new FindCommand(args);
        case "help":
            return new HelpCommand(args);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Splits input into cmd and arg parts.
     * @param input user input.
     * @return array containing the parts.
     */
    private static String[] splitInput(String input) {
        int firstSpace = input.indexOf(" ");

        boolean hasSpace = firstSpace != -1;
        boolean hasTrailingCharsAfterFirstSpace = firstSpace + 1 < input.length();

        String cmd = hasSpace ? input.substring(0, firstSpace) : input;
        String args = hasSpace && hasTrailingCharsAfterFirstSpace
                      ? input.substring(firstSpace + 1)
                      : "";

        assert !cmd.contains(" ") : "Command keyword contains no spaces.";
        assert cmd.length() + args.length() <= input.length() : "Length of cmd and args do not exceed length of input.";

        return new String[]{cmd, args};
    }
}
