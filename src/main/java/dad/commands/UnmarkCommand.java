package dad.commands;

import dad.DadException;
import dad.TaskList;
import dad.Task;

public class UnmarkCommand extends Command {

    TaskList tasks;
    Task task;


    public UnmarkCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1) {
            throw new DadException("You undid wha?");
        }

        this.task = tasks.validateIndex(commands[1]);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Command.pushdown.push(this);
        return this.tasks.unmarkTask(this.task);
    }

    @Override
    public String undo() {
        return this.tasks.markTask(this.task);
    }
}
