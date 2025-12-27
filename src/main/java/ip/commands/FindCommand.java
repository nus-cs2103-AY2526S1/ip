package ip.commands;

import ip.exceptions.UnknownInputException;
import ip.storage.Storage;
import ip.tasks.Task;
import ip.tasks.TaskList;
import ip.ui.Ui;

/**
 * Command to find a task by searching for a keyword in the task description
 */
public class FindCommand implements Command {
    private static final String PREFIX = "find ";
    private static final int PREFIX_LENGTH = PREFIX.length();

    /**
     * Finds tasks in the task list that has matching keyword
     *
     * @throws UnknownInputException if find has no keyword or list is empty
     * @inheritDoc
     */
    @Override
    public String execute(String input, Ui ui, Storage storage, TaskList tasks)
            throws UnknownInputException {

        if (input.length() <= PREFIX_LENGTH) {
            throw new UnknownInputException("'find' needs a keyword after");
        }

        if (tasks.isEmpty()) {
            throw new UnknownInputException("what do you hope to find in an empty list?");
        }

        String keyword = input.substring(PREFIX_LENGTH).trim();

        TaskList results = new TaskList();
        int max = tasks.size();

        for (int i = 0; i < max; i++) {
            Task curr = tasks.get(i);
            if (curr.getDescription().contains(keyword)) {
                results.addTask(curr);
            }
        }

        if (results.isEmpty()) {
            return ui.showNoResult(keyword);
        }

        assert !results.isEmpty() : "Results is empty";

        return ui.showFindCommand(results);

    }
}
