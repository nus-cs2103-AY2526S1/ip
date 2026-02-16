package jinbot.command;

import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents the command to terminate the program.
 * When executed, it signals the UI to display the goodbye message
 * and ends the user session.
 */
public class ByeCommand implements Command {

    @Override
    public String execute(Ui ui, TaskList taskList) {
        ui.showGoodbye();
        return "Bye. Hope to see you again soon!";
    }
}
