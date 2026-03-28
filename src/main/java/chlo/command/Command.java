package chlo.command;

import chlo.storage.Storage;
import chlo.task.TaskList;
import chlo.ui.Ui;

/**
 * Represents an abstract command class that gives template of all task classes.
 * Task classes: Event, Todo, Deadline
 */
public abstract class Command {
    /**
     * Carries out the command with tasks, ui, storage taking changes
     * @param tasks
     * @param ui
     * @param storage
     */
    private String string;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public void setString(String s) {
        this.string = s;
    }

    public String getString() {
        return string;
    }
    public boolean isExit() { return false; }
}


