package thopz;

import java.io.IOException;

/**
 * Abstract command class
 */

public abstract class Command {

    /**
     * Method which signals end of running
     *
     */

    public boolean isDone() {
        return false;
    }

    /**
     * Executes the add-event command by adding an event task to the task list.
     *
     * @param list   The task list that the event will be added to.
     * @param ui      The UI that handles user interaction.
     * @param storage The storage that saves task data.
     */

    public abstract String perform(TaskList list, Storage storage, Ui ui) throws IOException;

}
