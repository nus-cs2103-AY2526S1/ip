package dad.commands;

import dad.DadException;
import dad.TaskList;
import java.util.Arrays;

public class FindCommand extends Command {

    TaskList tasks;
    String searchString;

    public FindCommand(String[] commands, TaskList tasks) throws DadException {
        if (commands.length <= 1) {
            throw new DadException("Hm, whaddya lookin' for?");
        }

        this.tasks = tasks;
        this.searchString = String.join(" ", Arrays.copyOfRange(commands, 1, commands.length));
    }

    @Override
    public String execute() {
        return this.tasks.findTasks(this.searchString);
    }
}
