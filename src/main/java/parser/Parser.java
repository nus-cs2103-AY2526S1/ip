package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import instruction.AddInstruction;
import instruction.DeleteInstruction;
import instruction.ExitInstruction;
import instruction.FindInstruction;
import instruction.HelpInstruction;
import instruction.Instruction;
import instruction.ListInstruction;
import instruction.MarkInstruction;
import instruction.OnDateInstruction;
import instruction.SortInstruction;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;
import util.Command;
import util.ShrekException;

/**
 * Handles parsing of user input and file data into executable instructions and tasks.
 * This class serves as the main parser for converting text commands into application instructions
 * and for parsing stored task data from files.
 */

public class Parser {

    /**
     * Parses user input string and converts it into an executable instruction.
     *
     * @param userInput the raw user input string to parse
     * @return an Instruction object corresponding to the parsed command
     * @throws ShrekException if the command is invalid or contains errors
     */
    public static Instruction parse(String userInput) throws ShrekException {
        String cleanedInput = userInput.trim().replaceAll("\\s+", " ");
        String[] parts = cleanedInput.split(" ", 2);
        String commandWord = parts[0].trim().toLowerCase();
        String arguments = parts.length > 1 ? parts[1] : "";
        Command commandEnum = Command.fromString(commandWord);
        return switch (commandEnum) {
        case BYE -> new ExitInstruction();
        case LIST -> new ListInstruction();
        case TODO -> parseTodo(arguments);
        case DEADLINE -> parseDeadline(arguments);
        case EVENT -> parseEvent(arguments);
        case MARK -> parseMark(arguments, true);
        case UNMARK -> parseMark(arguments, false);
        case DELETE -> parseDelete(arguments);
        case ONDATE -> parseOnDate(arguments);
        case FIND -> parseFind(arguments.trim().split("\\s+"));
        case SORT -> parseSort(arguments);
        case HELP -> new HelpInstruction();
        default -> throw new ShrekException(
                "Shrek doesn't speak your language. What's: " + commandWord + "?");
        };
    }

