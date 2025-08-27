package beebong.command;

import beebong.task.TaskList;

public class FindTaskCommand extends ListTasksCommand {
    private final String keyword;

    public FindTaskCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    protected TaskList createTaskList(TaskList taskList) {
        return taskList.findTasks(this.keyword);
    }
}
