package Command;

import JohnException.JohnException;

import Task.TaskList;

import UI.GuiUi;
import UI.Ui;

/**
 * Overall command class to be inherited to execute specific commands.
 */
public abstract class Command {
    /**
     * Returns false to indicate that the application should not exit.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command against the application state.
     *
     * @param tasks Current task list.
     * @param ui UI used to present feedback to the user.
     * @throws JohnException If execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui) throws JohnException;

    /**
     * Executes the command and returns the output from the GuiUI.
     *
     * @param tasks Current task list.
     * @return Output string from Gui.
     * @throws JohnException When user gives invalid input for the command.
     */
    public String executeAndReturn(TaskList tasks) throws JohnException {
        assert tasks != null : "Command: tasks must not be null";
        GuiUi tempUi = new GuiUi();
        this.execute(tasks, tempUi);
        String out = tempUi.getOutput();
        assert out != null : "Command: execute must produce non-null output";
        return out;
    }
}
