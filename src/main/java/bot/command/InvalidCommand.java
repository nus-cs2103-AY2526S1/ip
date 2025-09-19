package bot.command;

import bot.service.FileService;
import bot.task.TaskList;

/**
 * Represents an invalid command that is executed when an unrecognized command is entered.
 * This command displays an error message to the user indicating that the command is not valid.
 */
public class InvalidCommand extends Command {
    public InvalidCommand() {}

    /**
     * Executes the invalid command by displaying an error message to the user.
     *
     * @param taskList the task list (not used in this command)
     * @param fileService the file services (not used in this command)
     */
    @Override
    public void execute(TaskList taskList, FileService fileService) {
        super.setResponse("I can't do this yet, I'm still learning. Please give me some time");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
