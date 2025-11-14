package shef.command;

import shef.storage.Storage;
import shef.tasklist.TaskList;

/**
 * Command that sends help on using commands.
 */
public class HelpCommand extends Command {
    private static final String FORMAT = "help";

    public HelpCommand(String args) {
        super(args);
    }

    public static String getFormat() {
        return FORMAT;
    }

    @Override
    public String execute(TaskList tasks, Storage storage) {
        return "Here are the commands you can execute:\n\n"
                + HelpCommand.getFormat() + "\n\n"
                + ListCommand.getFormat() + "\n\n"
                + ExitCommand.getFormat() + "\n\n"
                + AddDeadlineCommand.getFormat() + "\n\n"
                + AddEventCommand.getFormat() + "\n\n"
                + AddTodoCommand.getFormat() + "\n\n"
                + MarkCommand.getFormat() + "\n\n"
                + UnmarkCommand.getFormat() + "\n\n"
                + DeleteCommand.getFormat() + "\n\n"
                + FindCommand.getFormat();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
