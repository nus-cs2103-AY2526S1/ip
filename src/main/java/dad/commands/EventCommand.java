package dad.commands;

import dad.DadException;
import dad.TaskList;
import dad.Task;
import dad.Event;
import java.util.Arrays;

public class EventCommand extends Command {

    TaskList tasks;
    Task task;


    public EventCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1 || commands[1].strip().equals("")) {
            throw new DadException("Where event where??");
        }
        String[] parses = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length)).strip().split("/from");
        if (parses.length != 2 || parses[0].strip().equals("") || parses[1].strip().equals("")) {
            throw new DadException("Whenzzit start?");
        }
        String[] parses2 = parses[1].split("/to");
        if (parses2.length != 2 || parses2[0].strip().equals("") || parses2[1].strip().equals("")) {
            throw new DadException("Whenzzit end?");
        }

        this.task = new Event(parses[0], parses2[0], parses2[1]);
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
