package tuesday.command;

import tuesday.storage.Storage;
import tuesday.task.TaskEnums;
import tuesday.task.TaskList;
import tuesday.ui.Ui;

import tuesday.command.CommandEnums.SortAction;

public class SortCommand extends Command {
    private SortAction sortAction;
    private TaskEnums.TaskType taskType;

    public SortCommand(SortAction sortAction, TaskEnums.TaskType taskType) {
        this.sortAction = sortAction;
        this.taskType = taskType;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) {
        ListCommand listCommand = new ListCommand();
        if (sortAction == SortAction.TIME) {
            return listCommand.getResponse(tasks.filterTaskByTime(this.taskType), ui, storage);
        } else if (sortAction == SortAction.TYPE) {
            return listCommand.getResponse(tasks.filterTaskByType(this.taskType), ui, storage);
        }
        return "";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ListCommand listCommand = new ListCommand();
        if (sortAction == SortAction.TIME) {
            listCommand.execute(tasks.filterTaskByTime(this.taskType), ui, storage);
        } else if (sortAction == SortAction.TYPE) {
            listCommand.execute(tasks.filterTaskByType(this.taskType), ui, storage);
        }
    }
}
