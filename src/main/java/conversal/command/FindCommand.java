package conversal.command;

import java.util.ArrayList;

import conversal.exception.ConversalException;
import conversal.storage.Storage;
import conversal.task.Task;
import conversal.task.TaskList;
import conversal.ui.Ui;

/**
 * Finds and lists all tasks whose descriptions contain the given keyword.
 */
public class FindCommand implements Command {

    private final String input;

    /**
     * Constructs a {@code FindCommand} with the full user input.
     *
     * @param fullInput the command string containing the keyword to search for
     */
    public FindCommand(String fullInput) {
        this.input = fullInput;
    }

    /**
     * Executes the find command by searching the task list for tasks whose
     * descriptions contain the keyword, then displays the results.
     *
     * @param tasks   task list to search
     * @param storage storage (unused here but required by signature)
     * @param ui      UI component to show results or errors
     * @throws ConversalException if the input is missing a keyword
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws ConversalException {
        if (input.length() <= 5) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionFind());
        }

        String keyword = input.substring(5).trim();
        if (keyword.isEmpty()) {
            throw new ConversalException("Ah, I got it! " + ui.getInstructionFind());
        }

        ArrayList<Task> matches = tasks.find(keyword);
        ui.showFound(matches);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
