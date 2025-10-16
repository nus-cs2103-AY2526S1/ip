package junny.command;

import java.util.ArrayList;

import junny.Storage;
import junny.Ui.Ui;
import junny.tasks.Task;

/**
 * Represents a command that finds tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private final String searchWord;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param searchWord The keyword to search for in tasks.
     */
    public FindCommand(String searchWord) {
        this.searchWord = searchWord;
    }

    /**
     * Executes the find command by filtering tasks whose descriptions
     * contain the search keyword and displaying the results.
     *
     * @param tasks   List of current tasks.
     * @param ui      The UI used to interact with the user.
     * @param storage The storage handler (not used here).
     */
    @Override
    public void run(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.toString().contains(searchWord)) {
                result.add(t);
            }
        }
        ui.findResults(result);
    }
}
