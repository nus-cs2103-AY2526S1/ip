package command;

import ui.TaskService;
import ui.ui;

/**
 * Add command for user to mark Task in a specific index as not done
 */
public class UnmarkCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;
    private int taskId;

    public UnmarkCommand(TaskService taskService, ui ui, String arguments) {
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
        this.taskId = 0;
    }

    @Override
    public void execute() {
        try{
            taskId = Integer.parseInt(arguments) - 1;
            taskService.unmarkTasks(taskId);
            ui.showMassage("OK, I've marked this task as not done yet: \n" +
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
            return "OK, I've marked this task as not done yet: \n" +
                    taskService.getTask(taskId).toString();
        } catch (Exception e) {
            return "Invalid task number!";
        }
    }
}
