package chani.commands;

import chani.Storage;
import chani.TaskList;
import chani.Ui;

/**
 * Command to list all tasks in the {@link TaskList}.
 */
public class ListCommand extends Command {

    /**
     * Creates a ListCommand with a command keyword.
     * @param command The command keyword (e.g., "list").
     * @param args Optional arguments (not used here).
     */
    public ListCommand(String command, String... args) {
        super(command, args);
    }

    /**
     * Retrieves all tasks and returns them as a numbered list.
     * @param storage The storage handler (not used here).
     * @return Message containing all tasks in a numbered list.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        StringBuilder taskMessage = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            taskMessage.append(String.format("%d. %s\n", i, taskList.get(i)));
        }
        return ui.showList(taskMessage.toString());
    }
}
