package command;

import model.Todo;
import ui.TaskService;
import ui.ui;

/**
 * Add command that support user to add a Todo Task
 */
public class AddTodoCommand implements Command {
    private TaskService taskService;
    private ui ui;
    private String arguments;
    private Todo todo;

    public AddTodoCommand(TaskService taskService, ui ui, String arguments) {
        this.taskService = taskService;
        this.ui = ui;
        this.arguments = arguments;
        this.todo = null;
    }

    @Override
    public void execute() {
        if (arguments.isEmpty()) {
            ui.showError("The description of a todo cannot be empty");
            return;
        }
        todo = new Todo(arguments);
        taskService.addTask(todo);
        ui.showTaskAdded(todo, taskService.getTaskCount());
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public String toString() {
        if (arguments.isEmpty()) {
            return "The description of a todo cannot be empty";
        }
        int taskCount = taskService.getTaskCount();
        return "Got it. I've added this task: \n" + todo.toString() + "\n"
        + "Now you have " + taskCount + " tasks in the list";
    }
}
