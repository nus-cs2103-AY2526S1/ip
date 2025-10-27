package command;

import ui.TaskService;
import ui.ui;

/**
 * Add command to support user checking TaskList
 */
public class ListCommand implements Command {
    private TaskService taskService;
    private ui ui;

    public ListCommand(TaskService taskService, ui ui) {
        this.taskService = taskService;
        this.ui = ui;
    }

    @Override
    public void execute() {
        ui.showListTask(taskService.getTasks());
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        return ui.showListTaskInString(taskService.getTasks());
    }
}
