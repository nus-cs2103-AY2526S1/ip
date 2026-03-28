package karl.command;

import karl.exception.KarlException;
import karl.storage.Storage;
import karl.task.TaskList;
import karl.ui.Ui;

/**
 * Command to show help information to the user.
 */
public class HelpCommand implements Command {

    private static final String HELP_MESSAGE = String.join("\n",
            "Karl Chatbot Help:",
            "- Type commands to manage your tasks.",
            "- todo DESCRIPTION : Add a todo task.",
            "- deadline DESCRIPTION /by DATE : Add a deadline task.",
            "- event DESCRIPTION /from DATE /to DATE : Add an event task.",
            "- mark INDEX : Mark a task as done.",
            "- unmark INDEX : Mark a task as not done.",
            "- delete INDEX : Delete a task.",
            "- list : List all tasks.",
            "- find KEYWORD : Search tasks containing the keyword.",
            "- help : Show this help message.",
            "- bye : Exit the chatbot."
    );

    /**
     * Executes the help command by displaying usage instructions.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws KarlException {
        ui.showMessage(HELP_MESSAGE);
    }

    /**
     * Indicates this command does not exit the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
