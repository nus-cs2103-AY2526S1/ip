package chatbot.command;

import java.util.List;

import chatbot.storage.Storage;
import chatbot.task.Task;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;



/**
 * Represents a command to find all tasks in the TaskList.
 * When executed, it displays all tasks with the matching string in the description.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructor for FindCommand.
     *
     * @param keyword the word to search for in task descriptions
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command: displays tasks that contain the keyword.
     *
     * @param tasks   the TaskList containing all tasks
     * @param ui      the Ui instance to display messages
     * @param storage the Storage instance (not used in this command)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";
        String loweredKeyword = keyword.toLowerCase();

        // Filter matching tasks
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.toString().toLowerCase().contains(loweredKeyword))
                .toList();

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found for: " + keyword;
        }

        // Format with index numbers using IntStream
        String taskListStr = java.util.stream.IntStream.range(0, matchingTasks.size())
                .mapToObj(i -> (i + 1) + ". " + matchingTasks.get(i))
                .collect(java.util.stream.Collectors.joining("\n"));

        return "Here are the matching tasks in your list:\n" + taskListStr;

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
