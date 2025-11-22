package watermelon;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import watermelon.command.Command;
import watermelon.command.DeadlineCommand;
import watermelon.command.DeleteCommand;
import watermelon.command.EventCommand;
import watermelon.command.FindCommand;
import watermelon.command.ListCommand;
import watermelon.command.MarkCommand;
import watermelon.command.ScheduleCommand;
import watermelon.command.TodoCommand;
import watermelon.command.UnmarkCommand;
import watermelon.exception.EmptyDateException;
import watermelon.exception.EmptyTaskDescriptionException;
import watermelon.exception.InvalidCommandException;
import watermelon.exception.InvalidDateTimeException;
import watermelon.exception.InvalidInputException;
import watermelon.exception.WatermelonException;

/**
 * Parses raw user input strings and converts them into {@link Command} objects for execution.
 *
 * <p>This class uses regular expressions to recognise
 * supported commands (list, mark, unmark, todo, deadline, event, delete).
 * Based on the match, it constructs the corresponding {@link Command}
 * subclass or throws a {@link WatermelonException} if the input is invalid.</p>
 */
public class Parser {
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructs a new {@code Parser} instance which uses the given task list and storage.
     *
     * @param taskList The {@link TaskList} on which commands will operate.
     * @param storage  The {@link Storage} used to save and load changes.
     * @param ui The {@link Ui} used to generate chatbot response messages.
     */
    public Parser(TaskList taskList, Storage storage, Ui ui) {
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Parses a user input string and returns the corresponding {@link Command}.
     *
     * @param input The full line of user input to parse.
     * @return A {@link Command} representing the user’s instruction.
     * @throws WatermelonException If the input is invalid, missing required fields,
     *                             refers to a non-existent task number, does not match required date format,
     *                             or does not match any known command.
     */
    public Command parseCommand(String input) throws WatermelonException {
        assert input != null : "input must not be null";

        // Used Claude to generate Matcher regex patterns for each command
        Matcher todo = Pattern.compile("^todo\\s*(.*)$").matcher(input);
        Matcher deadline = Pattern.compile(
                "^deadline\\s*([^/]*)(?:/\\s*by\\s*(.*))?$").matcher(input);
        Matcher event = Pattern.compile(
                        "^event\\s*([^/]*)(?:/\\s*from\\s*([^/]*))?(?:/\\s*to\\s*(.*))?$")
                .matcher(input);
        Matcher mark = Pattern.compile("^mark\\s*(.*)$").matcher(input);
        Matcher unmark = Pattern.compile("^unmark\\s*(.*)$").matcher(input);
        Matcher delete = Pattern.compile("^delete\\s*(.*)$").matcher(input);
        Matcher find = Pattern.compile("^find\\s*(.*)$").matcher(input);
        Matcher schedule = Pattern.compile("^schedule\\s*(.*)$").matcher(input);

        if (input.equals("list")) { // returns a ListCommand
            return new ListCommand(taskList, ui);
        } else if (mark.matches()) { // returns a MarkCommand
            return parseMarkCommand(mark);
        } else if (unmark.matches()) { // returns an UnmarkCommand
            return parseUnmarkCommand(unmark);
        } else if (todo.matches()) { // returns a TodoCommand
            return parseTodoCommand(todo);
        } else if (deadline.matches()) { // returns a DeadlineCommand
            return parseDeadlineCommand(deadline);
        } else if (event.matches()) { // returns a EventCommand
            return parseEventCommand(event);
        } else if (delete.matches()) { // returns a DeleteCommand
            return parseDeleteCommand(delete);
        } else if (find.matches()) { // returns a FindCommand
            return parseFindCommand(find);
        } else if (schedule.matches()) {
            return parseScheduleCommand(schedule);
        } else { // invalid command
            throw new InvalidCommandException();
        }
    }

    private Command parseMarkCommand(Matcher mark) throws WatermelonException {
        if (mark.group(1) == null || mark.group(1).isEmpty()) {
            throw new InvalidInputException("missing task number!");
        }
        if (!mark.group(1).trim().matches("-?\\d+")) {
            throw new InvalidInputException("not a valid integer!");
        }
        int taskNumber = Integer.parseInt(mark.group(1));
        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new InvalidInputException("not a valid task number!");
        }
        return new MarkCommand(taskList, taskNumber, storage, ui);
    }

