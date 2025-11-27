package whchenyicn.command;

import whchenyicn.exceptions.WhchenyicnException;
import whchenyicn.task.TaskList;
import whchenyicn.ui.Storage;
import whchenyicn.ui.Ui;

/**
 * A command class with execute function.
 */
public abstract class Command {
    private static final int MAX_TASKS = 100;

    /**
     * Executes the command using provided fields.
     *
     * @param tlist TaskList to execute on.
     * @param ui UI to interact with user.
     * @param storage Storage to store data from interaction.
     * @throws WhchenyicnException Throws error if encounter any issue.
     */
    public abstract String execute(TaskList tlist, Ui ui, Storage storage) throws WhchenyicnException;

    /**
     * Returns whether application is terminating.
     *
     * @return True if terminating, false if not.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if list is full.
     *
     * @param tlist List to be checked.
     * @throws WhchenyicnException Throws error if list is full.
     */
    public void checkFull(TaskList tlist) throws WhchenyicnException {
        if (tlist.size() >= MAX_TASKS) {
            throw new WhchenyicnException("List is full, max 100");
        }
    }

    /**
     * Asserts tlist, ui and storage to not be empty,
     *
     * @param tlist List to be checked.
     * @param ui Ui to be checked.
     * @param storage Storage to be checked.
     */
    protected void assertUi(TaskList tlist, Ui ui, Storage storage) {
        assert tlist != null : "TaskList dependency must be provided";
        assert ui != null : "Ui dependency must be provided";
        assert storage != null : "Storage dependency must be provided";
    }
}
