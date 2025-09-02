package paul.parser;

import paul.exception.PaulException;
import paul.task.TaskList;
import paul.storage.Storage;
import paul.ui.Ui;

/**
 * Parses user commands and delegates actions to TaskList, Storage, and Ui.
 */
public class Parser {
    public enum CommandType {
        TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, UNKNOWN
    }

    /**
     * Returns the command type based on the user input string.
     *
     * @param input User input.
     * @return The command type corresponding to the user's input.
     */
    public CommandType getCommandType(String input) {
        String[] words = input.split(" ", 2);
        String commandWord = words[0].toLowerCase();

        return switch (commandWord) {
            case "todo" -> CommandType.TODO;
            case "deadline" -> CommandType.DEADLINE;
            case "event" -> CommandType.EVENT;
            case "list" -> CommandType.LIST;
            case "mark" -> CommandType.MARK;
            case "unmark" -> CommandType.UNMARK;
            case "delete" -> CommandType.DELETE;
            case "bye" -> CommandType.BYE;
            default -> CommandType.UNKNOWN;
        };
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
    public void parse(String fullCommand, TaskList tasks, Storage storage, Ui ui) throws PaulException {
        String[] parseCommand = fullCommand.split(" ", 2);
        CommandType command = getCommandType(parseCommand[0]);

        switch (command) {
        case TODO, DEADLINE, EVENT:
            tasks.addTask(parseCommand, storage, ui);
            break;
        case LIST:
            ui.showTasks(tasks);
            break;
        case MARK, UNMARK:
            tasks.markTask(parseCommand, storage, ui);
            break;
        case DELETE:
            tasks.deleteTask(parseCommand, storage, ui);
            break;
        case BYE:
            ui.byeUser();
            break;
        case UNKNOWN:
            throw new PaulException("Sorry! I do not know what that means :(");
        }
    }
}
