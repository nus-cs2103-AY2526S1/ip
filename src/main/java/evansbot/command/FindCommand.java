package evansbot.command;

import java.util.ArrayList;

import evansbot.task.Storage;
import evansbot.task.Task;
import evansbot.task.TaskList;
import evansbot.ui.Ui;

/**
 * Finds and lists tasks that contain a specific keyword in their description.
 * The search is case-insensitive.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword Keyword to search for in task descriptions.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command on the given TaskList.
     * Searches for tasks whose description contains the keyword and prints
     * them to the console. If no tasks match, a message is displayed.
     *
     * @param tasks   TaskList containing all tasks.
     * @param ui      UI instance used to interact with the user.
     * @param storage Storage instance used for persisting tasks (not modified here).
     * @return String of Find command.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> allTasks = tasks.getTasks();
        ArrayList<Task> matched = new ArrayList<>();

        for (Task task : allTasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matched.add(task);
            }
        }

        if (matched.isEmpty()) {
            return "No matching tasks found for \"" + keyword + "\".";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Here are the matching tasks in your list:");
            for (int i = 0; i < matched.size(); i++) {
                sb.append((i + 1)).append(".").append(matched.get(i)).append("\n");
            }
            return sb.toString();
        }
    }
}
