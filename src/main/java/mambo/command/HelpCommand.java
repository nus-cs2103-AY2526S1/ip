package mambo.command;

import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.TaskList;

/**
 * Represents a single "help" command that has been passed through to the chatbot
 *
 * @author kentalim2
 */
public class HelpCommand extends Command {

    public HelpCommand(String argument) {
        super(argument);
    }

    /**
     * Returns a help list of all the commands that are valid.
     *
     * @param ui Ui instance to return command list
     * @return Chatbot message
     */
    @Override
    public String execute(Ui ui, TaskList tasks, TaskListFileManager file) {
        return ui.sendHelp();
    }
}
