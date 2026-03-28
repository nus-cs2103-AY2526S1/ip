package bugsbunny.commands;

import java.util.ArrayList;

import bugsbunny.app.Ui;
import bugsbunny.storage.Storage;
import bugsbunny.tasks.Task;
import bugsbunny.tasks.TaskList;

/**
 * Prints the current list of task to the chat.
 */
public class ListCommand extends Command {

    public ListCommand(String args) {
        super(args);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getList();
        String output = "Here are the tasks in your list:";

        for (int i = 0; i < list.size(); i++) {
            output += String.format("\n %d. %s", i + 1, list.get(i).toString());
        }
        return output;
    }
}
