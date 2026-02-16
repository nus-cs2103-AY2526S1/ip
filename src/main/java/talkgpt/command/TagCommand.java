package talkgpt.command;

import talkgpt.storage.Storage;
import talkgpt.tasklist.TaskList;
import talkgpt.ui.Ui;

/**
 * Represents a command to show all the tasks of the tag in the TalkGPT application.
 */
public class TagCommand extends Command {
    private String tag;

    /**
     * Constructs a TagCommand to show all tasks with the specified tag.
     *
     * @param tag   Tag to be added to the task.
     */
    public TagCommand(String tag) {
        this.tag = tag;
    }

    /**
     * Executes the show tasks with tag function for the TaskList and UI.
     *
     * @param list TaskList to search for tasks with the specified tag.
     * @param ui   UI for the show tasks with tag print statement.
     * @param storage Storage for consistency with other commands (not used here).
     * @return The message showing all tasks with the specified tag.
     */
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        return ui.tagView(list, tag);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TagCommand)) {
            return false;
        }
        TagCommand that = (TagCommand) other;
        return this.tag.equals(that.tag);
    }
}
