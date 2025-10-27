package command;

import ui.TaskService;
import ui.ui;

/**
 * Add command support finding task with a description
 */
public class FindCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;

    public FindCommand(TaskService taskService, ui ui, String arguments){
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
    }

    @Override
    public void execute() {
        if (arguments.isEmpty()){
            ui.showError("Please enter a task");
        }
        ui.showMatchingTask(taskService.searchTask(arguments));
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        if (arguments.isEmpty()){
            return "Please enter a task";
        }
        return ui.showMatchingTaskInString(taskService.searchTask(arguments));
    }
}
