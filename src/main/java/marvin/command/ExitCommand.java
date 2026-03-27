package marvin.command;

import marvin.Personality;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for the exit command in Marvin.
 */
public class ExitCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        String response = Personality.getGoodbye();
        return new CommandResult(() -> Ui.printGoodbye(response), response);
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
