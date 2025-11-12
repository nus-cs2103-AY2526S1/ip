package chatbot.command;

import chatbot.storage.Storage;
import chatbot.tasklist.TaskList;
import chatbot.ui.Ui;

/**
 * Represents a command to give the user a help sheet
 */
public class HelpCommand extends Command {

    private static final String HELP_MESSAGE = String.join("\n",
            "Here are the commands you can use BEEP B00P:",
            "",
            "1. todo DESCRIPTION",
            "   -> Adds a new ToDo task.",
            "",
            "2. deadline DESCRIPTION /by YYYY-MM-DD HHmm",
            "   -> Adds a task with a deadline. Time is optional (defaults to 23:59).",
            "",
            "3. event DESCRIPTION /from time /to time",
            "   -> Adds an event at the specified date/time.",
            "",
            "4. list",
            "   -> Shows all tasks in your list.",
            "",
            "5. mark INDEX",
            "   -> Marks the task at INDEX as done.",
            "",
            "6. unmark INDEX",
            "   -> Marks the task at INDEX as not done.",
            "",
            "7. delete INDEX",
            "   -> Deletes the task at INDEX.",
            "",
            "8. find KEYWORD",
            "   -> Finds tasks containing the given keyword.",
            "",
            "9. save",
            "   -> Saves your tasks to disk immediately.",
            "",
            "10. help",
            "   -> Shows this help message.",
            "",
            "11. reset",
            "   -> Resets your sample.txt tasks.",
            "",
            "11. bye",
            "   -> Exits the chatbot."
    );

    /**
     * Constructs a HelpCommand.
     *
     */
    public HelpCommand() {}

    /**
     * Executes the command by giving the user a cheatsheet to use this app.
     *
     * @param tasks   the TaskList containing the tasks
     * @param ui      the Ui to show messages to the user
     * @param storage the Storage to save the tasks (not used here)
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return HELP_MESSAGE;
    }

    /**
     * Indicates whether this command exits the application.
     *
     * @return false because HelpCommand does not terminate the chatbot
     */
    @Override
    public boolean isExit() { return false; }
}
