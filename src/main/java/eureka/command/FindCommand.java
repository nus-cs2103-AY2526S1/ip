package eureka.command;

import java.util.ArrayList;

import eureka.Storage;
import eureka.task.Task;
import eureka.task.TaskList;
import eureka.ui.Ui;

/**
 * Represents a command that searches for tasks in the task list
 * matching a given keyword.
 * <p>
 * The command checks all tasks in the {@link TaskList} and returns
 * a formatted list of matching tasks. If no matches are found,
 * a suitable message is returned.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Constructs a FindCommand with the specified keyword to search for.
     *
     * @param keyword the keyword to search for in the task list
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching the given {@link TaskList}
     * for tasks that contain the keyword. Displays results to the UI
     * and returns a formatted string for GUI display.
     *
     * @param tasks   the task list to search in
     * @param ui      the user interface used to show messages
     * @param storage the storage used to save or load data (not modified here)
     * @return a formatted string containing the matching tasks, or a message
     *         if no matches are found
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> results = tasks.find(keyword);

        if (results.isEmpty()) {
            return "No matching tasks found for keyword: " + keyword;
        }

        ui.showLine();
        System.out.println("Here are the matching tasks in your list:");
        for (Task result : results) {
            System.out.println(result.toString());
        }
        ui.showLine();

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");

        for (int i = 0; i < results.size(); i++) {
            sb.append((i + 1)).append(". ").append(results.get(i).toString()).append("\n");
        }

        return sb.toString().trim(); // remove last newline
    }

    /**
     * Indicates whether this command will terminate the program.
     *
     * @return false, as FindCommand does not exit the program
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
