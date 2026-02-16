package betty.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;

import betty.command.AddDeadlineCommand;
import betty.command.AddEventCommand;
import betty.command.AddTodoCommand;
import betty.command.ByeCommand;
import betty.command.Command;
import betty.command.DeleteCommand;
import betty.command.FindCommand;
import betty.command.ListCommand;
import betty.command.MarkTaskCommand;
import betty.command.SetPriorityCommand;
import betty.command.UnmarkTaskCommand;
import betty.exception.BettyException;
import betty.task.Deadline;
import betty.task.Event;
import betty.task.Priority;
import betty.task.Task;
import betty.task.Todo;

/**
 * Represents a parser class that can parse strings into other useful objects
 * Supports operation such as parseCommand, parseDate, parseTask
 */
public class Parser {
    // Create a list to store multiple formats of date
    private static final List<DateTimeFormatter> FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MMM dd yyyy")
    );

    /**
     * Parses a task from a string representation in storage into a task that can be added into task list
     * @param taskString String representation of task from storage file
     * @return task that has been parsed
     * @throws BettyException BettyException if error in parsing task
     */
    public static Task parseTask(final String taskString) throws BettyException {
        if (taskString == null || taskString.isEmpty()) {
            throw new BettyException("Task string cannot be null or empty");
        }
        String[] arguments = taskString.split(" \\| ", 10);
        String type = arguments[0];

        return switch (type) {
        case "T" -> parseTodo(arguments);
        case "D" -> parseDeadline(arguments);
        case "E" -> parseEvent(arguments);
        default -> throw new IllegalArgumentException("Unknown task type in file: " + type);
        };
    }

    private static Todo parseTodo(String[] args) throws BettyException {
        boolean isDone = parseIsDone(args[1]);
        String description = args[2];
        Priority priority = Priority.getPriority(args[3]);
        Todo todoTask = new Todo(description, isDone);
        setPriorityIfPresent(todoTask, priority);
        return todoTask;
    }

    private static Deadline parseDeadline(String[] args) throws BettyException {
        boolean isDone = parseIsDone(args[1]);
        String description = args[2];
        Priority priority = Priority.getPriority(args[3]);
        LocalDate deadline = parseDate(args[4]);
        Deadline deadlineTask = new Deadline(description, deadline, isDone);
        setPriorityIfPresent(deadlineTask, priority);
        return deadlineTask;
    }

    private static Event parseEvent(String[] args) throws BettyException {
        boolean isDone = parseIsDone(args[1]);
        String description = args[2];
        Priority priority = Priority.getPriority(args[3]);
        LocalDate from = parseDate(args[4]);
        LocalDate to = parseDate(args[5]);
        Event eventTask = new Event(description, from, to, isDone);
        setPriorityIfPresent(eventTask, priority);
        return eventTask;
    }

    private static boolean parseIsDone(String completed) {
        return "1".equals(completed);
    }
    private static void setPriorityIfPresent(Task task, Priority priority) {
        if (priority != Priority.NONE) {
            task.setPriority(priority);
        }
    }
    /**
     * Parse commands given by users into useful commands to be interpreted by the task manager object
     * @param command String representation of command inputted by user
     * @return a command to be executed by the task manager
     * @throws BettyException BettyException if there is error in parsing command
     */
    public static Command parseCommand(final String command) throws BettyException {
        if (command == null || command.isBlank()) {
            throw new BettyException("Command cannot be null or empty");
        }
        String[] arguments = command.split(" ", 2);
        String commandName = arguments[0];
        String commandArgs = arguments.length > 1 ? arguments[1] : "";
        Command.CommandName cm = Command.CommandName.fromString(commandName);

        return switch (cm) {
        case BYE -> new ByeCommand();
        case LIST -> new ListCommand();
        case MARK -> new MarkTaskCommand(parseTaskNum(commandArgs, "mark"));
        case UNMARK -> new UnmarkTaskCommand(parseTaskNum(commandArgs, "unmark"));
        case TODO -> {
            if (commandArgs.isBlank()) {
                throw new BettyException("Description missing for todo");
            }
            yield new AddTodoCommand(new Todo(commandArgs, false));
        }
        case DEADLINE -> parseDeadlineCommand(commandArgs);
        case EVENT -> parseEventCommand(commandArgs);
        case DELETE -> new DeleteCommand(parseTaskNum(commandArgs, "delete"));
        case FIND -> {
            if (commandArgs.isBlank()) {
                throw new BettyException("Please include description to find");
            }
            yield new FindCommand(commandArgs);
        }
        case PRIORITY -> parsePriorityCommand(commandArgs);
        default -> throw new BettyException("Unknown Command");
        };
    }
    private static int parseTaskNum(String arg, String action) throws BettyException {
        try {
            return Integer.parseInt(arg.trim());
        } catch (NumberFormatException e) {
            throw new BettyException("Please provide a valid number to " + action);
        }
    }

    private static Command parseDeadlineCommand(String commandArgs) throws BettyException {
        if (commandArgs.isBlank()) {
            throw new BettyException("Please include description and deadline for deadline task");
        }
        if (!commandArgs.contains("/by ")) {
            throw new BettyException("Deadline must have a /by <time>");
        }
        String[] info = commandArgs.split("/by ", 2);
        String description = info[0].trim();
        if (description.isEmpty()) {
            throw new BettyException("Please include description for deadline task");
        }
        LocalDate by = parseDate(info[1].trim());
        return new AddDeadlineCommand(new Deadline(description, by, false));
    }

    private static Command parseEventCommand(String commandArgs) throws BettyException {
        if (commandArgs.isBlank()) {
            throw new BettyException("Please include description and duration for event task");
        }
        if (!commandArgs.contains("/from ") || !commandArgs.contains("/to ")) {
            throw new BettyException("Event must have a /from <time> and /to <time>");
        }
        String[] info = commandArgs.split("/from ", 2);
        String description = info[0].trim();
        if (description.isEmpty()) {
            throw new BettyException("Please include description for event task");
        }
        String[] time = info[1].split(" /to ", 2);
        LocalDate from = parseDate(time[0].trim());
        LocalDate to = parseDate(time[1].trim());
        return new AddEventCommand(new Event(description, from, to, false));
    }

    private static Command parsePriorityCommand(String commandArgs) throws BettyException {
        if (commandArgs.isBlank()) {
            throw new BettyException("Please include a task number in list to set priority");
        }
        String[] info = commandArgs.split(" ", 2);
        if (info.length < 2 || info[1].isBlank()) {
            throw new BettyException("Please include a priority (low, medium, high) to set");
        }
        int taskNum = parseTaskNum(info[0], "set priority");
        String priorityStr = info[1].trim();
        if (!priorityStr.equalsIgnoreCase("low") && !priorityStr.equalsIgnoreCase("medium")
                && !priorityStr.equalsIgnoreCase("high")) {
            throw new BettyException("Please use these priority values only (low, medium, high)");
        }
        Priority priority = Priority.getPriority(priorityStr);
        return new SetPriorityCommand(taskNum, priority);
    }
    /**
     * Parses date string representation into a LocalDate object
     * @param date String representation of date provided by user
     * @return LocalDate object after string representation of date has been parsed
     */
    public static LocalDate parseDate(String date) throws BettyException {
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                return LocalDate.parse(date, formatter);
            } catch (DateTimeParseException e) {
                // Try the next formatter
            }
        }
        // if no format matches, invalid date format;
        throw new BettyException("Please input a valid date format: MM-DD-YYYY");
    }
}
