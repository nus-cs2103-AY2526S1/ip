package peanutbutter.commands;

import peanutbutter.exceptions.JuinException;
import peanutbutter.tasks.TaskList;
import peanutbutter.ui.Ui;

/**
 * Represents a command to find tasks matching a keyword.
 */
public class FindCommand extends Command {
    private String args;

    /**
     * Creates a new FindCommand with the given arguments.
     *
     * @param args the keyword(s) to search for
     */
    public FindCommand(String args) {
        this.args = args;
    }

    /**
     * Executes the FindCommand.
     *
     * @param taskList the list of tasks
     * @param ui the user interface for displaying messages
     * @throws JuinException if the search keyword is empty
     */
    @Override
    public boolean run(TaskList taskList, Ui ui) throws JuinException {
        if (args == null || args.isBlank()) {
            throw new JuinException("Search keyword cannot be empty.");
        }
        TaskList keyList = new TaskList(taskList.getTasks().stream()
                .filter(task -> task.getDescription().contains(args))
                .toList());
        ui.showKeyListMessage(keyList, args);

        return false;
    }
}
