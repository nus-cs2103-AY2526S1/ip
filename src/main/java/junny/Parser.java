package junny;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import junny.Ui.Ui;
import junny.command.ByeCommand;
import junny.command.Command;
import junny.command.DeadlineCommand;
import junny.command.DeleteCommand;
import junny.command.EventCommand;
import junny.command.FindCommand;
import junny.command.ListCommand;
import junny.command.ListOnDateCommand;
import junny.command.MarkCommand;
import junny.command.SortCommand;
import junny.command.TodoCommand;
import junny.command.UnmarkCommand;


/**
 * Parses user input into commands that the program can execute.
 */
public class Parser {
    private Ui ui;

    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the given input string and returns the corresponding command.
     *
     * @param userCommand The command entered by the user
     * @return A Command object representing the parsed instruction
     */
    public Command parse(String userCommand) {
        assert userCommand != null : "Input to parser should not be null";
        String[] inputByParts = userCommand.split(" ", 2);
        String commandWord = inputByParts[0].toUpperCase();
        String commandDetail = inputByParts.length > 1 ? inputByParts[1] : "";
        CommandTypes command = null;

        try {
            command = CommandTypes.valueOf(commandWord);
        } catch (IllegalArgumentException e) {
            // handle exception 2
            ui.printError("I'm sorry, but I don't know what that means :(");
            return null;
        } catch (Exception e) {
            ui.printError("I'm sorry, but I don't know what that means :(");
            return null;
        }

        switch (command) {
        case BYE:
            return new ByeCommand();
        case LIST:
            return parseList(commandDetail);
        case MARK:
            return new MarkCommand(Integer.valueOf(commandDetail));
        case UNMARK:
            return new UnmarkCommand(Integer.valueOf(commandDetail));
        case DELETE:
            return new DeleteCommand(Integer.valueOf(commandDetail));
        case DEADLINE:
            return parseDeadline(commandDetail);
        case EVENT:
            return parseEvent(commandDetail);
        case TODO:
            return parseTodo(commandDetail);
        case FIND:
            return new FindCommand(commandDetail);
        case SORT:
            return new SortCommand();
        default:
            // handle exception 2
            ui.printError("I'm sorry, but I don't know what that means :(");
            return null;
        }

    }

    private Command parseTodo(String commandDetail) {
        try {
            String description = commandDetail.trim();
            if (description.isEmpty()) {
            throw new IllegalArgumentException(
                    "The description of a todo cannot be empty. Please follow: todo <task description>"
            );
        }
        return new TodoCommand(description);
        } catch (StringIndexOutOfBoundsException e) {
            // happens if commandDetail is shorter than 4 chars (e.g. just "todo")
            throw new IllegalArgumentException(
                    "The description of a todo cannot be empty.\n" +
                            "Please follow: todo <task description>"
            );
        }
    }

    private Command parseList(String commandDetail) {
        if (commandDetail.startsWith("/on")) {
            try {
                String dateStr = commandDetail.substring(3).trim(); // get the date string, which is 4th position
                LocalDate targetDate = LocalDate.parse(dateStr); // yyyy-MM-dd
                return new ListOnDateCommand(targetDate);
            } catch (Exception e) {
                ui.printError("Please enter the date in yyyy-MM-dd format.");
            }
        }
        return new ListCommand();
    }

    private Command parseDeadline(String commandDetail) {
        // split to "read book" & "Sunday"
        String[] parts = commandDetail.split("/by", 2);
        // throw exception 5
        if (parts.length < 2) {
            throw new IllegalArgumentException("deadline task must have a due time. "
                    + "Please follow deadline read /by yyyy-mm-dd.");
        }
        String description = parts[0].trim(); // "read book"
        String by = parts[1].trim(); // 2025-08-20
        if (!isValidDate(by)) {
            throw new IllegalArgumentException(
                    "Invalid date format: \"" + by + "\". Please use yyyy-mm-dd, e.g., 2025-09-20"
            );
        }
        assert !description.isEmpty() : "Deadline description should not be empty";
        return new DeadlineCommand(description, by);
    }

    private Command parseEvent(String commandDetail) {
        // split to "read book" & "/from xxx" (split on from)
        String[] parts1 = commandDetail.split("/from", 2);
        // throw exception 6
        if (parts1.length < 2) {
            throw new IllegalArgumentException("event task must have a from time. "
                    + "Please follow event read /from yyyy-mm-dd /to yyyy-mm-dd.");
        }
        String eventDescription = parts1[0].trim(); // "read book"
        String fromTo = parts1[1].trim(); // "/from xxx /to xxx"
        // split to "from" & "to"
        String[] parts2 = fromTo.split("/to", 2);
        if (parts2.length < 2) {
            throw new IllegalArgumentException("event task must have a to time. "
                    + "Please follow event read /from yyyy-mm-dd /to yyyy-mm-dd.");
        }
        String from = parts2[0].trim();
        String to = parts2[1].trim();
        if (!isValidDate(from) || !isValidDate(to)) {
            throw new IllegalArgumentException(
                    "Invalid date format." + " Please use yyyy-mm-dd, e.g., 2025-09-20"
            );
        }
        return new EventCommand(eventDescription, from, to);
    }

    private boolean isValidDate(String dateStr) {
        try {
            LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
