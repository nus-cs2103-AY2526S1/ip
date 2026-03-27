package command;

import amogus.FileStorage;
import amogus.UI;
import tasks.TaskList;

/**
 * Represents an instruction for the chatbot to display
 * list of tasks.
 */
public class ListCommand implements Command {

    /**
     * Reads from any existing txt file and lists its contents.
     *
     * @param tasks List of tasks
     * @param ui any display back to the user
     * @param f existing txt file
     */
    @Override
    public String execute(TaskList tasks, UI ui, FileStorage f) {
        if (tasks.isEmpty()) {
            return ui.showMsg(ui.format("Empty List.\n"));
        } else {
            return ui.showTaskList(tasks);
        }
    }

    /**
     * For the program to know when to exit the conversation with user.
     * @return boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
