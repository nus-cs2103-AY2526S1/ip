package floydai.command;

import java.util.ArrayList;

import floydai.storage.Storage;
import floydai.task.Task;
import floydai.task.TaskList;
import floydai.ui.UI;

/**
 * Represents the command to find tasks that contain a specific keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand from the user's input.
     *
     * @param input The full user input starting with "find".
     */
    public FindCommand(String input) {
        this.keyword = input.replaceFirst("find", "").trim();
    }

    /**
     * Executes the find command.
     * Searches for tasks containing the keyword and displays them via UI.
     *
     * @param tasks   The TaskList object containing all tasks.
     * @param ui      The UI object to show output.
     * @param storage The Storage object (not needed here but required for consistency).
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        if (keyword.isEmpty()) {
            ui.showError("Please provide a keyword to search for.");
            return;
        }

        ArrayList<Task> matchingTasks = tasks.findTasks(keyword);
        if (matchingTasks.isEmpty()) {
            ui.showMessage("No matching tasks found for: " + keyword);
        } else {
            StringBuilder sb = new StringBuilder("Here are the matching tasks in his list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                sb.append(" ").append(i + 1).append(".").append(matchingTasks.get(i)).append("\n");
            }
            ui.showMessage(sb.toString().trim());
        }
    }

    /**
     * Indicates that this command does not exit the program.
     *
     * @return false always, because find does not terminate FloydAI.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
