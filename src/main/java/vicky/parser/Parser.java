package vicky.parser;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import vicky.command.*;
import vicky.exception.InvalidInputException;
import vicky.exception.InvalidTaskException;

/**
 * Class responsible for parsing user input and managing commands.
 *
 * @author Rachel Wong
 */
public class Parser {

    /** Input date-time format for parsing user input. */
    public static final DateTimeFormatter INPUT_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy HHmm");
    /** Output date-time format for displaying parsed data. */
    public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

    /**
     * Parses a string into a LocalDateTime object based on the input format.
     *
     * @param s The input string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws InvalidInputException if the input string is in an invalid format.
     */
    public static LocalDateTime parseInputString(String s) throws InvalidInputException {
        try {
            return LocalDateTime.parse(s, INPUT_FORMAT);
        } catch (DateTimeException e) {
            throw new InvalidInputException("Invalid date and time format!\nAccepted formats are: ddMMyyyy HHmm.");
        }
    }


    /**
     * Parses a string into a LocalDateTime object based on the output format.
     *
     * @param s The input string to be parsed.
     * @return The parsed LocalDateTime object.
     * @throws DateTimeException if the input string is in an invalid format.
     */
    public static LocalDateTime parseOutputString(String s) throws DateTimeException {
        return LocalDateTime.parse(s, OUTPUT_FORMAT);
    }

