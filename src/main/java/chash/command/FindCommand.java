package chash.command;

import chash.storage.ChashDb;
import chash.task.Task;
import chash.task.TaskList;
import chash.ui.ChashUi;

/** Command to find tasks that contain a given search term in their description. */
public class FindCommand extends Command {
    private final String searchTerm;

    /**
     * Creates a new find command with the given search term.
     *
     * @param searchTerm Keyword or phrase to search for
     */
    public FindCommand(String searchTerm) {
        assert searchTerm != null;

        this.searchTerm = searchTerm;
    }

    /**
     * {@inheritDoc}
     * Searches the task list for matching tasks and displays them to the user.
     * If no tasks match, a message is printed instead.
     */
    @Override
    public void execute(TaskList tasks, ChashUi ui, ChashDb db) {
        assert tasks != null;
        assert ui != null;

        if (tasks.size() == 0) {
            ui.printMsg("No tasks in your list!");
            return;
        }

        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        int counter = 0;
        for (Task task : tasks.getAll()) {
            String tmp = task.toString();

            if (tmp.contains(this.searchTerm)) {
                counter += 1;
                sb.append(String.format("%d. %s\n", counter, tmp));
            }
        }

        //Check if there was any tasks found
        if (counter == 0) {
            ui.printMsg("No tasks found matching: " + this.searchTerm);
        } else {
            ui.printMsg(sb.toString().stripTrailing());
        }
    }
}
