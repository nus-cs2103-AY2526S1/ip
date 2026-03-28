package ozil.command;

import ozil.exception.ErrorMessages;
import ozil.main.Messages;
import ozil.exception.OzilException;
import ozil.main.TaskList;

/**
 * Class that runs the unmark command
 */
public class UnmarkTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for Unmark task command
     * @param userInput Input given by user
     * @throws OzilException
     */
    public UnmarkTaskCommand(String userInput) throws OzilException {
        assert !userInput.isEmpty();
        String[] sections = userInput.split("\\s+", 2);
        if (sections.length < 2) {
            throw new OzilException(ErrorMessages.wrongMarkNumber());
        }
        this.taskNumber = Integer.parseInt(sections[1]);
    }

    /**
     * Runs the unmark task command.
     * @param tasks Tasklist of current chatbot
     * @throws OzilException Handles exception
     */
    @Override
    public String run(TaskList tasks) throws OzilException {
        if (this.taskNumber > tasks.getNumberOfTasks()) {
            throw new OzilException(ErrorMessages.wrongMarkNumber());
        }
        tasks.markTaskAsUndone(this.taskNumber);
        return "Ok! I have marked this task as has not been completed:\n"
                + tasks.getTask(this.taskNumber).toString();
    }
}
