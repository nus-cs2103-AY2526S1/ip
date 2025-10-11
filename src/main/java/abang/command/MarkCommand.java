package abang.command;

import abang.task.TaskList;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command{
    private String[] inputArray;

    /**
     * Creates a MarkCommand with the given input array.
     *
     * @param inputArray the parsed user input split into words
     */
    public MarkCommand(String[] inputArray) {
        this.inputArray = inputArray;
    }

    /**
     * Executes the mark command by marking the specified task as done
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

        taskList.mark(index);

        StringBuilder sb = new StringBuilder();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append("  ").append(taskList.getTask(index));

        storage.save(taskList.toFileLines());
        return sb.toString();
    }

}