    private Command parseUnmarkCommand(Matcher unmark) throws WatermelonException {
        if (unmark.group(1) == null || unmark.group(1).isEmpty()) {
            throw new InvalidInputException("missing task number!");
        }
        if (!unmark.group(1).trim().matches("-?\\d+")) {
            throw new InvalidInputException("not a valid integer!");
        }
        int taskNumber = Integer.parseInt(unmark.group(1));
        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new InvalidInputException("not a valid task number!");
        }
        return new UnmarkCommand(taskList, taskNumber, storage, ui);
    }

    private Command parseTodoCommand(Matcher todo) throws WatermelonException {
        if (todo.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("todo task description is empty!");
        }
        return new TodoCommand(taskList, todo.group(1).trim(), storage, ui);
    }

    private Command parseDeadlineCommand(Matcher deadline) throws WatermelonException {
        if (deadline.group(1) == null || deadline.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("deadline task description is empty!");
        }
        if (deadline.group(2) == null || deadline.group(2).isEmpty()) {
            throw new EmptyDateException();
        }
        return new DeadlineCommand(taskList, deadline.group(1).trim(), deadline.group(2), storage, ui);
    }

    private Command parseEventCommand(Matcher event) throws WatermelonException {
        if (event.group(1) == null || event.group(1).isEmpty()) {
            throw new EmptyTaskDescriptionException("event task description is empty!");
        }
        if (event.group(2) == null || event.group(2).isEmpty()) {
            throw new EmptyDateException("start date/time is missing!");
        }
        if (event.group(3) == null || event.group(3).isEmpty()) {
            throw new EmptyDateException("end date/time is missing!");
        }
        return new EventCommand(taskList, event.group(1).trim(), event.group(2).trim(), event.group(3), storage, ui);
    }

    private Command parseDeleteCommand(Matcher delete) throws WatermelonException {
        if (delete.group(1) == null || delete.group(1).isEmpty()) {
            throw new InvalidInputException("missing task number!");
        }
        if (!delete.group(1).trim().matches("-?\\d+")) {
            throw new InvalidInputException("not a valid integer!");
        }
        int taskNumber = Integer.parseInt(delete.group(1));
        if (taskNumber < 1 || taskNumber > taskList.getSize()) {
            throw new InvalidInputException("not a valid task number!");
        }
        return new DeleteCommand(taskList, taskNumber, storage, ui);
    }

    private Command parseFindCommand(Matcher find) throws WatermelonException {
        if (find.group(1) == null || find.group(1).isBlank()) {
            throw new InvalidInputException("missing keyword!");
        }
        return new FindCommand(taskList, find.group(1).trim(), ui);
    }

    private Command parseScheduleCommand(Matcher schedule) throws InvalidDateTimeException {
        if (schedule.group(1) == null || schedule.group(1).isBlank()) {
            return new ScheduleCommand(taskList, ui);
        }
        LocalDate date = stringToDate(schedule.group(1).trim());
        return new ScheduleCommand(taskList, date, ui);
    }

    /**
     * Converts a string with "ddMMyyyy" format into a {@link LocalDate}.
     * @param input String representing a date
     * @return A {@link LocalDate} representing the specified date.
     * @throws DateTimeParseException If input does not match the "ddMMyyyy" pattern.
     */
    private LocalDate stringToDate(String input) throws InvalidDateTimeException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
            LocalDate date = LocalDate.parse(input, formatter);
            return date;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException(
                    "Invalid date format. Please use ddMMyyyy format! (Eg. 25092025)");
        }
    }
}
