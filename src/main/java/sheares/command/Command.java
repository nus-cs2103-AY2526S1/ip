package sheares.command;

import sheares.Storage;
import sheares.TaskList;
import sheares.Ui;

/**
 * abstract class that represents a command, execution differs based on subclass
 */
public abstract class Command {

    private boolean isExit;
    public Command() {
        this.isExit = false;
    }
    public boolean isExit() {
        return this.isExit;
    }

    /**
     * executes the corresponding command, and edits TaskList, UI, and Storage if needed
     * @param ls
     * @param ui
     * @param storage
     */
    public abstract void execute(TaskList ls, Ui ui, Storage storage);
    public abstract String executeWithString(TaskList ls, Ui ui, Storage storage);
}
