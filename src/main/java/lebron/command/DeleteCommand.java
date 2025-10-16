package lebron.command;

import java.util.ArrayList;

import lebron.common.Constants;
import lebron.exception.LeBronException;
import lebron.main.Storage;
import lebron.main.Ui;
import lebron.task.TaskList;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final String arguments;

    /**
     * Constructor for DeleteCommand.
     *
     * @param arguments The arguments provided with the delete command, typically the task number to delete.
     */
    public DeleteCommand(String arguments) {
        this.arguments = arguments;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        ArrayList<Integer> tasksToDelete = getTasksIfValid();
        String successfulRemoveMessage = removeTaskMessage(taskList, tasksToDelete);
        taskList.removeTasks(tasksToDelete);

        return successfulRemoveMessage;
    }

    /**
     * Parses and validates the task numbers from the command arguments.
     *
     * @return A list of task indices to be deleted.
     */
    private ArrayList<Integer> getTasksIfValid() throws LeBronException {
        try {
            String[] indexStrings = arguments.split(" ");
            ArrayList<Integer> taskIndices = new ArrayList<>();
            for (String index : indexStrings) {
                taskIndices.add(Integer.parseInt(index.trim()) - 1);
            }
            return taskIndices;
        } catch (NumberFormatException e) {
            throw new LeBronException(Constants.INVALID_TASK_NUMBER_ERROR);
        }
    }
    /**
     * Formats the message to be displayed after tasks are deleted.
     *
     * @param taskList      The current task list.
     * @param tasksToDelete The list of task indices that were deleted.
     * @return A formatted string message.
     */
    private String removeTaskMessage(TaskList taskList, ArrayList<Integer> tasksToDelete) throws LeBronException {
        try {
            StringBuilder removedTasks = new StringBuilder();
            for (int index : tasksToDelete) {
                removedTasks.append(String.format("%d. %s\n", index + 1, taskList.getTask(index)));
            }

            return String.format(Constants.REMOVE_TASKS_MESSAGE_FORMAT,
                    removedTasks, taskList.getSize() - tasksToDelete.size());
        } catch (IndexOutOfBoundsException e) {
            throw new LeBronException(Constants.TASK_NUMBER_OUT_OF_RANGE_ERROR);
        }
    }
}
