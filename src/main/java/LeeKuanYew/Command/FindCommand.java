package LeeKuanYew.Command;

import LeeKuanYew.Storage;
import LeeKuanYew.Task.TaskList;
import LeeKuanYew.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for tasks containing the specified keyword.
     * If the task list is empty, returns an appropriate message via the UI.
     * Otherwise, displays the list of matching tasks using the UI.
     *
     * @param taskList the list of current tasks
     * @param ui the user interface for displaying messages
     * @param storage the storage handler
     * @return a message indicating the result of the command
     */
    @Override
    public String execute (TaskList taskList, Ui ui, Storage storage) {
        if (taskList.size() == 0) {
            return ui.showMessage("""
                    This is not a game of cards!
                    Your list is empty, get to work!""");
        }

        TaskList result = taskList.find(keyword);
        ListCommand c = new ListCommand();
        return c.execute(result, ui, storage);
    }

}
