package marvin.command;

import marvin.Personality;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for what to do when Marvin doesn't recognize the command inputted.
 */
public class UnknownCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        String reply = Personality.getUnknownCommandText();
        return new CommandResult(() -> Ui.printGeneric(reply), reply);
    }
}
