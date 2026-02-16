package mambo.command;

import mambo.MamboException;
import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.TaskList;

/**
 * Represents a single "list" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class ListCommand extends Command {

    public ListCommand(String argument) {
        super(argument);
    }

    /**
     * Returns the current list of tasks to display to the user
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @return Chatbot message
     * @throws MamboException Throws exception non-proper format is used to add task
     */
    @Override
    public String execute(Ui ui, TaskList tasks, TaskListFileManager file) {
        return "these are your tasks!\n" + tasks;
    }
}
