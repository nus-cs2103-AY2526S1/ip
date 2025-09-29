package johnny.parser;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import johnny.tasklist.TaskList;
import johnny.commands.ByeCommand;
import johnny.commands.Command;
import johnny.commands.DeadlineCommand;
import johnny.commands.DeleteCommand;
import johnny.commands.ErrorCommand;
import johnny.commands.EventCommand;
import johnny.commands.FindCommand;
import johnny.commands.HelpCommand;
import johnny.commands.ListCommand;
import johnny.commands.MarkCommand;
import johnny.commands.PeriodCommand;
import johnny.commands.TodoCommand;
import johnny.commands.UnmarkCommand;
import johnny.storage.Storage;
import johnny.ui.Ui;

/**
 * A helper class that provides useful methods to parse a command/date
 */
public class Parser {
    /**
     * Returns a Command based off the user input
     * 
     * @param input   User input read
     * @param storage Storage for reading and writing into the save file
     * @param tasks   Task List for editing
     * @param ui      UI object that prints out messages
     * @return A command that can be executed, or null otherwise if the input cannot
     *         be parsed
     */
    public static Command read(String input, Storage storage, TaskList tasks, Ui ui) {
        assert input != null : "Input by user cannot be null";
        assert storage != null : "Storage cannot be null";
        assert tasks != null : "Task List cannot be null";
        assert ui != null : "UI cannot be null";

        if (input == null) {
            String msg = "Null input.";
            return new ErrorCommand(msg);
        } else if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("mark ") || input.startsWith("unmark ")) {
            return Parser.parseMark(input, ui);
        } else if (input.startsWith("todo ")) {
            return Parser.parseTodo(input, ui);
        } else if (input.startsWith("deadline ")) {
            return Parser.parseDeadline(input, ui);
        } else if (input.startsWith("event ")) {
            return Parser.parseEvent(input, ui);
        } else if (input.startsWith("delete ")) {
            return Parser.parseDelete(input, ui);
        } else if (input.startsWith("find ")) {
            return Parser.parseFind(input, ui);
        } else if (input.startsWith("period ")) {
            return Parser.parsePeriod(input, ui);
        } else {
            String msg = "Invalid command.";
            return new ErrorCommand(msg);
        }
    }

    /**
     * Returns a find command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A find command if it can be parsed, null otherwise
     */
    public static Command parseFind(String input, Ui ui) {
        String[] strings = input.split(" ");
        if (strings.length < 2) {
            String msg = ui.printNoTaskNameError();
            return new ErrorCommand(msg);
        }

        String searchString = input.substring(5);
        return new FindCommand(searchString);
    }

    /**
     * Returns a delete command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A delete commmand if it can be parsed, null otherwise
     */
    public static Command parseDelete(String input, Ui ui) {
        String[] strings = input.split(" ");
        if (strings.length != 2) {
            String msg = ui.printNoNumberError();
            return new ErrorCommand(msg);
        }

        int num = 0;

        try {
            num = Integer.parseInt(strings[1]);
            num = num - 1;
        } catch (NumberFormatException e) {
            String msg = ui.printInvalidNumberError();
            return new ErrorCommand(msg);
        }

        return new DeleteCommand(num);
    }

    /**
     * Returns a event command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A event commmand if it can be parsed, null otherwise
     */
    public static Command parseEvent(String input, Ui ui) {
        String errorMsg = "Invalid event name / date / time specified.\n" +
                "Please use the format: event [task name] /from [start time] /to [end time]";
        String[] firstParse = input.split("/to ");
        if (firstParse.length != 2) {
            return new ErrorCommand(errorMsg);
        }

        String[] secondParse = firstParse[0].split("/from ");
        if (secondParse.length != 2) {
            return new ErrorCommand(errorMsg);
        }

        String[] eventName = secondParse[0].split(" ");
        if (eventName.length < 2) {
            return new ErrorCommand(errorMsg);
        }

        String taskName = Parser.parseName(eventName);

        LocalDateTime start = Parser.parseDateTime(secondParse[1].trim(), ui);
        LocalTime end = Parser.parseTime(firstParse[1], ui);

        if (start != null && end != null) {
            return new EventCommand(taskName, start, end);
        } else {
            return new ErrorCommand(errorMsg);
        }
    }

    /**
     * Returns a Period command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A period command if it can be parsed, Error command otherwise.
     */
    public static Command parsePeriod(String input, Ui ui) {
        String errorMsg = "Invalid task name / date / time specified.\n" +
                "Please use the format: period [task name] /between [start time] /and [end time]";
        String[] firstParse = input.split("/and ");
        if (firstParse.length != 2) {
            return new ErrorCommand(errorMsg);
        }

        String[] secondParse = firstParse[0].split("/between ");
        if (secondParse.length != 2) {
            return new ErrorCommand(errorMsg);
        }

        String[] eventName = secondParse[0].split(" ");
        if (eventName.length < 2) {
            return new ErrorCommand(errorMsg);
        }

        String taskName = Parser.parseName(eventName);

        LocalDate start = Parser.parseDate(secondParse[1].trim(), ui);
        LocalDate end = Parser.parseDate(firstParse[1].trim(), ui);
        if (start != null && end != null) {
            return new PeriodCommand(taskName, start, end);
        } else {
            return new ErrorCommand(errorMsg);
        }
    }

    /**
     * Returns a deadline command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A deadline commmand if it can be parsed, null otherwise
     */
    public static Command parseDeadline(String input, Ui ui) {
        String[] strings = input.split("/by ");

        if (strings.length != 2) {
            String msg = ui.printDeadlineError();
            return new ErrorCommand(msg);
        }

        String[] firstHalf = strings[0].split(" ");
        if (firstHalf.length < 2) {
            String msg = ui.printNoTaskNameError();
            return new ErrorCommand(msg);
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < firstHalf.length; i++) {
            taskName.append(firstHalf[i]);
            if (i < firstHalf.length - 1) {
                taskName.append(" ");
            }
        }

        String[] secondHalf = strings[1].trim().split("/");
        if (secondHalf.length != 3) {
            String msg = ui.printDateError();
            return new ErrorCommand(msg);
        }

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate deadline = LocalDate.parse(strings[1].trim(), formatter);
            return new DeadlineCommand(taskName.toString(), deadline);
        } catch (DateTimeException e) {
            String msg = ui.printDateTimeException(e);
            return new ErrorCommand(msg);
        }
    }

    /**
     * Returns a todo command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A todo commmand if it can be parsed, null otherwise
     */
    public static Command parseTodo(String input, Ui ui) {
        String[] strings = input.split(" ");

        if (strings.length < 2) {
            String msg = ui.printNoTaskNameError();
            return new ErrorCommand(msg);
        }

        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < strings.length; i++) {
            taskName.append(strings[i]);
            if (i < strings.length - 1) {
                taskName.append(" ");
            }
        }

        return new TodoCommand(taskName.toString());
    }

    /**
     * Returns a mark or unmark command after parsing the user input
     * 
     * @param input
     * @param ui
     * @return A mark or unmark commmand if it can be parsed, null otherwise
     */
    public static Command parseMark(String input, Ui ui) {
        String[] strings = input.split(" ");

        if (strings.length != 2) {
            String msg = ui.printNoNumberError();
            return new ErrorCommand(msg);
        }

        int num = 0;

        try {
            num = Integer.parseInt(strings[1]);
            num = num - 1;
        } catch (NumberFormatException e) {
            String msg = ui.printInvalidNumberError();
            return new ErrorCommand(msg);
        }

        if (strings[0].equals("mark")) {
            return new MarkCommand(num);
        } else {
            return new UnmarkCommand(num);
        }
    }

    private static String parseName(String[] eventName) {
        StringBuilder taskName = new StringBuilder();
        for (int i = 1; i < eventName.length; i++) {
            taskName.append(eventName[i]);
            if (i < eventName.length - 1) {
                taskName.append(" ");
            }
        }

        return taskName.toString();
    }

    /**
     * Returns a LocalDate from the string input. If the input cannot be parsed,
     * returns null
     * 
     * @param input String representation of the date
     * @param ui
     * @return date
     */
    public static LocalDate parseDate(String input, Ui ui) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }

    /**
     * Returns a LocalDateTime from the string input. If the input cannot be parsed,
     * returns null
     * 
     * @param input String representation of the datetime
     * @param ui
     * @return date and time
     */
    public static LocalDateTime parseDateTime(String input, Ui ui) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        try {
            LocalDateTime dateTime = LocalDateTime.parse(input, formatter);
            return dateTime;
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }

    /**
     * Returns a LocalTime from the string input. If the input cannot be parsed,
     * returns null
     * 
     * @param input String representation of the time
     * @param ui
     * @return time
     */
    public static LocalTime parseTime(String input, Ui ui) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        try {
            LocalTime time = LocalTime.parse(input, formatter);
            return time;
        } catch (DateTimeException e) {
            ui.printDateTimeException(e);
            return null;
        }
    }
}
