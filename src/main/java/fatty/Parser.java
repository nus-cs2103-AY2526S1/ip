package fatty;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import fatty.command.ByeCommand;
import fatty.command.Command;
import fatty.command.DeadlineCommand;
import fatty.command.DeleteCommand;
import fatty.command.EventCommand;
import fatty.command.FindCommand;
import fatty.command.ListCommand;
import fatty.command.MarkCommand;
import fatty.command.ToDoCommand;
import fatty.command.UnmarkCommand;

/**
 * Parser takes in the input from user as a String and parse it to appropriate formats for Commands to execute.
 */
public class Parser {

    /**
     * Returns Command object to be executed based of user input received.
     * If user input is not recognised, returns FattyException.
     *
     * @param fullCommand Full input from user.
     * @return Command object to be executed.
     * @throws FattyException If input is in the wrong format or not recognised.
     */
    public static Command parse(String fullCommand) throws FattyException {
        String[] parts = fullCommand.split(" ", 2);
        String commandWord = parts[0].toLowerCase();

        switch (commandWord) {
        case "find":
            if (parts.length < 2) {
                throw new FattyException("You must specify a task description!");
            }

            String keyword = parts[1].trim();
            return new FindCommand(keyword);

        case "list":
            return new ListCommand();

        case "mark":
            if (parts.length < 2) {
                throw new FattyException("You must specify a task number to mark!");
            }
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }

            int markId = Integer.parseInt(parts[1]);
            return new MarkCommand(markId);

        case "unmark":
            if (parts.length < 2) {
                throw new FattyException("You must specify a task number to unmark!");
            }
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }

            int unmarkId = Integer.parseInt(parts[1]);
            return new UnmarkCommand(unmarkId);

        case "delete":
            if (parts.length < 2) {
                throw new FattyException("You must specify a task number to delete!");
            }
            if (!parts[1].matches("\\d+")) {
                throw new FattyException("Task number must be a valid number!");
            }

            int deleteId = Integer.parseInt(parts[1]);
            return new DeleteCommand(deleteId);

        case "todo":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of a todo cannot be empty.");
            }
            return new ToDoCommand(parts[1].trim());

        case "deadline":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of a deadline cannot be empty.");
            }

            String[] deadlineParts = parts[1].split("/by", 2);
            if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                throw new FattyException("Deadline must have a description and a /by <d/M/yyyy HHmm>.");
            }

            String deadlineDescription = deadlineParts[0].trim();
            String byInput = deadlineParts[1].trim();

            LocalDateTime by;
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                by = LocalDateTime.parse(byInput, inputFormat);
            } catch (DateTimeParseException e) {
                throw new FattyException("Invalid date/time format! Use d/M/yyyy HHmm (e.g., 21/12/2024 1800).");
            }

            return new DeadlineCommand(deadlineDescription, by);

        case "event":
            if (parts.length < 2 || parts[1].trim().isEmpty()) {
                throw new FattyException("The description of an event cannot be empty.");
            }

            String[] eventParts = parts[1].split("/from", 2);
            if (eventParts.length < 2 || eventParts[0].trim().isEmpty()) {
                throw new FattyException("Event must have a description and /from <d/M/yyyy HHmm>.");
            }

            String[] timeParts = eventParts[1].split("/to", 2);
            if (timeParts.length < 2 || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                throw new FattyException("Event must have both /from <d/M/yyyy HHmm> and /to <d/M/yyyy HHmm>.");
            }

            String eventDescription = eventParts[0].trim();
            String fromInput = timeParts[0].trim();
            String toInput = timeParts[1].trim();

            LocalDateTime from;
            LocalDateTime to;
            try {
                DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
                from = LocalDateTime.parse(fromInput, inputFormat);
                to = LocalDateTime.parse(toInput, inputFormat);
            } catch (DateTimeParseException e) {
                throw new FattyException("Invalid date/time format! Use d/M/yyyy HHmm (e.g., 21/12/2024 1800).");
            }

            return new EventCommand(eventDescription, from, to);

        case "bye":
            return new ByeCommand();

        default:
            throw new FattyException("Sorry, I don’t understand that command.");
        }
    }
}
