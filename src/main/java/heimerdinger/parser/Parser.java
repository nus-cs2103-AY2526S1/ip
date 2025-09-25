package heimerdinger.parser;

import heimerdinger.HeimerdingerException;
import heimerdinger.command.AddDeadlineCommand;
import heimerdinger.command.AddEventCommand;
import heimerdinger.command.AddToDoCommand;
import heimerdinger.command.Command;
import heimerdinger.command.DeleteCommand;
import heimerdinger.command.ExitCommand;
import heimerdinger.command.FindCommand;
import heimerdinger.command.ListCommand;
import heimerdinger.command.MarkCommand;
import heimerdinger.command.ScheduleCommand;
import heimerdinger.command.UnmarkCommand;

/**
 * Responsible for reading input by the user and deciding which command to call.
 */
public class Parser {

    /**
     * Parses the raw input string and chooses appropriate command to call.
     *
     * @param command full input provided by user
     * @return Command object that encapsulates the logic needed to execute user command
     * @throws HeimerdingerException exception thrown if no appropriate command is given
     */
    public static Command parse(String command) throws HeimerdingerException {
        String[] split = command.split(" ", 2);
        String commandWord = split[0];
        String args = (split.length > 1) ? split[1] : "";

        switch (commandWord) {
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return new MarkCommand(args);
        case "unmark":
            return new UnmarkCommand(args);
        case "delete":
            return new DeleteCommand(args);
        case "todo":
            return new AddToDoCommand(args);
        case "deadline":
            return new AddDeadlineCommand(args);
        case "event":
            return new AddEventCommand(args);
        case "find":
            return new FindCommand(args);
        case "schedule":
            return new ScheduleCommand(args);
        default:
            throw new HeimerdingerException("I don't seem to understand this archaic language.");
        }
    }
}
