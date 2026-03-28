package ozil.command;

import ozil.exception.ErrorMessages;
import ozil.main.Messages;
import ozil.exception.OzilException;
import ozil.main.TaskList;

/**
 * The command to mark tasks as complete
 */
public class MarkTaskCommand extends Command {
    private int taskNumber;

    /**
     * Constructor for a mark task as done command
     * @param userInput Input given by user
     * @throws OzilException
     */
    public MarkTaskCommand(String userInput) throws OzilException {
        assert !userInput.isEmpty();
        String[] sections = userInput.split("\\s+", 2);
        if (sections.length < 2) {
            throw new OzilException(ErrorMessages.wrongMarkNumber());
        }
        this.taskNumber = Integer.parseInt(sections[1]);
    }

    @Override
    public String run(TaskList tasks) throws OzilException {
        if (this.taskNumber > tasks.getNumberOfTasks()) {
            throw new OzilException(ErrorMessages.wrongMarkNumber());
        }
        tasks.markTaskAsDone(this.taskNumber);
        return "Ok! I have marked this task as completed:\n"
               + tasks.getTask(this.taskNumber).toString();
    }
}
