package kleebot.ui;

import kleebot.command.*;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Parser class 
 */
public class Parser {

    public static Command parse(String fullCommand) throws KleeExceptions {
        String[] parts = fullCommand.split("\\s+");
//        String taskDescription;
        assert parts.length > 0 : "Command parts should not be empty";
        switch (parts[0]) {
        case "bye":
            return new ExitCommand();
        case "todo":
            return handleTodo(parts);
        case "deadline":
            return handleDeadline(parts);
        case "event":
            return handleEvent(parts);
        case "list":
            return new ListCommand();
        case "mark":
            return new MarkCommand(parts);
        case "unmark":
            return new UnmarkCommand(parts);
        case "delete":
            return new DeleteCommand(parts[1]);
        case "find":
            if (parts.length == 1) {
                throw new KleeExceptions(Ui.ErrorMessage.MISSING_SEARCH_TERM.getMessage());
            }
            return new FindCommand(parts[1]);
        case "setPriority":
            if (parts.length == 1) {
                throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
            } else if (parts.length == 2) {
                throw new KleeExceptions(Ui.ErrorMessage.MISSING_PRIORITY.getMessage());
            }
            return new SetpriorityCommand(Integer.parseInt(parts[1]) - 1, Integer.parseInt(parts[2]));
        case "echo":
            return new EchoCommand(fullCommand);
        default:
            return new UnknownCommand();
        }

    }



    private static Command handleTodo(String[] parts) throws KleeExceptions {
        if (parts.length <= 1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
        }
        String taskDescription = extractString(parts, 1, parts.length);
        return new TodoCommand(taskDescription);
    }

    private static Command handleDeadline(String[] parts) throws KleeExceptions {
        if (parts.length <= 1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
        }

        int byIndex = findParamIndex(parts, "/by");

        if (byIndex == -1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY.getMessage());
        }
        if (byIndex == parts.length - 1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_BY_2.getMessage());
        }

        String taskDescription = extractString(parts, 1, byIndex);
        String by = extractString(parts, byIndex + 1, parts.length);
        String dated_by = Parser.parseDateStr(by); // returns the string format in MMM dd yyyy if the input is a valid date
        return new DeadlineCommand(taskDescription, dated_by);
    }

    private static Command handleEvent(String[] parts) throws KleeExceptions {
        if (parts.length <= 1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_DETAILS.getMessage());
        }
        int fromIndex = findParamIndex(parts, "/from");
        if (fromIndex == -1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_FROM.getMessage());
        }
        // test is separated from others bc the toIndex stream uses fromIndex

        int toIndex = findParamIndex(parts, "/to", fromIndex);
        if (toIndex == -1) {
            throw new KleeExceptions(Ui.ErrorMessage.MISSING_TO.getMessage());
        }
        if (fromIndex == toIndex - 1) {
            throw new KleeExceptions("Gimmie more info on when it starts!!");
        }
        if (toIndex == parts.length - 1) {
            throw new KleeExceptions("Gimmie more info on when it ends!!");
        }

        String taskDescription = extractString(parts, 1, fromIndex);
        String from = extractString(parts, fromIndex + 1, toIndex);
        String to = extractString(parts, toIndex + 1, parts.length);

        String dated_from = Parser.parseDateStr(from);
        String dated_to = Parser.parseDateStr(to);
        return new EventCommand(taskDescription, dated_from, dated_to);
    }

    public static String parseDateStr(String str) {
        assert str != null : "Date string cannot be null";
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .append(DateTimeFormatter.ofPattern("[MM/dd/yyyy]" + "[yyyy/MM/dd]" + "[dd-MM-yyyy]" + "[yyyy-MM-dd]"))
                .toFormatter();
        LocalDate date;
        try {
            date = LocalDate.parse(str, formatter);
        } catch (DateTimeParseException e) {
            return str;
        }
        return date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    private static int findParamIndex(String[] parts, String param) {
        return IntStream.range(0, parts.length)
                .filter(i -> parts[i].equals(param))
                .findFirst()
                .orElse(-1);
    }

    private static int findParamIndex(String[] parts, String param, int fromIndex) {
        return IntStream.range(fromIndex, parts.length)
                .filter(i -> parts[i].equals(param))
                .findFirst()
                .orElse(-1);
    }

    private static String extractString(String[] parts, int fromIndex, int toIndex) {
        return Arrays.stream(parts, fromIndex, toIndex)
                .reduce("", (a, b) -> a + " " + b)
                .trim();
    }

}