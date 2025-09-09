package phuc.parser;

import java.time.LocalDateTime;

import phuc.exception.PhucException;
import phuc.ui.UserInterface;
import phuc.util.ErrorHandler;

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
    public void sayGreetingMessage() {
        ui.sayHello();
    }

    /**
     * Gives command for the goodbye message when the application starts.
     */
    public void sayGoodbyeMessage() {
        ui.sayGoodbye();
    }

    /**
     * Gives command for loading the file from storage.
     *
     * @throws PhucException if storage file meet an error
     */
    public void loadDataFromStorage() throws PhucException {
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
        case "find":
            ui.find(arg);
            break;
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
     * @param taskIndexInStringFormat the task index to delete
     * @throws PhucException if the index is invalid
     */
    private void handleDelete(String taskIndexInStringFormat) throws PhucException {
        //Check if the task index is valid or not
        ErrorHandler.validateTaskIndex(taskIndexInStringFormat, ui.numTasks());
        ui.delete(taskIndexInStringFormat);
    }

    /**
     * Handles the event command to add a new event task.
     *
     * @param eventInStringForm the event description and time range
     * @throws PhucException if the event format is invalid
     */
    private void handleEvent(String eventInStringForm) throws PhucException {
        //Check if the event string is valid and extract it start and end date
        String[] eventArg = ErrorHandler.validateEventFormat(eventInStringForm);
        //Transform date, time of start date and end date from string to localdatetime
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
        //Check if the deadline task is valid or not and extract deadline and task description in it
        String[] deadlineArg = ErrorHandler.validateDeadlineFormat(arguments);
        String taskDescription = deadlineArg[0];
        //Transform date, time of deadline from string to localdatetime
        LocalDateTime deadline = ErrorHandler.validateAndParseDateTime(deadlineArg[1]);
        ui.deadline(taskDescription, deadline);
    }

    /**
     * Handles the todo command to add a new to-do task.
     *
     * @param taskDescription the to-do description
     * @throws PhucException if the description is empty
     */
    private void handleTodo(String taskDescription) throws PhucException {
        //Check if the task description is valid or not
        ErrorHandler.validateDescription(taskDescription);
        ui.toDo(taskDescription);
    }

    /**
     * Handles the unmark command to mark a task as not done.
     *
     * @param taskIndexInStringFormat the task index to unmark
     * @throws PhucException if the index is invalid
     */
    private void handleUnmark(String taskIndexInStringFormat) throws PhucException {
        //Check if the index is valid
        ErrorHandler.validateTaskIndex(taskIndexInStringFormat, ui.numTasks());
        ui.unMark(taskIndexInStringFormat);
    }

    /**
     * Handles the mark command to mark a task as done.
     *
     * @param taskIndexInStringFormat the task index to mark
     * @throws PhucException if the index is invalid
     */
    private void handleMark(String taskIndexInStringFormat) throws PhucException {
        //Check if the index is valid
        ErrorHandler.validateTaskIndex(taskIndexInStringFormat, ui.numTasks());
        ui.mark(taskIndexInStringFormat);
    }
}