    //CHECKSTYLE.OFF: Indentation
    /**
     * Parses a full command string into a corresponding Command object.
     * The method splits the command string into the command type and its arguments,
     * then processes the command accordingly, returning the corresponding Command object.
     *
     * @param fullCommand The full user command string.
     * @return The corresponding Command object based on the parsed command.
     * @throws InvalidTaskException if the task in the command is invalid.
     * @throws InvalidInputException if the input arguments are invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    public static Command parse(String fullCommand) throws InvalidTaskException, InvalidInputException,
            DateTimeException, DateTimeParseException {
        String[] words = fullCommand.split(" ", 2);
        String command = words[0];
        String arguments = words.length > 1 ? words[1] : "";

        switch (command.toLowerCase()) {
            case "list":
                return new ListCommand();
            case "unmark":
                return parseUnmark(arguments);
            case "mark":
                return parseMark(arguments);
            case "delete":
                return parseDelete(arguments);
            case "todo", "deadline", "event":
                return parseTask(command, arguments);
            case "find":
                return parseFind(arguments);
            case "clear":
                return new ClearAllTasksCommand();
            case "love":
                return parseLove(arguments);
            case "change":
                return parseChange(arguments);
            case "bye":
                return parseBye(arguments);
            default:
                throw new InvalidInputException("Bitch I don't know what that means. :( ");
        }

    }

    /**
     * Parses the "unmark" command arguments and returns an UnmarkTaskCommand object.
     * The method checks that the arguments contain a valid task number and returns a command to unmark the task.
     *
     * @param arguments The arguments string containing the task number to be unmarked.
     * @return The corresponding UnmarkTaskCommand object.
     * @throws InvalidTaskException if the task number is missing or invalid.
     */
    private static UnmarkTaskCommand parseUnmark(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Unmark requires a task number!");
        }
        try {
            int num = Integer.parseInt(arguments) - 1;
            return new UnmarkTaskCommand(num);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }
    }

    /**
     * Parses the "mark" command arguments and returns a MarkTaskCommand object.
     * The method checks that the arguments contain a valid task number and returns a command to mark the task.
     *
     * @param arguments The arguments string containing the task number to be marked.
     * @return The corresponding MarkTaskCommand object.
     * @throws InvalidTaskException if the task number is missing or invalid.
     */
    private static MarkTaskCommand parseMark(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Mark requires a task number!");
        }
        try {
            int num = Integer.parseInt(arguments) - 1;
            return new MarkTaskCommand(num);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }
    }

    /**
     * Parses the "delete" command arguments and returns a DeleteTaskCommand object.
     * The method checks that the arguments contain a valid task number and returns a command to delete the task.
     *
     * @param arguments The arguments string containing the task number to be deleted.
     * @return The corresponding DeleteTaskCommand object.
     * @throws InvalidTaskException if the task number is missing or invalid.
     */
    private static DeleteTaskCommand parseDelete(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Delete requires a task number!");
        }
        try {
            int num = Integer.parseInt(arguments) - 1;
            return new DeleteTaskCommand(num);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }
    }

    /**
     * Parses the "find" command arguments and returns a FindTasksCommand object.
     * The method checks that the arguments contain a valid keyword and returns a command to find tasks based on the keyword.
     *
     * @param arguments The arguments string containing the keyword to search for.
     * @return The corresponding FindTasksCommand object.
     * @throws InvalidTaskException if the keyword is missing or invalid.
     */
    private static FindTasksCommand parseFind(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Find requires a keyword!");
        }
        return new FindTasksCommand(arguments);
    }

    /**
     * Parses the "love" command arguments and returns a LoveCommand object.
     * The method constructs a command that expresses love for the specified target or the default "you".
     *
     * @param arguments The arguments string containing the person or entity to express love for.
     * @return The corresponding LoveCommand object with the target to love.
     */
    private static LoveCommand parseLove(String arguments) {
        if (arguments.isEmpty()) {
            return new LoveCommand("you");
        }
        return new LoveCommand(arguments);
    }

    /**
     * Parses the "bye" command arguments and returns a GoodbyeCommand or DesperateGoodbyeCommand object.
     * The method checks whether arguments are provided to determine the type of goodbye command.
     *
     * @param arguments The arguments string containing a possible additional detail for the goodbye message.
     * @return The corresponding GoodbyeCommand or DesperateGoodbyeCommand object based on the arguments.
     */
    private static Command parseBye(String arguments) {
        if (arguments.isEmpty()) {
            return new GoodbyeCommand();
        }
        return new DesperateGoodbyeCommand();
    }

    /**
     * Parses an arguments string into a corresponding task Command object.
     *
     * @param command The command string specifying the task type.
     * @param arguments The arguments string of the task.
     * @return The corresponding Command object based on the parsed command.
     * @throws InvalidTaskException if the task in the command is invalid.
     * @throws InvalidInputException if the input arguments are invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    public static Command parseTask(String command, String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        switch (command.toLowerCase()) {
            case "todo":
                return parseTodoTask(arguments);
            case "deadline":
                return parseDeadlineTask(arguments);
            case "event":
                return parseEventTask(arguments);
            default:
                throw new InvalidTaskException("Invalid task.");
        }
    }
    //CHECKSTYLE.ON: Indentation

    /**
     * Parses the arguments for a "todo" task and returns an AddTodoCommand object.
     * This method checks that the description is not empty and constructs a command to add the todo task.
     *
     * @param arguments The arguments string containing the description of the todo task.
     * @return The corresponding AddTodoCommand object.
     * @throws InvalidTaskException if the todo description is missing or invalid.
     */
    private static AddTodoCommand parseTodoTask(String arguments) throws InvalidTaskException {
        if (arguments.isEmpty()) {
            throw new InvalidTaskException("Missing todo description.");
        }
        return new AddTodoCommand(arguments);
    }

    /**
     * Parses the arguments for a "deadline" task and returns an AddDeadlineCommand object.
     * This method checks that both the description and deadline time are provided and constructs a command to add the deadline task.
     *
     * @param arguments The arguments string containing the description and deadline time of the task.
     * @return The corresponding AddDeadlineCommand object with the parsed description and deadline time.
     * @throws InvalidTaskException if the description or deadline time is missing or invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    private static AddDeadlineCommand parseDeadlineTask(String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        String[] temp = arguments.split(" /by ");
        if (temp.length != 2) {
            throw new InvalidTaskException("Invalid deadline task.");
        } else {
            String description = temp[0];
            String by = temp[1];
            if (description.isEmpty()) {
                throw new InvalidTaskException("Missing deadline description.");
            }
            if (by.isEmpty()) {
                throw new InvalidTaskException("Missing deadline time.");
            }
            LocalDateTime dateTime = parseInputString(by);

            return new AddDeadlineCommand(description, dateTime);
        }
    }

    /**
     * Parses the arguments for an "event" task and returns an AddEventCommand object.
     * This method checks that both the description and event start and end times are provided and constructs a command to add the event task.
     *
     * @param arguments The arguments string containing the description, start time, and end time of the event.
     * @return The corresponding AddEventCommand object with the parsed description, start time, and end time.
     * @throws InvalidTaskException if the description, start time, or end time is missing or invalid.
     * @throws DateTimeException if date-time parsing fails.
     * @throws DateTimeParseException if the date-time format is incorrect.
     */
    private static AddEventCommand parseEventTask(String arguments) throws InvalidTaskException, DateTimeException,
            DateTimeParseException {
        String[] temp = arguments.split(" /from ");
        if (temp.length != 2) {
            throw new InvalidTaskException("Invalid event task.");
        } else {
            String description = temp[0];
            if (description.isEmpty()) {
                throw new InvalidTaskException("Missing event description.");
            }
            String[] eventTime = temp[1].split(" /to ");
            if (eventTime.length != 2) {
                throw new InvalidTaskException("Invalid event time.");
            } else {
                String from = eventTime[0];
                String by = eventTime[1];
                if (from.isEmpty()) {
                    throw new InvalidTaskException("Missing event start time.");
                }
                if (by.isEmpty()) {
                    throw new InvalidTaskException("Missing event end time.");
                }

                LocalDateTime start = parseInputString(from);
                LocalDateTime end = parseInputString(by);
                if (start.isAfter(end)) {
                    throw new InvalidInputException("What kind of event starts after it ends?");
                }
                return new AddEventCommand(description, start, end);
            }
        }
    }

    /**
     * Parses the "change" command arguments to create a specific ChangeTaskCommand.
     *
     * @param str The arguments for the change command.
     * @return The appropriate ChangeTaskCommand based on the type of change requested.
     * @throws InvalidInputException if the change arguments are invalid.
     * @throws DateTimeException if parsing new date-time values fails.
     * @throws DateTimeParseException if date-time strings are incorrectly formatted.
     */
    private static Command parseChange(String str) throws InvalidInputException,
            DateTimeException, DateTimeParseException {

        if (str.isEmpty()) {
            throw new InvalidTaskException("What do you want me to change? " +
                    "Use your words and communicate like an adult.");
        }

        // Getting command
        String[] words = str.split("\\s", 2);
        String command = words[0];

        // Check that str contains command and arguments
        if (words.length != 2) {
            throw new InvalidTaskException("Change requires a task number!");
        }

        // Getting arguments
        String[] arguments = words[1].split("\\s/\\s");
        if (arguments.length < 2) {
            throw new InvalidInputException("Change requires arguments!");
        }

        // Getting task index
        int num;
        try {
            num = Integer.parseInt(arguments[0]) - 1;
        } catch (NumberFormatException e) {
            throw new InvalidTaskException("Invalid task number!");
        }

        switch (command.toLowerCase()) {
            case "name":
                return parseChangeName(num, arguments);
            case "end":
                return parseChangeEndTime(num, arguments);
            case "start":
                return parseChangeStartTime(num, arguments);
            case "event":
                return parseChangeEventTime(num, arguments);
            default:
                throw new InvalidInputException("Invalid change command!");
        }

    }

    /**
     * Parses arguments to change a task's name.
     *
     * @param num The index of the task to change.
     * @param arguments The new task name.
     * @return A ChangeTaskNameCommand for the specified task.
     * @throws InvalidInputException if the new name is invalid.
     */
    private static ChangeTaskNameCommand parseChangeName(int num, String[] arguments) throws InvalidInputException {
        String name = arguments[1];
        if (name == null || name.isEmpty()) {
            throw new InvalidInputException("Invalid name!");
        }
        return new ChangeTaskNameCommand(num, name);
    }

    /**
     * Parses arguments to change a task's end time.
     *
     * @param num The index of the task to change.
     * @param arguments The new end time as a string.
     * @return A ChangeTaskEndTimeCommand for the specified task.
     * @throws InvalidInputException if the new time is invalid.
     * @throws DateTimeException if parsing fails.
     * @throws DateTimeParseException if the string is incorrectly formatted.
     */
    private static ChangeTaskEndTimeCommand parseChangeEndTime(int num, String[] arguments) throws
            InvalidInputException, DateTimeException, DateTimeParseException {
        String s = arguments[1];
        if (s == null || s.isEmpty()) {
            throw new InvalidInputException("Invalid end time!");
        }
        LocalDateTime by = parseInputString(s);
        return new ChangeTaskEndTimeCommand(num, by);
    }

    /**
     * Parses arguments to change a task's start time.
     *
     * @param num The index of the task to change.
     * @param arguments The new start time as a string.
     * @return A ChangeTaskStartTimeCommand for the specified task.
     * @throws InvalidInputException if the new time is invalid.
     * @throws DateTimeException if parsing fails.
     * @throws DateTimeParseException if the string is incorrectly formatted.
     */
    private static ChangeTaskStartTimeCommand parseChangeStartTime(int num, String[] arguments) throws
            InvalidInputException, DateTimeException, DateTimeParseException {
        String s = arguments[1];
        if (s == null || s.isEmpty()) {
            throw new InvalidInputException("Invalid start time!");
        }
        LocalDateTime from = parseInputString(s);
        return new ChangeTaskStartTimeCommand(num, from);
    }

    /**
     * Parses arguments to change an event task's start and end times.
     *
     * @param num The index of the task to change.
     * @param arguments The new start and end times.
     * @return A ChangeEventTimeCommand for the specified event task.
     * @throws InvalidInputException if the times are missing or invalid.
     * @throws DateTimeException if parsing fails.
     * @throws DateTimeParseException if the strings are incorrectly formatted.
     */
    private static ChangeEventTimeCommand parseChangeEventTime(int num, String[] arguments) throws
            InvalidInputException, DateTimeException, DateTimeParseException {
        if (arguments.length != 3) {
            throw new InvalidInputException("Changing event time requires a start and end time!");
        }
        String startStr = arguments[1];
        String endStr = arguments[2];
        if (startStr == null || startStr.isEmpty()) {
            throw new InvalidInputException("Start time is required!");
        }
        if (endStr == null || endStr.isEmpty()) {
            throw new InvalidInputException("End time is required!");
        }
        LocalDateTime from = parseInputString(startStr);
        LocalDateTime by = parseInputString(endStr);
        return new ChangeEventTimeCommand(num, from, by);
    }

}
