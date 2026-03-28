package dad.commands;

import dad.DadException;
import dad.TaskList;
import dad.Task;
import dad.Deadline;
import java.util.Arrays;

public class DeadlineCommand extends Command {

    TaskList tasks;
    Task task;


    public DeadlineCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1 || commands[1].strip().equals("")) {
            throw new DadException("When 'n what?");
        }
        String[] parses = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)).strip().split("/by");
        if (parses.length != 2 || parses[0].strip().equals("") || parses[1].strip().equals("")) {
            throw new DadException("When 'r what?");
        }

        this.task = new Deadline(parses[0], parses[1]);
        this.tasks = tasks;
    }

    @Override
    public String execute() {
        Command.pushdown.push(this);
        return this.tasks.addTask(this.task);
    }

    @Override
    public String undo() {
        return this.tasks.deleteTask(this.task);
    }
}
