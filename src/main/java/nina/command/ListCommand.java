package nina.command;

import nina.task.TaskList;
/**
 * Represents a ListCommand that can list all tasks in a TaskList.
 */
public class ListCommand implements Command {
    @Override
    public String execute(TaskList tasks) {
        return tasks.showList();
    }
}
