package bot.command;

import bot.service.FileService;
import bot.exception.InvalidCommandException;
import bot.task.TaskList;
import bot.ui.ResponseMessage;
import bot.task.Task;

/**
 * Represents a command to add an event task to the task list.
 * This command parses event information from user input and creates a new event task.
 * The command format should be: "event <task name> /from <start date> /to <end date>"
 */
public class AddEventCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs an AddEventCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "event"
     *                    - commandInfo[1] should contain the task name, start date, and end date
     *                      in the format: "<task name> /from <start date> /to <end date>"
     */
    public AddEventCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the add event command by parsing the command information,
     * validating the format, creating a new event task, and saving it to file.
     *
     * @param taskList the task list to which the new event task will be added
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
                                "event <Task Name> /from <Start Date> /to <End Date>");
            }

            // Split the command info with by "/from" to extract task name
            // eventInfo[0] = task name
            // eventInfo[1] = start date and end date
            String[] eventInfo = commandInfo[1].split(" /from ");

            // Validate command format, re-prompt if incorrect command format
            boolean isValidEventInfo = eventInfo.length == 2;
            if (!isValidEventInfo) {
                throw new InvalidCommandException(
                        "Oh no, command format is not right, let me give you a hint: " +
                                "event <Task Name> /from <Start Date> /to <End Date>");
            }

            // Extract start and end date from the separated eventInfo (taskName, date)
            String[] dateInfo = eventInfo[1].split(" /to ");

            // Validate command format, re-prompt if incorrect command format
            boolean isValidDateInfo = dateInfo.length == 2;
            if (!isValidDateInfo) {
                throw new InvalidCommandException(
                        "Oh no, command format is not right, let me give you a hint: " +
                                "event <Task Name> /from <Start Date> /to <End Date>");
            }

            String taskName = eventInfo[0];
            String from = dateInfo[0];
            String to = dateInfo[1];

            // Add event task
            Task newTask = taskList.addTask(taskName, from, to);

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
