package peppa.command;

import peppa.task.TaskList;

public class ListTasks implements Command {
    private final TaskList tasks;

    public ListTasks(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return tasks.displayTasks();
    }
}
