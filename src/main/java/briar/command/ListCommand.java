package briar.command;

import briar.exception.BriarException;
import briar.task.TaskList;
import briar.ui.Storage;
import briar.ui.Ui;

/**
 * Represents a command that lists all Tasks from a TaskList.
 */
public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    /**
     * Executes the command to list all tasks in the task list.
     * @param tasks Task list of the chatbot.
     * @param ui Ui used by the chatbot to display.
     * @param storage Storage used to save and load the task list.
     * @return Response from Briar after completing the command
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws BriarException {
        return ui.showTaskList(tasks);
    }

    /**
     * Returns a String containing the format of the command and what it does.
     */
    public static String getCommandInformation() {
        return "list | Displays a list of all tasks";
    }

    /**
     * Returns the string representation of the command.
     */
    @Override
    public String toString() {
        return "List";
    }
}
