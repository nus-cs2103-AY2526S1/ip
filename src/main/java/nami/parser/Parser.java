package nami.parser;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


import nami.command.*;
import nami.exception.DukeException;

public class Parser {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");

    /**
     * Commands to branch out different scanner input (initialises the commands by calling the constructors)
     * @param full
     * @return
     * @throws DukeException
     */
    public static Command parse(String full) throws DukeException {
        String input = full.trim();
        assert input != null: "input should not be null";
        String[] parts = input.split(" ", 2);
        String keyword = parts[0].toLowerCase();
        String args = parts.length > 1 ? parts[1].trim() : "";

        switch (keyword) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "mark":
                return new MarkCommand(parseIndex(args));

            case "unmark":
                return new UnmarkCommand(parseIndex(args));

            case "todo":
                return new AddToDoCommand(args);

            case "deadline": {
                String[] deadlineParts = input.split("/by", 2);
                if (deadlineParts.length < 2) {
                    throw new DukeException("The deadline must have a /by date/time.");
                }
                String desc = deadlineParts[0].substring(9).trim(); // after "deadline"
                String byStr = deadlineParts[1].trim();
                try {
                    LocalDateTime by = LocalDateTime.parse(byStr, INPUT_DATE_FORMAT);
                    return new AddDeadlineCommand(desc, by);
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPSSS!!! Please enter the date/time in the format: d/M/yyyy HHmm (For e.g. 24/8/2025 2359)");
                }
            }

            case "event": {
                String[] p1 = input.split("/from", 2);
                if (p1.length < 2) {
                    throw new DukeException("Event must include /from and /to.");
                }
                String desc = p1[0].substring(6).trim(); // after "event"
                String[] p2 = p1[1].split("/to", 2);
                if (p2.length < 2) {
                    throw new DukeException("Event must include /to.");
                }
                try {
                    LocalDateTime start = LocalDateTime.parse(p2[0].trim(), INPUT_DATE_FORMAT);
                    LocalDateTime end = LocalDateTime.parse(p2[1].trim(), INPUT_DATE_FORMAT);
                    return new AddEventCommand(desc, start, end);
                } catch (DateTimeParseException e) {
                    throw new DukeException("OOPSSS!!! Please enter the date/time in the format: dd/M/yyyy HH:mm (for e.g 24/8/2025 2359");
                }
            }

            case "delete":
                return new DeleteCommand(parseIndex(args));

            case "find":
                if(args.isEmpty()) {
                    throw new DukeException("Please provide a keyword to find.");
                }
                return new nami.command.FindCommand(args);

            case "sort":
                return new SortCommand();


            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private static int parseIndex(String numStr) throws DukeException {
        try {
            int oneBased = Integer.parseInt(numStr);
            return oneBased - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Please give a valid task number.");
        }
    }
}
