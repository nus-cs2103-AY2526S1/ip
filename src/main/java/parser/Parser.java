package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.Command;
import command.DateCommand;
import command.DeleteCommand;
import command.ListCommand;
import command.MarkCommand;
import command.QuitCommand;
import command.UpdateCommand;
import misc.Commands;
import misc.TaskBotException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Class handling the reading of commands
 */
public class Parser {

    /**
     * Matches the intended command to its execution
     * @param input intended command from user input
     * @return
     * @throws TaskBotException
     */
    public static Command parse(String input) throws TaskBotException {
        String[] parts = input.split(" ", 2);
        String c = parts[0].trim().toLowerCase();
        String args = parts.length > 1 ? parts[1] : "";

        Commands cEnum = Commands.fromString(c);

        switch (cEnum) {
        case BYE:
            return new QuitCommand();

        case LIST:
            return new ListCommand();

        case TODO:
            return parseTodo(args);

        case DEADLINE:
            return parseDeadline(args);

        case EVENT:
            return parseEvent(args);

        case MARK:
            return parseMark(args, true);

        case UNMARK:
            return parseMark(args, false);

        case DELETE:
            return parseDelete(args);

        case ONDATE:
            return parseOnDate(args);

        case UPDATE:
            return parseUpdate(input);

        default:
            throw new TaskBotException("OOPS!! I don't know what you mean by " + c);
        }
    }

    private static Command parseTodo(String args) throws TaskBotException {
        assert args != null : "Arguments should not be null";
        if (args.trim().isEmpty()) {
            throw new TaskBotException("What do you want to do?");
        }
        return new AddCommand(new Todo(args.trim()));
    }

    private static Command parseDeadline(String args) throws TaskBotException {
        assert args != null : "Arguments should not be null";
        if (!args.contains("/by")) {
            throw new TaskBotException("Deadlines need a description and date.");
        }

        String[] deadlineParts = args.split("/by", 2);
        if (deadlineParts.length < 2) {
            throw new TaskBotException("Format the deadline by description /by yyyy-MM-dd");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new TaskBotException("What is your deadline?");
        }

        return new AddCommand(new Deadline(description, by));
    }

    /**
     * Handles task update execution
     * @param input user input
     * @return UpdateCommand object holding the new updated info
     * @throws TaskBotException
     */
    public static Command parseUpdate(String input) throws TaskBotException {
        String[] parts = input.split(" ", 4);
        if (parts.length < 4) {
            throw new TaskBotException("Usage: update <index> /<field> <newVal>");
        }
        int index = Integer.parseInt(parts[1]);
        String field = parts[2];
        String newValue = parts[3];

        return new UpdateCommand(index, field, newValue);
    }


    private static Command parseEvent(String args) throws TaskBotException {
        if (!args.contains("/from") || !args.contains("/to")) {
            throw new TaskBotException("Events need both /from and /to date and times");
        }

        String description;
        String from;
        String to;

        if (args.indexOf("/from") < args.indexOf("/to")) {
            String[] eventParts = args.split("/from", 2);

            if (eventParts.length < 2) {
                throw new TaskBotException("Error: event input incomplete");
            }

            description = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/to", 2);

            if (timeParts.length < 2) {
                throw new TaskBotException("Error: event time details incomplete");
            }

            from = timeParts[0].trim();
            to = timeParts[1].trim();
        } else {
            String[] eventParts = args.split("/to", 2);

            if (eventParts.length < 2) {
                throw new TaskBotException("Format the event by description /from time /to time");
            }

            description = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/from", 2);

            if (timeParts.length < 2) {
                throw new TaskBotException("Format the event by description /from time /to time");
            }

            to = timeParts[0].trim();
            from = timeParts[1].trim();
        }

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new TaskBotException("Event details are incomplete");
        }
        return new AddCommand(new Event(description, from, to));
    }

    private static Command parseMark(String args, boolean isMarked) throws TaskBotException {
        if (args.trim().isEmpty()) {
            throw new TaskBotException("Which task would you like to " + (isMarked ? "mark?" : "unmark?"));
        }
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            return new MarkCommand(index, isMarked);
        } catch (NumberFormatException e) {
            throw new TaskBotException("OOPS!! Valid task number not found");
        }
    }

    private static Command parseDelete(String args) throws TaskBotException {
        if (args.trim().isEmpty()) {
            throw new TaskBotException("Which task would you like to delete?");
        }
        try {
            int index = Integer.parseInt(args.trim()) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new TaskBotException("OOPS!! Valid task number not found");
        }
    }

    private static Command parseOnDate(String args) throws TaskBotException {
        if (args.trim().isEmpty()) {
            throw new TaskBotException("What is your target date?");
        }
        try {
            LocalDate date = LocalDate.parse(args.trim());
            return new DateCommand(date);
        } catch (DateTimeParseException e) {
            throw new TaskBotException("OOPS!! Valid date in yyyy-MM-dd format not found");
        }
    }

    /**
     * Extracts intended command from user input
     * @param line user input
     * @return intended command
     * @throws TaskBotException
     */
    public static Task parseTask(String line) throws TaskBotException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new TaskBotException("Invalid task format in file: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task;
        switch (type) {
        case "T":
            if (parts.length != 3) {
                throw new TaskBotException("Invalid todo format in file: " + line);
            }
            task = new Todo(desc);
            break;
        case "D":
            if (parts.length != 4) {
                throw new TaskBotException("Invalid deadline format in file: " + line);
            }
            task = new Deadline(desc, parts[3]);
            break;
        case "E":
            if (parts.length != 5) {
                throw new TaskBotException("Invalid event format in file: " + line);
            }
            task = new Event(desc, parts[3], parts[4]);
            break;
        default:
            throw new TaskBotException("Invalid task in storage: " + line);
        }

        if (isDone) {
            task.mark();
        }
        return task;
    }
}
