package lenny.logic.command;

import lenny.logic.storage.Storage;
import lenny.logic.task.TaskList;
import lenny.logic.ui.Ui;

/**
 * Represents a command that terminates the program.
 * When executed, it returns a farewell message and signals that the
 * application should exit.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by returning a farewell message.
     *
     * @param tasks   The TaskList containing all tasks
     *                (not directly used here).
     * @param storage The Storage object responsible for persisting tasks
     *                (not directly used here).
     * @param ui      The Ui component used for interactions
     *                (not directly used here).
     * @return A farewell message to display before exiting the program.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        //Used AI to generate personality
        return "Powering down... see you soon \uD83D\uDC4B.";
    }

    /**
     * Indicates that this command will cause the program to exit.
     *
     * @return true, since this is the exit command.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
