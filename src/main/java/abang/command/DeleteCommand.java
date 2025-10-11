package abang.command;

import abang.task.TaskList;
import abang.ui.UI;
import abang.storage.Storage;
import abang.exception.AbangException;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private String[] inputArray;

    /**
     * Creates a DeleteCommand with the given input array.
     *
     * @param inputArray the parsed user input split into words
     */
    public DeleteCommand(String[] inputArray) {
        this.inputArray = inputArray;
    }

    /**
     * Executes the delete command by removing the specified task
     * from the task list and saving the updated list to storage.
     *
     * @param taskList the current task list
     * @param ui       the UI object for interaction
     * @param storage  the storage object for saving tasks
     * @throws AbangException if the task index is invalid
     */
    @Override
    public String execute(TaskList taskList, UI ui, Storage storage) throws AbangException {
        int index = Integer.parseInt(inputArray[1]);
        if (index < 1 || index > taskList.numTask()) {
            throw new AbangException("Please key in valid number");
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Noted. I've removed this task:\n");
        sb.append("  ").append(taskList.getTask(index)).append("\n");

        taskList.remove(index);
        int numTask = taskList.numTask();
        sb.append(String.format("Now you have %d tasks in the list.", numTask));

        storage.save(taskList.toFileLines());
        return sb.toString();
    }

}
