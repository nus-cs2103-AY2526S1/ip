package marvin.command;

import marvin.MarvinException;
import marvin.Personality;
import marvin.task.TaskList;
import marvin.ui.Ui;

/**
 * Contains logic for the delete command in Marvin.
 */
public class DoAfterCommand extends Command {
    private final String parentLocator;
    private final String subTaskLocator;

    /**
     * Instantiate a Do After command.
     *
     * @param parentLocator The string representation of where the task that has to be completed first is
     * @param subTaskLocator The string representation of where the task that can be completed after is
     */
    public DoAfterCommand(String parentLocator, String subTaskLocator) {
        this.parentLocator = parentLocator;
        this.subTaskLocator = subTaskLocator;
    }

    @Override
    public CommandResult execute(TaskList taskList) {
        // Remove the old task from the list
        // Handle the parent/child dilemma
        try {
            taskList.setTaskToDoAfter(parentLocator, subTaskLocator);
        } catch (MarvinException e) {
            return new CommandResult(() -> Ui.printGeneric(e.getMessage()),
                    e.getMessage()
            );
        }

        String reply = Personality.getGenericCompletedText();
        return new CommandResult(() -> Ui.printGeneric(reply), reply);
    }
}
