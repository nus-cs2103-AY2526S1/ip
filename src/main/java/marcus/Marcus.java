package marcus;

import marcus.exception.InvalidIndexError;
import marcus.exception.InvalidInputError;
import marcus.exception.MissingDescriptionError;
import marcus.task.Task;

import java.time.LocalDate;
import java.util.ArrayList;

public class Marcus {
    private TaskList taskList = new TaskList();
    private Ui ui = new Ui();
    private boolean isExit = false;


    /**
     * Generates a welcome message.
     */
    public String getWelcome() {
        return ui.showWelcome();
    }

    /**
     * Generates a message prompting user input.
     */
    public String requestAction() {
        return ui.requestAction();
    }

    /**
     * Passes parsed input to appropriate handler function.
     *
     * ChatGPT was used to abstract out methods, and create
     * the handler functions below.
     */
    public String run(String userInput) {
        try {
            ArrayList<String> parsedCommand = Parser.parseCommand(userInput);
            String command = parsedCommand.get(0);

            switch (command) {
            case "bye":
                return handleBye();
            case "list":
                return handleList();
            case "help":
                return handleHelp();
            case "mark":
                return handleMark(parsedCommand);
            case "unmark":
                return handleUnmark(parsedCommand);
            case "toDo":
                return handleToDo(parsedCommand);
            case "deadline":
                return handleDeadline(parsedCommand);
            case "event":
                return handleEvent(parsedCommand);
            case "delete":
                return handleDelete(parsedCommand);
            case "find":
                return handleFind(parsedCommand);
            default:
                throw new InvalidInputError();
            }
        } catch (MissingDescriptionError | InvalidInputError | InvalidIndexError e) {
            return e.getMessage();
        }
    }

    private String handleBye() {
        this.isExit = true;
        return ui.showGoodbye();
    }

    private String handleList() {
        return ui.showTaskList(taskList);
    }

    private String handleHelp() {
        return ui.showHelp();
    }

    private String handleMark(ArrayList<String> parsedCommand) throws InvalidIndexError {
        int taskIndex = Integer.parseInt(parsedCommand.get(1));
        return taskList.mark(taskIndex);
    }

    private String handleUnmark(ArrayList<String> parsedCommand) throws InvalidIndexError {
        int taskIndex = Integer.parseInt(parsedCommand.get(1));
        return taskList.unmark(taskIndex);
    }

    private String handleToDo(ArrayList<String> parsedCommand) throws MissingDescriptionError {
        String taskDescription = parsedCommand.get(1);
        taskList.add(taskDescription);
        return ui.showTaskAdded(taskList);
    }

    private String handleDeadline(ArrayList<String> parsedCommand) {
        String taskDescription = parsedCommand.get(1);
        LocalDate taskDeadline = LocalDate.parse(parsedCommand.get(2));
        taskList.add(taskDescription, taskDeadline);
        return ui.showTaskAdded(taskList);
    }

    private String handleEvent(ArrayList<String> parsedCommand) {
        String taskDescription = parsedCommand.get(1);
        String start = parsedCommand.get(2);
        String end = parsedCommand.get(3);
        taskList.add(taskDescription, start, end);
        return ui.showTaskAdded(taskList);
    }

    private String handleDelete(ArrayList<String> parsedCommand) throws InvalidIndexError {
        int taskIndex = Integer.parseInt(parsedCommand.get(1));
        Task deletedTask = taskList.delete(taskIndex);
        return ui.showTaskDeleted(taskList, deletedTask);
    }

    private String handleFind(ArrayList<String> parsedCommand) {
        String keyword = parsedCommand.get(1);
        return ui.findTask(taskList, keyword);
    }

    public boolean getIsExit() {
        return this.isExit;
    }
}
