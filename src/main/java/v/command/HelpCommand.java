package v.command;

import v.storage.Storage;
import v.task.TaskList;
import v.ui.Ui;

/**
 * Command to show help information with V's dramatic flair.
 */
public class HelpCommand extends Command {
    private final String helpTopic;

    /**
     * Constructs a HelpCommand with the specified help topic.
     *
     * @param helpTopic the topic to get help for (sort, general, or empty for general)
     */
    public HelpCommand(String helpTopic) {
        this.helpTopic = helpTopic.toLowerCase().trim();
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        // Assertion: parameters should not be null
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";
        assert storage != null : "Storage cannot be null";

        ui.showHelp(helpTopic);
        return false;
    }
}
