package bobby.parser;

import bobby.bobbyexception.BobbyException;
import bobby.command.AddDeadlineCommand;
import bobby.command.AddEventCommand;
import bobby.command.AddTodoCommand;
import bobby.command.Command;
import bobby.command.DeleteCommand;
import bobby.command.MarkCommand;
import bobby.command.ExitCommand;
import bobby.command.FindCommand;
import bobby.command.ListCommand;
import bobby.command.UnmarkCommand;

/**
 * Parses user input commands and returns the corresponding Command object.
 */
public class Parser {
    private static final String[] VALID_COMMANDS = {
        "list", "mark", "unmark", "delete", "todo", "deadline", "event", "find", "bye", "viewschedule"
    };

    /**
     * Parses the full user command string and returns the appropriate Command object.
     *
     * @param fullCommand The full command string entered by the user.
     * @return The corresponding Command object.
     * @throws BobbyException If the command is invalid or arguments are missing.
     */
    public static Command parse(String fullCommand) throws BobbyException {
        assert fullCommand != null : "Input command should not be null";
        // Normalize input: trim and replace multiple spaces with single space
        fullCommand = fullCommand.trim().replaceAll("\\s+", " ");
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];
        switch (command) {
        case "list":
            return new ListCommand();
        case "mark":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Please specify the task number to mark.");
            }
            if (!words[1].trim().matches("\\d+")) {
                throw new BobbyException("Task number for mark must be a positive integer.");
            }
            int doneIndex = Integer.parseInt(words[1].trim()) - 1;
            return new MarkCommand(doneIndex);
        case "unmark":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Please specify the task number to unmark.");
            }
            if (!words[1].trim().matches("\\d+")) {
                throw new BobbyException("Task number for unmark must be a positive integer.");
            }
            int undoneIndex = Integer.parseInt(words[1].trim()) - 1;
            return new UnmarkCommand(undoneIndex);
        case "delete":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Please specify the task number to delete.");
            }
            if (!words[1].trim().matches("\\d+")) {
                throw new BobbyException("Task number for delete must be a positive integer.");
            }
            int deleteIndex = Integer.parseInt(words[1].trim()) - 1;
            return new DeleteCommand(deleteIndex);
        case "todo":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Whatdatmean the description of a todo cannot be empty.");
            }
            if (words[1].contains("/")) {
                throw new BobbyException("Todo description should not contain '/' characters.");
            }
            return new AddTodoCommand(words[1].trim());
        case "deadline":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Deadline must have both a description and a /by date famalam");
            }
            if (words[1].split("/by").length > 2) {
                throw new BobbyException("Deadline command should only have one /by.");
            }
            String[] deadlineParts = words[1].split("/by", 2);
            if (deadlineParts.length < 2) {
                throw new BobbyException("Deadline must have a /by date famalam");
            }
            if (deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new BobbyException("Deadline must have both a description and a /by date famalam");
            }
            if (deadlineParts[0].contains("/")) {
                throw new BobbyException("Deadline description should not contain '/' except for /by.");
            }
            return new AddDeadlineCommand(deadlineParts[0].trim(), deadlineParts[1].trim());
        case "event":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Event must have a description, a /from date, and a /to date bro try again!");
            }
            if (words[1].split("/from").length > 2 || words[1].split("/to").length > 2) {
                throw new BobbyException("Event command should only have one /from and one /to.");
            }
            String[] eventParts = words[1].split("/from|/to");
            if (eventParts.length < 3) {
                throw new BobbyException("Event must have /from and /to dates bro try again!");
            }
            if (eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
                throw new BobbyException("Event must have a description, a /from date, and a /to date bro try again!");
            }
            if (eventParts[0].contains("/")) {
                throw new BobbyException("Event description should not contain '/' except for /from and /to.");
            }
            return new AddEventCommand(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
        case "find":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Whatdatmean the search keyword cant be empty fam");
            }
            return new FindCommand(words[1].trim());
        case "bye":
            return new ExitCommand();
        case "viewschedule":
            if (words.length < 2 || words[1].trim().isEmpty()) {
                throw new BobbyException("Please specify a date in d/M/yyyy format, e.g., viewschedule 19/09/2025");
            }
            return new bobby.command.ViewScheduleCommand(words[1].trim());
        default:
            StringBuilder sb = new StringBuilder();
            sb.append("Whatdatmean! I can do these commands:\n");
            sb.append("- list\n");
            sb.append("- mark <task number>\n");
            sb.append("- unmark <task number>\n");
            sb.append("- delete <task number>\n");
            sb.append("- todo <description>\n");
            sb.append("- deadline <description> /by <date time>\n");
            sb.append("- event <description> /from <date time> /to <date time>\n");
            sb.append("- find <keyword>\n");
            sb.append("- viewschedule <date>\n");
            sb.append("- bye\n");
            throw new BobbyException(sb.toString().trim());
        }
    }
}