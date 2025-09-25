package tarawrr;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 ** Parser Class - Responsible for parsing user input commands.
 */
public class Parser {

    /**
     * Parse user input into its respective command and throws an exception if invalid input is given
     * @param input
     * @return
     * @throws TarawrrException
     */
    public static Command parseTask(String input) throws TarawrrException {
        String taskType = input.split(" ")[0].toLowerCase().trim();

    switch (taskType) {
    case "help":
        return new HelpCommand();

    case "clear":
        return new ClearCommand();

    case "snooze":
        String[] parts = input.split(" ");
        if (parts.length < 4) {
            throw new TarawrrException("Snooze command must have a task number and minimally a new date :(");
        }

        int index = Integer.parseInt(parts[1]);
        String[] dates = input.split("/to")[1].trim().split(" ");
        String newDate = dates[0];
        String endDate = "";
        if (dates.length == 1) {
            endDate = "";
        } else {
            endDate = dates[1];
        }
        return new SnoozeCommand(index, newDate, endDate);

    case "find":
        String keyword = input.substring(taskType.length()).trim();
        return new FindCommand(keyword);

    case "todo":
        String description = input.substring(taskType.length()).trim();
        if (description == "") {
            throw new TarawrrException("Todo task must have a description :-(");
        }
        return new TodoCommand(description);

    case "deadline":
        String[] deadlineParts = input.substring(taskType.length()).trim().split(" /by ");
        if (deadlineParts.length < 2) {
            throw new TarawrrException("Deadline task must have a description and deadline :-(");
        }
        return new DeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());

        case "event":
            String[] firstSplit = input.substring(taskType.length()).trim().split(" /from ");
            if (firstSplit.length < 2) {
                throw new TarawrrException("Event task must have a description, start and end date :-(");
            }
            String details = firstSplit[0].trim();
            String[] secondSplit = firstSplit[1].split(" /to ");
            if (secondSplit.length < 2) {
                throw new TarawrrException("Event task must have a start and end date :-(");
            }
            String start = secondSplit[0].trim();
            String end = secondSplit[1].trim();
            return new EventCommand(details, start, end);

        case "delete":
        if (input.split(" ").length < 2) {
            throw new TarawrrException("I'm not sure which task number to delete :-(");
        }
        int deleteNumber = Integer.parseInt(input.split(" ")[1]);
        return new DeleteCommand(deleteNumber);

    case "list":
        return new ListCommand();

    case "mark":
        if (input.split(" ").length < 2) {
            throw new TarawrrException("I'm not sure which task number to mark :-(");
        }
        int markNumber = Integer.parseInt(input.split(" ")[1]);
        return new MarkCommand(markNumber);

    case "unmark":
        if (input.split(" ").length < 2) {
            throw new TarawrrException("I'm not sure which task number to unmark :-(");
        }
        int unmarkNumber = Integer.parseInt(input.split(" ")[1]);
        return new UnmarkCommand(unmarkNumber);

    default:
    throw new TarawrrException("OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
    }
}
