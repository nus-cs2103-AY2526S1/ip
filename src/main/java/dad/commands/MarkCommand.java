package dad.commands;

import dad.DadException;
import dad.TaskList;
import dad.Task;

public class MarkCommand extends Command {

    TaskList tasks;
    Task task;


    public MarkCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1) {
            throw new DadException("Finishin'...?");
        }

        this.task = tasks.validateIndex(commands[1]);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Command.pushdown.push(this);
        return this.tasks.markTask(this.task);
    }

    @Override
    public String undo() {
        return this.tasks.unmarkTask(this.task);
    }
}
