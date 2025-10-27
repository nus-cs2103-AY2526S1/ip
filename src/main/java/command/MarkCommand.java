package command;

import ui.TaskService;
import ui.ui;

/**
 * Add command for user to mark Task in a specific index as done
 */
public class MarkCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;
    private int taskId;

    public MarkCommand(TaskService taskService, ui ui, String arguments) {
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
        this.taskId = 0;
    }

    @Override
    public void execute() {
        try{
            taskId = Integer.parseInt(arguments) - 1;
            taskService.markTask(taskId);
            ui.showMassage("Nice ! I've marked this task as done: \n" +
                    taskService.getTask(taskId));
        } catch (Exception e) {
            ui.showError("Invalid task number!");
        }
    }
    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        try{
            return "Nice ! I've marked this task as done: \n" +
                    taskService.getTask(taskId).toString();
        } catch (Exception e) {
            return "Invalid task number!";
        }
    }
}
