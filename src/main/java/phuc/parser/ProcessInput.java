package phuc.parser;

import phuc.errorhandle.ErrorHandler;
import phuc.ui.UserInterface;
import phuc.exception.PhucException;
import java.time.LocalDateTime;

public class ProcessInput {
    private final UserInterface ui = new UserInterface();

    public ProcessInput() {
    }

    public void start() {
        ui.sayHello();
    }

    public void end() {
        ui.sayGoodbye();
    }

    public void load() throws PhucException {
        ui.load();
    }

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

    private void handleDelete(String arguments) throws PhucException {
        ErrorHandler.validateTaskIndex(arguments, ui.numTasks());
        ui.delete(arguments);
    }

    private void handleEvent(String arguments) throws PhucException {
        String[] eventArg = ErrorHandler.validateEventFormat(arguments);
        LocalDateTime startDate = ErrorHandler.validateAndParseDateTime(eventArg[1]);
        LocalDateTime endDate = ErrorHandler.validateAndParseDateTime(eventArg[2]);
        ui.event(eventArg[0], startDate, endDate);
    }

    private void handleDeadline(String arguments) throws PhucException {
        String[] deadlineArg = ErrorHandler.validateDeadlineFormat(arguments);
        LocalDateTime deadline = ErrorHandler.validateAndParseDateTime(deadlineArg[1]);
        ui.deadline(deadlineArg[0], deadline);
    }

    private void handleTodo(String arguments) throws PhucException {
        ErrorHandler.validateDescription(arguments, "todo", "todo");
        ui.toDo(arguments);
    }

    private void handleUnmark(String arguments) throws PhucException {
        ErrorHandler.validateTaskIndex(arguments, ui.numTasks());
        ui.unMark(arguments);
    }

    private void handleMark(String arguments) throws PhucException {
        ErrorHandler.validateTaskIndex(arguments, ui.numTasks());
        ui.mark(arguments);
    }
}