    /**
     * Parses arguments for a todo command and creates the corresponding instruction.
     *
     * @param arguments the arguments string following the todo command
     * @return an AddInstruction containing a new Todo task
     * @throws ShrekException if the description is empty
     */
    private static Instruction parseTodo(String arguments) throws ShrekException {
        assert arguments != null : "Arguments should not be null";
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("No Onions?!? The description of a todo cannot be empty.");
        }
        return new AddInstruction(new Todo(arguments.trim()));
    }

    /**
     * Parses arguments for a deadline command and creates the corresponding instruction.
     *
     * @param arguments the arguments string following the deadline command
     * @return an AddInstruction containing a new Deadline task
     * @throws ShrekException if the format is invalid or arguments are missing
     */
    private static Instruction parseDeadline(String arguments) throws ShrekException {
        assert arguments != null : "Arguments should not be null";
        if (!arguments.contains("/by")) {
            throw new ShrekException("Deadlines must have a description and a /by date time.");
        }

        String[] deadlineParts = arguments.split("/by", 2);
        if (deadlineParts.length < 2) {
            throw new ShrekException("Deadline format should be: description /by yyyy-MM-dd hh:mm");
        }

        String description = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();

        if (description.isEmpty() || by.isEmpty()) {
            throw new ShrekException("Where's my onions? Deadline description or date/time cannot be empty.");
        }

        return new AddInstruction(new Deadline(description, by));
    }

    /**
     * Parses arguments for an event command and creates the corresponding instruction.
     * Handles both /from before /to and /to before /from argument orders.
     *
     * @param arguments the arguments string following the event command
     * @return an AddInstruction containing a new Event task
     * @throws ShrekException if the format is invalid or arguments are missing
     */
    private static Instruction parseEvent(String arguments) throws ShrekException {
        // Check if both /from and /to are present
        if (!arguments.contains("/from") || !arguments.contains("/to")) {
            throw new ShrekException("Events must have both /from and /to date and times");
        }

        // Handle both orders: /from first or /to first
        String description;
        String from;
        String to;

        if (arguments.indexOf("/from") < arguments.indexOf("/to")) {
            // Normal order: /from comes before /to
            String[] eventParts = arguments.split("/from", 2);
            if (eventParts.length < 2) {
                throw new ShrekException("Invalid event format! Shrek needs: event description /from yyyy-MM-dd HH:mm "
                        + "/to yyyy-MM-dd HH:mm\n" + "Example: event team meeting /from 2025-12-05 14:00 "
                        + "/to 2025-12-05 16:00");
            }

            description = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/to", 2);

            if (timeParts.length < 2) {
                throw new ShrekException("Missing time parameters!\n" + "Shrek needs event with /from 2025-12-05 "
                        + "14:00 /to 2025-12-31 16:00");
            }

            from = timeParts[0].trim();
            to = timeParts[1].trim();
        } else {
            // Reverse order: /to comes before /from
            String[] eventParts = arguments.split("/to", 2);
            if (eventParts.length < 2) {
                throw new ShrekException("Event format should be: description /from time /to time");
            }

            description = eventParts[0].trim();
            String[] timeParts = eventParts[1].split("/from", 2);

            if (timeParts.length < 2) {
                throw new ShrekException("Event format should be: description /from time /to time");
            }

            from = timeParts[1].trim();
            to = timeParts[0].trim();
        }

        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ShrekException("Event description, start time or end time cannot be empty.");
        }

        return new AddInstruction(new Event(description, from, to));
    }

    /**
     * Parses arguments for mark/unmark commands and creates the corresponding instruction.
     *
     * @param arguments  the arguments string containing the task index
     * @param markAsDone true for mark command, false for unmark command
     * @return a MarkInstruction with the specified index and mark status
     * @throws ShrekException if the index is missing or invalid
     */
    private static Instruction parseMark(String arguments, boolean markAsDone) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("Shrek needs a task number to " + (markAsDone ? "mark" : "unmark") + "!");
        }

        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new MarkInstruction(index, markAsDone);
        } catch (NumberFormatException e) {
            throw new ShrekException("Shrek needs a valid task number!");
        }
    }

    /**
     * Parses arguments for a delete command and creates the corresponding instruction.
     *
     * @param arguments the arguments string containing the task index
     * @return a DeleteInstruction with the specified index
     * @throws ShrekException if the index is missing or invalid
     */
    private static Instruction parseDelete(String arguments) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("Shrek needs a task number to delete!");
        }

        try {
            int index = Integer.parseInt(arguments.trim()) - 1;
            return new DeleteInstruction(index);
        } catch (NumberFormatException e) {
            throw new ShrekException("Shrek needs a valid task number!");
        }
    }

    /**
     * Parses arguments for an ondate command and creates the corresponding instruction.
     *
     * @param arguments the arguments string containing the date
     * @return an OnDateInstruction with the specified date
     * @throws ShrekException if the date is missing or invalid
     */
    private static Instruction parseOnDate(String arguments) throws ShrekException {
        if (arguments.trim().isEmpty()) {
            throw new ShrekException("Shrek needs a date! Format: yyyy-MM-dd\n"
                    + "Example: ondate 2025-12-25");
        }

        try {
            LocalDate date = LocalDate.parse(arguments.trim());
            return new OnDateInstruction(date);
        } catch (DateTimeParseException e) {
            throw new ShrekException("Shrek needs a valid date in *yyyy-MM-dd* format!\n"
                    + "Example: ondate 2025-12-25");
        }
    }

    /**
     * Parses arguments for a find command and creates the corresponding instruction.
     * Supports one or more search keywords using varargs.
     *
     * @param keywords one or more keywords to search for
     * @return a FindInstruction containing the joined search keywords
     * @throws ShrekException if no keywords are provided or empty
     */
    private static Instruction parseFind(String... keywords) throws ShrekException {
        String query = String.join(" ", keywords).trim();

        if (query.isEmpty()) {
            throw new ShrekException("Shrek needs a word to find tasks!");
        }

        return new FindInstruction(query);
    }

    /**
     * Parses arguments for a sort command and creates the corresponding instruction.
     *
     * @param arguments the sorting criteria
     * @return a SortInstruction with the specified criteria
     * @throws ShrekException if the criteria is invalid
     */
    private static Instruction parseSort(String arguments) throws ShrekException {
        String criteria = arguments.trim().toLowerCase();

        switch (criteria) {
        case "description":
            return new SortInstruction(SortInstruction.SortCriteria.DESCRIPTION);
        case "date":
            return new SortInstruction(SortInstruction.SortCriteria.DATE);
        case "type":
            return new SortInstruction(SortInstruction.SortCriteria.TYPE);
        case "":
            throw new ShrekException("Shrek needs to know how to sort! Use: sort description/date/type");
        default:
            throw new ShrekException("Shrek can only sort by description, date, or type. Not: " + criteria);
        }
    }

    /**
     * Parses a line from the storage file and converts it into a Task object.
     *
     * @param line the line from the storage file to parse
     * @return a Task object reconstructed from the file data
     * @throws ShrekException if the line format is invalid or corrupted
     */
    // Keep the file parsing method for Storage
    public static Task parseTaskFromFile(String line) throws ShrekException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new ShrekException("Rotten onion! Invalid task format in file: " + line);
        }

        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String desc = parts[2];

        Task task;
        switch (type) {
        case "T":
            if (parts.length != 3) {
                throw new ShrekException("Invalid todo format in file: " + line);
            }
            task = new Todo(desc);
            break;
        case "D":
            if (parts.length != 4) {
                throw new ShrekException("Invalid deadline format in file: " + line);
            }
            task = new Deadline(desc, parts[3]);
            break;
        case "E":
            if (parts.length != 5) {
                throw new ShrekException("Invalid event format in file: " + line);
            }
            task = new Event(desc, parts[3], parts[4]);
            break;
        default:
            throw new ShrekException("Stinky onion (Corrupted task) in storage: " + line);
        }

        if (isDone) {
            task.markAsDone();
        }
        return task;
    }
}
