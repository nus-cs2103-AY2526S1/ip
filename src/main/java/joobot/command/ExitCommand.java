package joobot.command;

import joobot.main.Storage;
import joobot.task.TaskList;

/**
 * Represents a command that exits the program.
 */
public class ExitCommand extends Command {

    public ExitCommand() {
        this.isExit = true;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Bye. Hope to see you again soon!";
    }
}
