package command;

import model.Task;
import ui.TaskService;
import ui.ui;

/**
 * Add command that support user to delete Task added form tasklist
 */
public class DeleteCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;
    private Task task;
    private int taskId;

    public DeleteCommand(TaskService taskService, ui ui, String arguments) {
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
        this.task = null;
        this.taskId = 0;
    }

    @Override
    public void execute() {
        try{
            taskId = Integer.parseInt(arguments) - 1;
            task = taskService.getTask(taskId);
            taskService.removeTask(taskId);
            ui.showTaskDeleted(task, taskService.getTaskCount());
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
            int taskCount = taskService.getTaskCount();
            return "Noted. I've removed this task: \n" + task.toString() + "\n"
            + "Now you have " + taskCount + " tasks in the list";
        } catch (Exception e) {
            return "Invalid task number!";
        }
    }

}
