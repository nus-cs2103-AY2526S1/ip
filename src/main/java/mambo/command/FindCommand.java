package mambo.command;

import mambo.MamboException;
import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.Task;
import mambo.task.TaskList;

/**
 * Represents a single "find" command that has been passed through to the chatbot
 *
 * @author kentalim2
 */
public class FindCommand extends Command {

    public FindCommand(String argument) {
        super(argument);
    }

    /**
     * Returns the list of tasks whose descriptions contain the keyword passed into the find command.
     * Iterates through the task list and checks each tasks' description against the keyword.
     * If description contains keyword, add the task to a new list.
     * The new list is returned the end of the function, showing all the matches that were found.
     * Throws an exception when there arent any tasks that contain the keyword in their description.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @return Chatbot message
     * @throws MamboException Throws exception when no task descriptions in list contain keyword
     */
    @Override
    public String execute(Ui ui, TaskList tasks, TaskListFileManager file) throws MamboException {
        TaskList tempList = new TaskList();
        String finding = this.getArgument().toLowerCase();

        for (int i = 1; i <= tasks.listSize(); i++) {
            Task temp = tasks.getTask(i);
            if (temp.getDescription().toLowerCase().contains(finding)) {
                tempList.addToList(temp);
            }
        }

        if (tempList.isEmpty()) {
            throw new MamboException("hmm, there arent any tasks in your list that match!");
        } else {
            return "here are all the tasks that match!\n" + tempList;
        }

    }
}
