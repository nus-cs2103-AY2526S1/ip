package command;

import task.TaskList;

public class FindCommand extends Command {

    public FindCommand(String arg, TaskList tasklist) {
        super(arg, tasklist);
    }

    public void execute() {
        taskList.find(arg);
    }
}
