package bot.command;

import bot.service.FileService;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to mark a task as completed.
 * This command takes a task index and marks the corresponding task as done.
 * The command format should be: "mark <task index>"
 */
public class MarkTaskCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs a MarkTaskCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "mark"
     *                    - commandInfo[1] should contain the task index as a positive integer
     */
    public MarkTaskCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the mark task command by parsing the task index, validating the format,
     * marking the specified task as completed, and saving the updated task list to file.
     *
     * @param taskList the task list containing the task to be marked
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
                                "mark <Task Index>");
            }

            int index = Integer.parseInt(commandInfo[1]);

            // Mark task
            Task task = taskList.markTaskAsDone(index);

            assert task != null : "Task mark done should not be null";

            // Write task list to file
            fileService.writeToFile(taskList);

            // Set confirmation message and new status as response
            super.setResponse(ResponseMessage.getMarkTaskSuccessMessage(task));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
