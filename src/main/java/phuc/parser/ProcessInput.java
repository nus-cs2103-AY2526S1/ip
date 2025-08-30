package phuc.parser;

import phuc.errorhandle.ErrorHandler;
import phuc.ui.UserInterface;
import phuc.exception.PhucException;
import java.time.LocalDateTime;

/**
 * Processes user input commands and delegates to appropriate handlers.
 * Acts as the main controller between user input and application logic.
 */
public class ProcessInput {
    private final UserInterface ui = new UserInterface();

    /**
     * Constructs a new ProcessInput.
     */
    public ProcessInput() {
    }

    /**
     * Gives command for the welcome message when the application starts.
     */
    public void start() {
        ui.sayHello();
    }

    /**
     * Gives command for the goodbye message when the application starts.
     */
    public void end() {
        ui.sayGoodbye();
    }

    /**
     * Gives command for loading the file from storage.
     *
     * @throws PhucException if storage file meet an error
     */
    public void load() throws PhucException {
        ui.load();
    }

    /**
     * Processes a user input command and executes the appropriate action.
     *
     * @param input the user input string to process
     * @throws PhucException if the command is invalid or processing fails
     */
    public void process(String input) throws PhucException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        String arg = words.length > 1 ? words[1] : "";

        switch (command) {
        case "delete":
            handleDelete(arg);
            break;
        case "event":
            handleEvent(arg);
            break;
        case "deadline":
            handleDeadline(arg);
            break;
        case "todo":
            handleTodo(arg);
            break;
        case "unmark":
            handleUnmark(arg);
            break;
        case "mark":
            handleMark(arg);
            break;
        case "list":
            ui.list();
            break;
        default:
            throw new PhucException(ErrorHandler.ERROR_UNKNOWN_COMMAND);
        }

        ui.saveToStorage();
    }

    /**
     * Handles the delete command to remove a task.
     *
     * @param arguments the task index to delete
     * @throws PhucException if the index is invalid
     */
    private void handleDelete(String arguments) throws PhucException {
        ErrorHandler.validateTaskIndex(arguments, ui.numTasks());
        ui.delete(arguments);
    }

    /**
     * Handles the event command to add a new event task.
     *
     * @param arguments the event description and time range
     * @throws PhucException if the event format is invalid
     */
    private void handleEvent(String arguments) throws PhucException {
        String[] eventArg = ErrorHandler.validateEventFormat(arguments);
        LocalDateTime startDate = ErrorHandler.validateAndParseDateTime(eventArg[1]);
        LocalDateTime endDate = ErrorHandler.validateAndParseDateTime(eventArg[2]);
        ui.event(eventArg[0], startDate, endDate);
    }

    /**
     * Handles the deadline command to add a new deadline task.
     *
     * @param arguments the deadline description and due date
     * @throws PhucException if the deadline format is invalid
     */
    private void handleDeadline(String arguments) throws PhucException {
        String[] deadlineArg = ErrorHandler.validateDeadlineFormat(arguments);
        LocalDateTime deadline = ErrorHandler.validateAndParseDateTime(deadlineArg[1]);
        ui.deadline(deadlineArg[0], deadline);
    }

    /**
     * Handles the todo command to add a new to-do task.
     *
     * @param arguments the to-do description
     * @throws PhucException if the description is empty
     */
    private void handleTodo(String arguments) throws PhucException {
        ErrorHandler.validateDescription(arguments, "todo", "todo");
        ui.toDo(arguments);
    }

    /**
     * Handles the unmark command to mark a task as not done.
     *
     * @param arguments the task index to unmark
     * @throws PhucException if the index is invalid
     */
    private void handleUnmark(String arguments) throws PhucException {
        ErrorHandler.validateTaskIndex(arguments, ui.numTasks());
        ui.unMark(arguments);
    }

    /**
     * Handles the mark command to mark a task as done.
     *
     * @param arguments the task index to mark
     * @throws PhucException if the index is invalid
     */
    private void handleMark(String arguments) throws PhucException {
        ErrorHandler.validateTaskIndex(arguments, ui.numTasks());
        ui.mark(arguments);
    }
}
