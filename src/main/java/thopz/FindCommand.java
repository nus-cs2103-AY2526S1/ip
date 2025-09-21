package thopz;

import java.util.List;

/**
 * Find and display a certain list of tasks
 * Narrow down tasklist based on key word
 */


public class FindCommand extends Command {

    private final String find;

    /**
     * Constructs a command to find specified description
     *
     * @param find  the description of the task
     *
     *
     */

    public FindCommand(String find) {
        this.find = find;

    }

    /**
     * Executes the find command to find a certain task from a list
     *
     * @param ls   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */

    @Override
    public String perform(TaskList ls, Storage storage, Ui ui) {
        try {
            List<String> matchedTasks = ls.findTasks(find);  // capture matched tasks
            return ui.showFind(matchedTasks);              // pass only matches to UI
        } catch (IllegalArgumentException e) {
            return ui.showError(e.getMessage());
        }
    }
}
