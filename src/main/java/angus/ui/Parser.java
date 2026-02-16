package angus.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import angus.command.ByeCommand;
import angus.command.Commands;
import angus.command.DeadlineCommand;
import angus.command.DeleteCommand;
import angus.command.EventCommand;
import angus.command.FindCommand;
import angus.command.ListCommand;
import angus.command.MarkCommand;
import angus.command.SortCommand;
import angus.command.TodoCommand;
import angus.command.UnmarkCommand;
import angus.exception.AngusException;
import angus.storage.Storage;
import angus.task.TaskList;

/**
 * Parses raw user input into specific commands that will be executed.
 * <p>
 * This class handles processing the user's input into commands, and validates if it is a valid command
 * before returning the appropriate command.
 */
public class Parser {
    public static final DateTimeFormatter FORMATTER_FROM = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter FORMATTER_TO = DateTimeFormatter.ofPattern("MMM-dd-yyyy");
    private final Ui ui;
    private final TaskList tasks;
    private final Storage storage;

    /**
     * Creates a parser to be used by Angus.
     * @param ui The user interface for interacting with the user.
     * @param tasks The current list of tasks the user has.
     * @param storage The storage class to handle saving user's tasks.
     */
    public Parser(Ui ui, TaskList tasks, Storage storage) {
        this.ui = ui;
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Parses user's input into Command objects.
     * <p>
     * The support commands are listed in the CommandList enumeration object in the Commands class.
     * @param fullCommand The raw user input.
     * @return The corresponding command based on the user input.
     * @throws AngusException If the command is invalid.
     */
    public Commands parse(String fullCommand) throws AngusException {
        assert ui != null : "ui should not be null";
        assert tasks != null : "tasks should not be null";
        assert storage != null : "storage should not be null";
        String[] commandList = fullCommand.split(" ");
        Commands.CommandList mainCommand = Commands.CommandList.valueOf(commandList[0]);
        switch (mainCommand) {
        case bye:
            return new ByeCommand(ui, storage, tasks);
            // no break because return prevents fallthrough
        case list:
            return new ListCommand(tasks);
            // no break because return prevents fallthrough
        case mark:
            return parseMark(commandList);
        // no break because return prevents fallthrough
        case unmark:
            return parseUnmark(commandList);
        // no break because return prevents fallthrough
        case todo:
            return parseTodo(commandList);
        // no break because return prevents fallthrough
        case deadline:
            return parseDeadline(commandList);
        // no break because return prevents fallthrough
        case event:
            return parseEvent(commandList);
        // no break because return prevents fallthrough
        case delete:
            return parseDelete(commandList);
        // no break because return prevents fallthrough
        case find:
            return parseFind(commandList);
        // no break because return prevents fallthrough
        case sort:
            return parseSort(commandList);
        default:
            throw new IllegalArgumentException();
        }
    }

    private SortCommand parseSort(String[] commandList) throws AngusException {
        if (commandList.length != 2) {
            throw new AngusException("Wrong usage of sort!"
                    + Ui.LINE_BREAK
                    + "Usage: sort deadline OR sort event");
        }
        return new SortCommand(tasks, commandList[1]);
    }

    private FindCommand parseFind(String[] commandList) throws AngusException {
        if (commandList.length != 2) {
            throw new AngusException("Wrong usage of find!"
                    + Ui.LINE_BREAK
                    + "Usage: find [filter keyword]");
        }
        return new FindCommand(tasks, commandList[1]);
    }

    private DeleteCommand parseDelete(String[] commandList) throws AngusException {
        int taskNum;
        if (commandList.length != 2) {
            throw new AngusException("Wrong usage of delete!"
                    + Ui.LINE_BREAK
                    + "Usage: delete [task number]");
        }

        // Handles case: delete [non integer]
        try {
            taskNum = Integer.parseInt(commandList[1]) - 1;
        } catch (NumberFormatException e) {
            throw new AngusException("Wrong usage of delete!"
                    + Ui.LINE_BREAK
                    + "Usage: delete [task number]");
        }
        return new DeleteCommand(tasks, taskNum);
    }

    private EventCommand parseEvent(String[] commandList) throws AngusException {
        StringBuilder endDate;
        int i = 1;
        StringBuilder eventName = new StringBuilder();
        StringBuilder startDate = new StringBuilder();
        endDate = new StringBuilder();
        while (i < commandList.length && !commandList[i].equals("/from")) {
            eventName.append(" ").append(commandList[i]);
            i++;
        }

        i++;

        while (i < commandList.length && !commandList[i].equals("/to")) {
            startDate.append(" ").append(commandList[i]);
            i++;
        }

        i++;

        while (i < commandList.length) {
            endDate.append(" ").append(commandList[i]);
            i++;
        }

        if (eventName.isEmpty()) {
            throw new AngusException("Event description cannot be empty!"
                    + Ui.LINE_BREAK
                    + "Usage: event [description] /from yyyy-mm-dd /to yyyy-mm-dd");
        } else if (startDate.isEmpty()) {
            throw new AngusException("Event start date cannot be empty!"
                    + Ui.LINE_BREAK
                    + "Usage: event [description] /from yyyy-mm-dd /to yyyy-mm-dd");
        } else if (endDate.isEmpty()) {
            throw new AngusException("Event end date cannot be empty!"
                    + Ui.LINE_BREAK
                    + "Usage: event [description] /from yyyy-mm-dd /to yyyy-mm-dd");
        }
        try {
            LocalDate formattedStartDate = LocalDate.parse(startDate.toString().trim(), FORMATTER_FROM);
            LocalDate formattedEndDate = LocalDate.parse(endDate.toString().trim(), FORMATTER_FROM);
            return new EventCommand(tasks, eventName.toString().trim(), formattedStartDate, formattedEndDate);
        } catch (RuntimeException e) {
            throw new AngusException("Incorrect date format!"
                    + Ui.LINE_BREAK
                    + "Usage: event [description] /from yyyy-mm-dd /to yyyy-mm-dd");
        }
    }

    private DeadlineCommand parseDeadline(String[] commandList) throws AngusException {
        StringBuilder endDate;
        int j = 1;
        StringBuilder deadlineName = new StringBuilder();
        endDate = new StringBuilder();
        while (j < commandList.length && !commandList[j].equals("/by")) {
            deadlineName.append(" ").append(commandList[j]);
            j++;
        }

        j++;

        while (j < commandList.length) {
            endDate.append(" ").append(commandList[j]);
            j++;
        }

        if (deadlineName.isEmpty()) {
            throw new AngusException("Deadline description cannot be empty!"
                    + Ui.LINE_BREAK
                    + "Usage: deadline [description] /by yyyy-mm-dd");
        } else if (endDate.isEmpty()) {
            throw new AngusException("Deadline due date cannot be empty!"
                    + Ui.LINE_BREAK
                    + "Usage: deadline [description] /by yyyy-mm-dd");
        }
        // convert endDate to DateTime object
        try {
            LocalDate dateTime = LocalDate.parse(endDate.toString().trim(), FORMATTER_FROM);
            return new DeadlineCommand(tasks, deadlineName.toString().trim(), dateTime);
        } catch (RuntimeException e) {
            throw new AngusException("Incorrect date format!"
                    + Ui.LINE_BREAK
                    + "Usage: deadline [description] /by yyyy-mm-dd");
        }
    }

    private TodoCommand parseTodo(String[] commandList) throws AngusException {
        StringBuilder todoName = new StringBuilder();
        for (int i = 1; i < commandList.length; i++) {
            todoName.append(" ").append(commandList[i]);
        }
        if (todoName.isEmpty()) {
            throw new AngusException("Description of a ToDo cannot be empty!"
                    + Ui.LINE_BREAK
                    + "Usage: todo [description]");
        }
        return new TodoCommand(tasks, todoName.toString().trim());
    }

    private UnmarkCommand parseUnmark(String[] commandList) throws AngusException {
        int taskNum;
        if (commandList.length != 2) {
            throw new AngusException("Wrong usage of unmark!"
                    + Ui.LINE_BREAK
                    + "Usage: unmark [task number]");
        }

        // Handles case: unmark [non integer]
        try {
            taskNum = Integer.parseInt(commandList[1]) - 1;
        } catch (NumberFormatException e) {
            throw new AngusException("Wrong usage of unmark!"
                    + Ui.LINE_BREAK
                    + "Usage: unmark [task number]");
        }
        return new UnmarkCommand(tasks, taskNum);
    }

    private MarkCommand parseMark(String[] commandList) throws AngusException {
        int taskNum;
        if (commandList.length != 2) {
            throw new AngusException("Wrong usage of mark!"
                    + Ui.LINE_BREAK
                    + "Usage: mark [task number]");
        }
        // Handles case: mark [non integer]
        try {
            taskNum = Integer.parseInt(commandList[1]) - 1;
        } catch (NumberFormatException e) {
            throw new AngusException("Wrong usage of mark!"
                    + Ui.LINE_BREAK
                    + "Usage: mark [task number]");
        }
        return new MarkCommand(tasks, taskNum);
    }
}
