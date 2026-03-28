package dad.commands;

import dad.DadException;
import dad.TaskList;

public class ListCommand extends Command {

    TaskList tasks;

    public ListCommand(TaskList tasks) {
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        return this.tasks.listTasks();
    }
}
