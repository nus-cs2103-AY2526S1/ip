package itachi;

import itachi.command.Command;
import itachi.command.DeadlineCommand;
import itachi.command.DeleteCommand;
import itachi.command.EventCommand;
import itachi.command.ExitCommand;
import itachi.command.FindCommand;
import itachi.command.ListCommand;
import itachi.command.MarkCommand;
import itachi.command.ToDoCommand;
import itachi.command.UndoCommand;
import itachi.command.UnmarkCommand;

/**
 * Utility class for parsing user input into {@link Command} objects.
 * This class handles splitting the input string, validating its format,
 * and delegating to the appropriate command constructor based on the command keyword.
 * Assertions are used to enforce internal invariants.
 */
public class Parser {

    /**
     * Parses a user input string into a corresponding {@link Command}.
     * <p>
     * The input is expected to begin with a valid command keyword (e.g., "todo", "deadline", "event"),
     * optionally followed by arguments. Throws {@link ItachiException} if the input does not
     * correspond to a valid command or if required arguments are missing/invalid.
     * </p>
     * @param input the raw string entered by user
     * @return object representing the parsed action
     * @throws ItachiException if the input is invalid or command specific errors occur
     */
    public static Command parse(String input) throws ItachiException {
        assert input != null && !input.isEmpty() : "Input command can't be null or empty";
        String[] words = input.split(" ", 2);
        assert words.length > 0 : "Splitting input should always produce at least one word";
        String command = words[0].toLowerCase().trim();
        assert !command.isEmpty() : "Command keyword should never be empty";
        switch (command) {
        case "todo":
            return parseTodo(words);
        case "deadline":
            return parseDeadline(words);
        case "event":
            return parseEvent(words);
        case "list":
            return new ListCommand();
        case "bye":
            return new ExitCommand();
        case "mark":
            return parseMark(words);
        case "unmark":
            return parseUnmark(words);
        case "delete":
            return parseDelete(words);
        case "find":
            return parseFind(words);
        case "undo":
            return new UndoCommand();
        default:
            throw new ItachiException("No such commands!");
        }
    }

    private static Command parseTodo(String[] words) throws ItachiException {
        assert words != null && words.length > 0 : "Words array should never be null or empty";
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new ItachiException("you not gonna key in what u wanna do?");
        }
        String toDoDesc = words[1].trim();
        assert toDoDesc != null : "Todo description should not be null";
        return new ToDoCommand(toDoDesc);
    }

    private static Command parseDeadline(String[] words) throws ItachiException {
        if (words.length < 2 || !words[1].contains("/by")) {
            throw new ItachiException("no empty messages and please write '/by' followed by the deadline");
        }
        String[] deadlineParts = words[1].split("/by", 2);
        assert deadlineParts.length == 2 : "Deadline must split into description and date";
        String deadlineDesc = deadlineParts[0].trim();
        String by = deadlineParts[1].trim();
        if (deadlineDesc.isEmpty() || by.isEmpty()) {
            throw new ItachiException("Write what you gonna do and the deadline");
        }
        return new DeadlineCommand(deadlineDesc, by);
    }

    private static Command parseEvent(String[] words) throws ItachiException {
        if (words.length < 2) {
            throw new ItachiException("Event command cannot be empty!");
        }
        String[] parts = words[1].split("/from|/to");
        if (parts.length != 3) { // description + from + to
            throw new ItachiException("Event must have description, '/from' date, and '/to' date!");
        }
        String description = parts[0].trim();
        String from = parts[1].trim();
        String to = parts[2].trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new ItachiException("Description, '/from' date, and '/to' date cannot be empty!");
        }
        return new EventCommand(description, from, to);
    }

    private static Command parseMark(String[] words) throws ItachiException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new ItachiException("Mark command must be in the format: mark <tasknumber>");
        }
        try {
            int taskNum = Integer.parseInt(words[1].trim()) - 1;
            assert taskNum >= 0 : "Task number must be non negative";
            return new MarkCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new ItachiException("Task number must be integer!");
        }
    }

    private static Command parseUnmark(String[] words) throws ItachiException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new ItachiException("Mark command must be in the format: mark <tasknumber>");
        }
        try {
            int taskIndex = Integer.parseInt(words[1].trim()) - 1;
            assert taskIndex >= 0 : "Task number must be non negative";
            return new UnmarkCommand(taskIndex);
        } catch (NumberFormatException e) {
            throw new ItachiException("Task number must be integer!");
        }
    }

    private static Command parseDelete(String[] words) throws ItachiException {
        if (words.length < 2) {
            throw new ItachiException("Please specify task number u wanna delete");
        }
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(words[1]);
            assert taskNumber >= 0 : "Task number must be non negative";
        } catch (NumberFormatException e) {
            throw new ItachiException("Task number must be integer!");
        }
        return new DeleteCommand(taskNumber);
    }

    private static Command parseFind(String[] words) throws ItachiException {
        if (words.length < 2 || words[1].trim().isEmpty()) {
            throw new ItachiException("The find command requires a keyword!");
        }
        String keyWord = words[1].trim();
        assert keyWord != null && !keyWord.isEmpty() : "Keyword can't be null or empty";
        return new FindCommand(keyWord);
    }
}
