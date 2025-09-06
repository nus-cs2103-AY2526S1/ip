package paul.parser;

import paul.exception.PaulException;
import paul.storage.Storage;
import paul.task.Task;
import paul.task.TaskList;
import paul.ui.Ui;

/**
 * Parses user commands and delegates actions to TaskList, Storage, and Ui.
 */
public class Parser {

    /**
     * Represents the different types of commands a user can make.
     */
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, FIND, BYE, UNKNOWN
    }

    /**
     * Returns the command type based on the user input string.
     *
     * @param input User input.
     * @return The command type corresponding to the user's input.
     */
    public static CommandType getCommandType(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0].toLowerCase();
        //CHECKSTYLE.OFF: Indentation
        return switch (commandWord) {
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            case "list" -> CommandType.LIST;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            case "find" -> CommandType.FIND;
            case "bye" -> CommandType.BYE;
            default -> CommandType.UNKNOWN;
        };
        //CHECKSTYLE.ON: Indentation
    }

    /**
     * Parses a command and executes the appropriate action.
     *
     * @param fullCommand Full command input by user.
     * @param tasks TaskList object containing list of tasks.
     * @param storage Storage object to load/save data.
     * @param ui Ui object for interacting with the user.
     * @throws PaulException if the command is unknown.
     */
    public String parseAndExecute(String fullCommand, TaskList tasks, Storage storage, Ui ui) throws PaulException {
        String[] parsedCommand = fullCommand.split(" ", 2);
        CommandType command = getCommandType(parsedCommand[0]);

        switch (command) {
        case TODO, DEADLINE, EVENT:
            Task newTask = tasks.addTask(parsedCommand);
            tasks.add(newTask);
            storage.saveTasks(tasks);
            return ui.showTaskAdded(newTask, tasks.size());
        case LIST:
            return ui.showTasks(tasks);
        case MARK:
            Task markedTask = tasks.markTask(parsedCommand);
            storage.saveTasks(tasks);
            return ui.showTaskMarked(markedTask);
        case UNMARK:
            Task unmarkedTask = tasks.unmarkTask(parsedCommand);
            storage.saveTasks(tasks);
            return ui.showTaskUnmarked(unmarkedTask);
        case DELETE:
            Task deletedTask = tasks.deleteTask(parsedCommand);
            storage.saveTasks(tasks);
            return ui.showTaskDeleted(deletedTask, tasks.size());
        case FIND:
            TaskList foundTasks = tasks.findTasks(parsedCommand);
            return ui.showTaskFound(foundTasks);
        case BYE:
            return ui.byeUser();
        case UNKNOWN:
            throw new PaulException("Sorry! I do not know what that means :(");
        default:
            throw new PaulException("Error! Not a valid command for parsing."); // Should not reach here
        }
    }
}
