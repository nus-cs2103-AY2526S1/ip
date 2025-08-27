package beebong.command;

import beebong.task.TaskList;

public class ListAllTasksCommand extends ListTasksCommand {
    @Override
    protected TaskList createTaskList(TaskList taskList) {
        return taskList;
    }
}
