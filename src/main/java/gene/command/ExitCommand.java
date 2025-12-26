package gene.command;

import gene.TaskList;

public class ExitCommand extends Command {
    public ExitCommand() {
        super(true);
    }

    @Override
    public String execute(TaskList tasksList) {
        return "Bye. Hope to see you again soon!";
    }
}
