package ozil.command;

import ozil.exception.ErrorMessages;
import ozil.exception.OzilException;
import ozil.main.TaskList;

/**
 * Runs the command to delete a task
 */
public class DeleteTaskCommand extends Command {
    private int taskNumber;

    public DeleteTaskCommand(String userInput) throws OzilException {
        assert !userInput.isEmpty();
        String[] sections = userInput.trim().split("\\s+", 2);
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
        return tasks.deleteTask(this.taskNumber);
    }
}
