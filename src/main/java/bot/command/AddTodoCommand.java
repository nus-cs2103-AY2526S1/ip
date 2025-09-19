package bot.command;

import bot.service.FileService;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to add a to-do task to the task list.
 * This command parses to-do information from user input and creates a new to-do task.
 * The command format should be: "todo <task name>"
 */
public class AddTodoCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an AddTodoCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "todo"
     *                    - commandInfo[1] should contain the task name
     */
    public AddTodoCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the add to-do command by parsing the command information,
     * validating the format, creating a new to-do task, and saving it to file.
     *
     * @param taskList the task list to which the new to-do task will be added
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
                                "todo <Task Name>");
            }

            String taskName = commandInfo[1];

            // Add To-do task
            Task newTask = taskList.addTask(taskName);

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
