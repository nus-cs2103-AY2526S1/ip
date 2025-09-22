package moon.commands;

import java.util.List;

import moon.commands.enums.Command;
import moon.models.Task;
import moon.models.TaskMatch;
import moon.ui.UiMessages;


/**
 * Command to find {@link Task} in the task list with matching names.
 */
public class FindTaskCommand extends BaseCommand {
    private static final Command COMMAND = Command.FIND;
    private final String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find task command to find all the tasks with
     * matching name in the task list.
     *
     * @return list of tasks found to be displayed to the user
     */
    public String execute() {
        List<TaskMatch> matchingTasks = this.getList().findByName(this.keyword);
        return UiMessages.showTasksMatchedMessage(matchingTasks, this.keyword);
    }
}
