package bot.command;

import bot.exception.InvalidCommandException;
import bot.service.FileService;
import bot.task.TaskList;
import bot.ui.ResponseMessage;

/**
 * Represents a command to sort tasks in the task list.
 * This command allows users to sort tasks either alphabetically by name or chronologically by date.
 * The command format should be: "sort name" or "sort date"
 */
public class SortCommand extends Command {
    private final String[] commandInfo;

    /**
     * Constructs a SortCommand with the provided command information.
     *
     * @param commandInfo an array containing the command details where:
     *                    - commandInfo[0] should be "sort"
     *                    - commandInfo[1] should be either "name" or "date"
     */
    public SortCommand(String[] commandInfo) {
        this.commandInfo = commandInfo;
    }

    /**
     * Executes the sort command by validating the command format, sorting the task list
     * based on the specified sort type, saving the sorted task list to file, and
     * setting an appropriate response message.
     * <p>
     * If the command format is invalid or the sort type is not recognized, an error
     * message is set as the response.
     * </p>
     *
     * @param taskList the task list to be sorted
     * @param fileService the file service for writing the sorted task list to storage
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        try {
            // Validate command format, re-prompt if incorrect command format
            boolean isValidCommandInfo = commandInfo.length == 2;
            if (!isValidCommandInfo) {
                throw new InvalidCommandException(
                        """
                                Oh no, command format is not right, let me give you a hint:\s
                                sort name
                                sort date""");
            }

            String sortType = commandInfo[1];

            // Sort task list based on sorting typing
            switch (sortType) {
            case "name" -> taskList.sortTaskByName();
            case "date" -> taskList.sortTaskByDate();
            default -> throw new InvalidCommandException(
                    """
                            Oh no, command format is not right, let me give you a hint: \s
                            sort name
                            sort date""");
            }

            // Write task list to file
            fileService.writeToFile(taskList);

            // Set success message
            super.setResponse(ResponseMessage.getSortListMessage(taskList.getTaskList()));
        } catch (Exception e) {
            super.setResponse(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
