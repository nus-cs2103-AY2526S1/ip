package lebron.command;

import java.util.List;
import java.util.stream.Collectors;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.Task;
import lebron.task.TaskList;

/**
 * Represents a command to find and list tasks that contain a specific keyword in their description.
 */
public class FindCommand extends Command {
    private final String arguments;

    public FindCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        String keyword = arguments.trim();
        assertKeywordNotEmpty(keyword);
        List<Task> matchingTasks = getTasksMatchingKeyword(taskList, keyword);
        return formatMatchingTasks(matchingTasks, taskList);
    }

    /**
     * Asserts that the keyword is not empty.
     * @param keyword The keyword to check.
     * @throws LeBronException if the keyword is empty.
     */
    private void assertKeywordNotEmpty(String keyword) throws LeBronException {
        if (keyword.isEmpty()) {
            throw new LeBronException(Constants.EMPTY_KEYWORD_ERROR);
        }
    }
    /**
     * Retrieves a list of tasks that contain the specified keyword in their description.
     * @param taskList The TaskList to search within.
     * @param keyword The keyword to search for.
     * @return A list of tasks that match the keyword.
     */
    private List<Task> getTasksMatchingKeyword(TaskList taskList, String keyword) {
        return taskList.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .toList();
    }
    /**
     * Formats the list of matching tasks for display.
     * @param matchingTasks The list of tasks that match the keyword.
     * @param taskList The original TaskList to retrieve task indices.
     * @return A formatted string of matching tasks or a message indicating no matches were found.
     */
    private String formatMatchingTasks(List<Task> matchingTasks, TaskList taskList) {
        if (matchingTasks.isEmpty()) {
            return Constants.NO_MATCHING_TASKS_MESSAGE;
        } else {
            String result = Constants.MATCHING_TASKS_HEADER;
            result += matchingTasks.stream()
                    .map(task -> (taskList.getTasks().indexOf(task) + 1) + ". " + task)
                    .collect(Collectors.joining("\n"));
            return result;
        }
    }
}
