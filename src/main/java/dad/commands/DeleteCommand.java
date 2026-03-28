package dad.commands;

import dad.DadException;
import dad.TaskList;
import dad.Task;

public class DeleteCommand extends Command {

    TaskList tasks;
    Task task;


    public DeleteCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1) {
            throw new DadException("Deletin' um, uh, where?");
        }

        this.task = tasks.validateIndex(commands[1]);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Command.pushdown.push(this);
        return this.tasks.deleteTask(this.task);
    }

    @Override
    public String undo() {
        return this.tasks.addTask(this.task);
    }
}
