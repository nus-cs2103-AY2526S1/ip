package abang.command;

import abang.task.TaskList;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private String[] inputArray;

    /**
     * Creates an UnmarkCommand with the given input array.
     *
     * @param inputArray the parsed user input split into words
     */
    public UnmarkCommand(String[] inputArray) {
        this.inputArray = inputArray;
    }

    /**
     * Executes the unmark command by marking the specified task as not done
     * and saving the updated task list to storage.
     *
     * @param taskList the current task list
     * @param ui       the UI object for interaction
     * @param storage  the storage object for saving tasks
     * @throws AbangException if the task index is invalid
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws AbangException {
        if (inputArray.length < 2) {
            throw new AbangException("Please provide a task number to mark");
        }
        String number = inputArray[1];
        int index = Integer.parseInt(number);
        if (index < 1 || index > taskList.numTask()) {
            throw new AbangException("Please key in a valid number");
        }

        taskList.unmark(index);

        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've unmarked this task as not done:\n");
        sb.append("  ").append(taskList.getTask(index));

        storage.save(taskList.toFileLines());
        return sb.toString();
    }

}
