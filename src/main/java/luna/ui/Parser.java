package luna.ui;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import luna.command.ByeCommand;
import luna.command.Command;
import luna.command.DeadlineCommand;
import luna.command.DeleteCommand;
import luna.command.EventCommand;
import luna.command.FindCommand;
import luna.command.FixedDurationTaskCommand;
import luna.command.ListCommand;
import luna.command.MarkCommand;
import luna.command.TodoCommand;
import luna.command.UnmarkCommand;
import luna.exception.LunaException;

/**
 * Parses the user input.
 */
public class Parser {
    /**
     * Returns the {@code Command} from the user input.
     *
     * @param command User input
     */
    public static Command parse(String command) {
        // Solution below adapted from ChatGPT
        String[] keywordAndArgs = command.split(" ", 2);
        String keyword = keywordAndArgs[0];
        String allArgs = keywordAndArgs.length > 1 ? keywordAndArgs[1].trim() : "";

        try {
            switch (keyword) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
            case "mark":
                return new MarkCommand(Integer.parseInt(allArgs));
            case "unmark":
                return new UnmarkCommand(Integer.parseInt(allArgs));
            case "delete":
                return new DeleteCommand(Integer.parseInt(allArgs));
            case "find":
                return new FindCommand(allArgs);
            case "todo":
                return new TodoCommand(allArgs);
            case "deadline":
                ArrayList<String> deadlineArgs = getParameters(allArgs, "/by");
                return new DeadlineCommand(deadlineArgs.get(0), LocalDate.parse(deadlineArgs.get(1)));
            case "event":
                ArrayList<String> eventArgs = getParameters(allArgs, "/from", "/to");
                return new EventCommand(eventArgs.get(0), LocalDate.parse(eventArgs.get(1)),
                        LocalDate.parse(eventArgs.get(2)));
            case "task":
                ArrayList<String> taskArgs = getParameters(allArgs, "/duration");
                return new FixedDurationTaskCommand(
                        taskArgs.get(0),
                        Duration.ofHours(Long.parseLong(taskArgs.get(1))));
            default:
                throw new LunaException("I'm sorry, but I don't know what that means :-(");
            }
        } catch (NumberFormatException e) {
            throw new LunaException("The argument needs to be an integer");
        } catch (DateTimeParseException e) {
            throw new LunaException("Please provide a valid date in yyyy-mm-dd format.");
        }
    }

    private static ArrayList<String> getParameters(String input, String... separators) {
        ArrayList<String> result = new ArrayList<>();
        String rest = input;
        for (String separator : separators) {
            String[] splitBySeparator = rest.split(separator, 2);
            if (splitBySeparator.length != 2) {
                throw new LunaException("Missing " + separator + " flag.");
            }
            result.add(splitBySeparator[0].trim());
            rest = splitBySeparator[1];
        }
        result.add(rest.trim());

        assert result.size() == separators.length + 1;
        return result;
    }
}
