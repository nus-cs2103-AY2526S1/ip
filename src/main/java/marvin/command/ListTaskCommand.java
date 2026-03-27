package marvin.command;

import marvin.Personality;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for the list command in Marvin.
 */
public class ListTaskCommand extends Command {
    @Override
    public CommandResult execute(TaskList taskList) {
        return new CommandResult(() -> Ui.printTaskList(taskList, Personality.getTaskIntro()),
                 Personality.getTaskIntroColorless() + taskList
        );
    }
}
