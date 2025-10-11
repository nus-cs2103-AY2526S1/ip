package abang.command;

import abang.exception.AbangException;
import abang.storage.Storage;
import abang.task.TaskList;
import abang.ui.UI;

/**
 * Represents a command to find tasks containing a given keyword.
 */
public class FindCommand extends Command {
    private String[] inputArray;

    /**
     * Creates a FindCommand with the given input array.
     *
     * @param inputArray the parsed user input split into words
     */
    public FindCommand(String[] inputArray) {
        this.inputArray = inputArray;
    }

    /**
     * Executes the find command by searching the task list for tasks
     * that contain the given keyword and printing the results.
     *
     * @param taskList the current task list
     * @param ui       the UI object for interaction
     * @param storage  the storage object for saving tasks
     * @throws AbangException if no keyword is provided
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws AbangException {
        if (inputArray.length < 2) {
            throw new AbangException("Please provide a keyword to find.");
        }
        String keyword = inputArray[1];
        TaskList results = taskList.find(keyword);

        StringBuilder sb = new StringBuilder();
        sb.append("Here are the matching tasks in your list:\n");
        sb.append(results);
        return sb.toString();
    }

}
