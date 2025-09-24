package khat;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import khat.command.AddCommand;
import khat.command.Command;
import khat.command.DateCommand;
import khat.command.DeleteCommand;
import khat.command.ExitCommand;
import khat.command.FindCommand;
import khat.command.ListCommand;
import khat.command.MarkCommand;
import khat.command.UnmarkCommand;
import khat.exception.DeadlineTaskException;
import khat.exception.EmptyTaskException;
import khat.exception.EventTaskException;
import khat.exception.KhatException;
import khat.task.Deadline;
import khat.task.Event;
import khat.task.Task;
import khat.task.Todo;

/** Handles parsing of user commands and task data. */
public class Parser {

    /** Represents the types of commands that can be used in the chatbot. */
    public enum CommandType {
        LIST, BYE, MARK, UNMARK, DELETE, DATE, FIND, TODO, DEADLINE, EVENT, UNKNOWN
    }

    /**
     * Returns the type of command from the user input.
     *
     * @param command The full user command string.
     * @return The command type as a string.
     */
    public static String getType(String command) {
        return command.split(" ")[0];
    }

    /**
     * Extracts and returns the description from the user command.
     *
     * @param command The full user command string.
     * @return The task description.
     * @throws EmptyTaskException If description is empty.
     */
    public static String getDescription(String command) {
        String description = command.split("/")[0]
                .substring(command.split("/")[0].indexOf(' ') + 1).trim();
        CommandType type = getCommandType(command);
        if (description.isEmpty()) {
            throw new EmptyTaskException("");
        } else if (!(type == CommandType.LIST) && !(type == CommandType.BYE)) {
            boolean isEmpty = description.equals(command.trim());
            if (isEmpty) {
                throw new EmptyTaskException("");
            }
        }
        return description;
    }

    /**
     * Returns the CommandType enum for the given command string.
     *
     * @param command The full user command string.
     * @return The CommandType.
     */
    //CHECKSTYLE.OFF: Indentation
    public static CommandType getCommandType(String command) {
        String type = getType(command);
        return switch (type) {
            case "list" -> CommandType.LIST;
            case "bye" -> CommandType.BYE;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            case "date" -> CommandType.DATE;
            case "find" -> CommandType.FIND;
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            default -> CommandType.UNKNOWN;
        };
    }

    /**
     * Parses the user command and returns the corresponding Command object.
     *
     * @param command The full user command string.
     * @return The Command object.
     * @throws KhatException If the command is invalid or arguments are missing.
     */
    public static Command parse(String command) throws KhatException {
        CommandType type = getCommandType(command);
        if (type == CommandType.UNKNOWN) {
            throw new KhatException("Invalid command!");
        }
        String description = getDescription(command);
        return switch (type) {
            case LIST -> new ListCommand();
            case BYE -> new ExitCommand();
            case MARK -> new MarkCommand(getIndex(command));
            case UNMARK -> new UnmarkCommand(getIndex(command));
            case DELETE -> new DeleteCommand(getIndex(command));
            case DATE -> new DateCommand(description);
            case FIND -> new FindCommand(description);
            case TODO -> new AddCommand(description, "todo");
            case DEADLINE -> new AddCommand(description, "deadline", getDeadline(command));
            case EVENT -> new AddCommand(description, "event", getFrom(command), getTo(command));
            default -> throw new KhatException("Invalid command!"); // should not reach
        };
    }
    //CHECKSTYLE.ON: Indentation

    /**
     * Returns the index in the array list for mark, unmark, or delete commands.
     *
     * @param command The full user command string.
     * @return The zero-based index of the task.
     */
    public static int getIndex(String command) {
        return Integer.parseInt(command.split(" ")[1]) - 1;
    }

    /**
     * Extracts the deadline string from a deadline command.
     *
     * @param command The full user command string.
     * @return The deadline string.
     * @throws DeadlineTaskException If the deadline is missing.
     */
    public static String getDeadline(String command) {
        String[] descriptionArr = command.split("/by"); // [0] -> type, [1] -> by
        if (descriptionArr.length < 2) {
            throw new DeadlineTaskException("Add a deadline task in the format 'deadline [task] /by [deadline]!'");
        }
        return descriptionArr[1].trim();
    }

    /**
     * Extracts the start date/time from an event command.
     *
     * @param command The full user command string.
     * @return The event start date/time string.
     * @throws EventTaskException If the event format is invalid.
     */
    public static String getFrom(String command) {
        String[] commandArr = command.split("/from|/to"); // [0] -> type, [1] -> from, [2] -> to
        if (commandArr.length < 3) {
            throw new EventTaskException("Add an event task in the format 'event [task] /from [start] /to [end]!'");
        }
        return commandArr[1].trim();
    }

    /**
     * Extracts the end date/time from an event command.
     *
     * @param command The full user command string.
     * @return The event end date/time string.
     * @throws EventTaskException If the event format is invalid.
     */
    public static String getTo(String command) {
        String[] commandArr = command.split("/from|/to"); // [0] -> type, [1] -> from, [2] -> to
        if (commandArr.length < 3) {
            throw new EventTaskException("Add an event task in the format 'event [task] /from [start] /to [end]!'");
        }
        return commandArr[2].trim();
    }

    /**
     * Parses a line from the save file and returns the corresponding Task object.
     *
     * @param line The line from the save file.
     * @return The parsed Task object.
     * @throws IllegalArgumentException If the task type is unknown or the line is malformed.
     */
    public static Task parseTask(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (type) {
        case "T":
            return new Todo(description, isDone);
        case "D":
            String by = parts[3];
            return new Deadline(description, isDone, by);
        case "E":
            String from = parts[3];
            String to = parts[4];
            return new Event(description, isDone, from, to);
        default:
            throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    /**
     * Parses a date string in the format dd-MM-yyyy and returns a LocalDate object.
     *
     * @param date The date string to parse.
     * @return The parsed LocalDate object.
     * @throws DateTimeParseException If the date format is invalid.
     */
    public static LocalDate parseDate(String date) throws DateTimeParseException {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }
}
