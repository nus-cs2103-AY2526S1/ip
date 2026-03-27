package david.command;

import david.exception.FindException;

import david.exception.DavidException;
import david.storage.Storage;
import david.task.TaskList;

public class FindCommand extends Command {
    String keyword;

    /**
     * Initialises a command to delete a Task.
     * @param args User input.
     * @throws DavidException If input is not in valid format.
     */
    public FindCommand(String args) throws DavidException {
        if (args.trim().isEmpty()) {
            throw new FindException("Find what leh? Don't leave the keyword empty can or not?");
        }
        this.keyword = args.trim();
        assert keyword != null && !keyword.isEmpty() : "Keyword should not be null or empty";
    }

    /**
     * Finds the tasks which match the keyword.
     *
     * @param tasks List of Tasks.
     * @param storage User's data storage.
     * @return Text to be displayed.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        TaskList tl = tasks.findTasks(keyword);
        if (tl.size() == 0) {
            return "    Bro you sure you search the write word? No results leh.";
        } else {
            return "    Eh here is your matching tasks ok, I took very long to find:\n" + tl;
        }
    }
}
