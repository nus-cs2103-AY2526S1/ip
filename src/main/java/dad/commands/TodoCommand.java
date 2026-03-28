package dad.commands;

import dad.DadException;
import dad.TaskList;
import dad.Task;
import dad.Todo;
import java.util.Arrays;

public class TodoCommand extends Command {

    TaskList tasks;
    Task task;


    public TodoCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1 || commands[1].strip().equals("")) {
            throw new DadException("What'cha hafta do?");
        }

        this.task = new Todo(String.join(" ", Arrays.copyOfRange(commands, 1, commands.length))); 
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
