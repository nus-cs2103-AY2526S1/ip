package uxie.commands;

import uxie.exceptions.UxieIOException;
import uxie.exceptions.UxieIllegalOpException;
import uxie.interfaces.Storage;
import uxie.interfaces.TaskList;
import uxie.interfaces.ui.Ui;

/**
 * Command that tags a Task with a String.
 *
 * @author junyan-k
 */
public class TagCommand extends Command {

    /** Index of task to tag. 0-indexed. */
    private int taskIndex;

    /** String to tag Task with. */
    private String tag;

    /**
     * Generates a TagCommand.
     *
     * @param taskIndex index of task to tag.
     * @param tag String to tag Task with.
     */
    public TagCommand(int taskIndex, String tag) {
        this.taskIndex = taskIndex;
        this.tag = tag;
    }

    /**
     * {@inheritDoc}
     * Tags task matching taskIndex in taskList with tag.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            String desc = tasks.tagTask(taskIndex, tag);
            storage.tagTask(taskIndex, tag);
            if (tag.isBlank()) {
                ui.uxieAppendln(String.format("Cleared tag from Task %s (%s).", taskIndex + 1, desc));
            } else {
                ui.uxieAppendln(String.format("Tagged Task %s (%s) as #%s.", taskIndex + 1, desc, tag));
            }
        } catch (UxieIllegalOpException | UxieIOException e) {
            ui.appendException(e);
        }
    }

    /**
     * {@inheritDoc}
     * Returns false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Returns true if provided object is equal to this TagCommand.
     * Two TagCommands are equal if they have the same index and tag.
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof TagCommand) {
            boolean isTaskIndexEqual = ((TagCommand) o).taskIndex == this.taskIndex;
            boolean isTagEqual = ((TagCommand) o).tag.equals(this.tag);
            return isTaskIndexEqual && isTagEqual;
        }
        return false;
    }
}
