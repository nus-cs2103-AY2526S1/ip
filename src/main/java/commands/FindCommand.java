package commands;

import java.util.ArrayList;

import exceptions.DawaeException;
import taskstuff.Task;
import taskstuff.TaskList;
import ui.Ui;
import storage.Storage;

public class FindCommand extends Command {
    private final String args;

    /**
     * Creates a find command using the raw argument string.
     *
     * @param args the keyword to search for in task descriptions.
     */
    public FindCommand(String args) {
        this.args = args;
    }

    /** {@inheritDoc} */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DawaeException {
        String keyword = (args == null) ? "" : args.trim().toLowerCase();
        if (keyword.isEmpty()) {
            throw new DawaeException("Brah pls give a KEYWORD");
        }
        ArrayList<Task> results = tasks.findContaining(keyword);
        return ui.showFound(results);
    }
}