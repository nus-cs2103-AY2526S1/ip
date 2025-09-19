package bot.command;

import bot.service.FileService;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to remove a task from the task list.
 * This command takes a task index and removes the corresponding task from the list.
 * The command format should be: "delete <task index>"
 */
public class RemoveTaskCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs a RemoveTaskCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "delete"
     *                    - commandInfo[1] should contain the task index as a positive integer
     */
    public RemoveTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the remove task command by parsing the task index, validating the format,
     * removing the specified task from the task list, and saving the updated task list to file.
     *
     * @param taskList the task list from which the task will be removed
     * @param fileService the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        try {
            // Validate command format, re-prompt if incorrect command format
            boolean isValidCommandInfo = commandInfo.length == 2 && commandInfo[1].matches("\\d+");
            if (!isValidCommandInfo) {
                throw new InvalidCommandException(
                        "Oh no, command format is not right, let me give you a hint: " +
                                "delete <Task Index>");
            }

            int index = Integer.parseInt(commandInfo[1]);

            Task task = taskList.removeTask(index);

            assert task != null : "task should not be null";

            // Write task list to file
            fileService.writeToFile(taskList);

            // Set confirmation message and list count as response
            super.setResponse(ResponseMessage.getRemoveTaskSuccessMessage(task, taskList.getSize()));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
