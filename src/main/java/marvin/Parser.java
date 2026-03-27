package marvin;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import marvin.command.AddTaskCommand;
import marvin.command.Command;
import marvin.command.DeleteTaskCommand;
import marvin.command.DoAfterCommand;
import marvin.command.ExitCommand;
import marvin.command.FindCommand;
import marvin.command.ListTaskCommand;
import marvin.command.MarkTaskCommand;
import marvin.command.UnknownCommand;
import marvin.task.Deadline;
import marvin.task.Event;
import marvin.task.Todo;

/**
 * Contains logic for parsing user input from the CLI.
 */
public class Parser {
    private static final DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

    /**
     * Parses an input and determines an action for the chatbot to run.
     *
     * @param command The input from the user who desires to complete a task.
     * @return A Command object suitable to be executed to perform the task.
     * @throws MarvinException Exception containing proper formatting for corrected command
     */
    public static Command parse(String command) {
        String initial = command.split(" ")[0];
        return switch (initial) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListTaskCommand();
        case "deadline" -> new AddTaskCommand(
                parseDeadline(command)
        );
        case "event" -> new AddTaskCommand(
                parseEvent(command)
        );
        case "todo" -> new AddTaskCommand(
                parseTodo(command)
        );
        case "mark", "unmark" -> parseMarkTask(command);
        case "delete" -> parseDeleteCommand(command);
        case "find" -> parseFindCommand(command);
        case "do" -> parseDoAfterCommand(command);
        default -> new UnknownCommand();
        };
    }

    private static FindCommand parseFindCommand(String command) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("find [query]")
        );

        validateCommand(command, formatError);
        String query = getUserInputParameter(command);
        assert (!query.isEmpty()) : "Query shouldn't be empty.";
        return new FindCommand(query);
    }

    private static DeleteTaskCommand parseDeleteCommand(String command) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("delete [index]")
        );

        validateCommand(command, formatError);
        String locator = getLocatorFromUserInput(command, 1, formatError);
        return new DeleteTaskCommand(locator);
    }

    private static MarkTaskCommand parseMarkTask(String command) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("mark/unmark [index]")
        );

        String[] parts = command.split(" ");
        validateCommand(command, formatError);
        assert (parts[0].equals("mark") || parts[0].equals("unmark")) : "Task identifier has to be mark or unmark";
        boolean isMark = parts[0].equalsIgnoreCase("mark");
        String locator = getLocatorFromUserInput(command, 1, formatError);
        return new MarkTaskCommand(locator, isMark);
    }

    private static DoAfterCommand parseDoAfterCommand(String command) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("do [index] /after [index]")
        );

        validateCommand(command, formatError);

        // Use Regex to extract multi-part user input
        Matcher matcher = matchUserInput(
                Pattern.compile(" (.*) /after (.*)"),
                command,
                formatError
        );
        String parentLocator = matcher.group(2);
        String subTaskLocator = matcher.group(1);

        return new DoAfterCommand(parentLocator, subTaskLocator);
    }

    private static Todo parseTodo(String text) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("todo [name]")
        );

        validateCommand(text, formatError);
        String name = getUserInputParameter(text);
        return new Todo(name);
    }

    private static Deadline parseDeadline(String text) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("deadline [name] /by dd/MM/yyyy hhmm")
        );

        // Use Regex to extract multi-part user input
        Matcher matcher = matchUserInput(
                Pattern.compile(" (.*) /by (.*)"),
                text,
                formatError
        );

        String description = matcher.group(1);
        LocalDateTime dueDate = parseDateFromString(matcher.group(2), formatError);

        return new Deadline(description, dueDate);
    }

    private static Event parseEvent(String text) {
        MarvinException formatError = new MarvinException(
                Personality.getInvalidFormatText("event [name] /from dd/MM/yyyy hhmm /to dd/MM/yyyy hhmm")
        );

        Matcher matcher = matchUserInput(
                Pattern.compile(" (.*) /from (.*) /to (.*)"),
                text,
                formatError
        );

        String description = matcher.group(1);
        LocalDateTime fromDate = parseDateFromString(matcher.group(2), formatError);
        LocalDateTime toDate = parseDateFromString(matcher.group(3), formatError);

        return new Event(description, fromDate, toDate);
    }

    private static void validateCommand(String command, MarvinException formatError) {
        String[] parts = command.split(" ");
        if (parts.length < 2) {
            throw formatError;
        }
    }

    /**
     * Parses user query after position 1
     * Example input: "find my very long query"
     * Return: "my very long query"
     *
     * @param command The full-text user command
     */
    private static String getUserInputParameter(String command) {
        String[] parts = command.split(" ");
        String[] nameArr = Arrays.stream(parts, 1, parts.length)
                .toArray(String[]::new);
        return String.join(" ", nameArr);
    }

    /**
     * Parses the resource locator in a given position
     * Example input: "find 1"
     * Return: 1
     *
     * @param command The full-text user command
     */
    private static String getLocatorFromUserInput(String command, int position, MarvinException formatError) {
        // do we still need this?
        String[] parts = command.split(" ");
        return parts[position];
    }

    private static Matcher matchUserInput(Pattern pattern, String command, MarvinException formatError) {
        Matcher matcher = pattern.matcher(command);
        if (!matcher.find()) {
            throw formatError;
        }
        return matcher;
    }

    private static LocalDateTime parseDateFromString(String input, MarvinException formatError) {
        LocalDateTime date;
        try {
            date = LocalDateTime.parse(
                    input,
                    FORMAT
            );
        } catch (DateTimeParseException e) {
            throw formatError;
        }

        return date;
    }
}
