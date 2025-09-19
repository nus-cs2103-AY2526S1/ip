package bot.command;

import bot.service.FileService;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.task.Task;
import bot.ui.ResponseMessage;

/**
 * Represents a command to add a deadline task to the task list.
 * This command parses deadline information from user input and creates a new deadline task.
 * The command format should be: "deadline <task name> /by <deadline date>"
 */
public class AddDeadlineCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an AddDeadlineCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "deadline"
     *                    - commandInfo[1] should contain the task name and deadline separated by " /by "
     */
    public AddDeadlineCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the add deadline command by parsing the command information,
     * validating the format, creating a new deadline task, and saving it to file.
     *
     * @param taskList the task list to which the new deadline task will be added
     * @param fileService the file services for writing the updated task list to storage
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        try {
            // Validate command format, re-prompt if incorrect command format
            boolean isValidCommandInfo = commandInfo.length == 2;
            if (!isValidCommandInfo) {
                throw new InvalidCommandException(
                        "Oh no, command format is not right, let me give you a hint: " +
                                "deadline <Task Name> /by <Date>");
            }

            // Split the command info with by " /by " to extract task name and deadline
            String[] deadlineInfo = commandInfo[1].split(" /by ");

            // Validate command format, re-prompt if incorrect command format
            boolean isValidDeadlineInfo = deadlineInfo.length == 2;
            if (!isValidDeadlineInfo) {
                throw new InvalidCommandException(
                        "Oh no, command format is not right, let me give you a hint: " +
                                "deadline <Task Name> /by <Date>");
            }

            String taskName = deadlineInfo[0];
            String deadline = deadlineInfo[1];

            // Add deadline task
            Task newTask = taskList.addTask(taskName, deadline);

            assert newTask != null : "Task added should not be null";

            // Write task list to file
            fileService.writeToFile(taskList);

            // Set success message
            super.setResponse(ResponseMessage.getAddTaskSuccessMessage(newTask, taskList.getSize()));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
