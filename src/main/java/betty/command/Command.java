package betty.command;

import betty.exception.BettyException;
import betty.storage.Storage;
import betty.task.TaskList;
import betty.ui.Ui;

/**
 * Represents the command object that can be read and executed by the task manager
 */
public abstract class Command {
    /**
     * An enumeration of all the different command names of the chatbot
     */
    public enum CommandName {
        BYE,
        LIST,
        MARK,
        UNMARK,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        FIND,
        PRIORITY,
        UNKNOWN;

        /**
         * Converts the user input command into a command name
         * If command does not match any known command, return UNKNOWN
         * @param command String representation of user command
         * @return corresponding command name of command
         */
        public static CommandName fromString(String command) {
            return switch (command.toLowerCase()) {
            case "bye" -> BYE;
            case "list" -> LIST;
            case "mark" -> MARK;
            case "unmark" -> UNMARK;
            case "todo" -> TODO;
            case "deadline" -> DEADLINE;
            case "event" -> EVENT;
            case "delete" -> DELETE;
            case "find" -> FIND;
            case "priority" -> PRIORITY;
            default -> UNKNOWN;
            };
        }
    }
    /**
     * Executes the command using the given task list, UI, and storage.
     *
     * @param taskList the list of tasks to operate on
     * @param ui       the user interface to display messages
     * @param storage  the storage manager to save changes
     * @return String representation of the result after executing the command
     * @throws BettyException if execution fails
     */
    public abstract String execute(TaskList taskList, Ui ui, Storage storage) throws BettyException;
    /**
     * Returns whether this command should terminate the program.
     *
     * @return true if this command signals an exit, false otherwise
     */
    public abstract boolean isExit();
}
