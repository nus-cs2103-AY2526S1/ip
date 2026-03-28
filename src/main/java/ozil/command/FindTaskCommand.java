package ozil.command;

import ozil.exception.OzilException;
import ozil.main.TaskList;

/**
 * Handles the command to find a task.
 */
public class FindTaskCommand extends Command {
    private String keywords;

    /**
     * Constructor for a find task command
     * @param userInput Input by user
     * @throws OzilException
     */
    public FindTaskCommand(String userInput) throws OzilException {
        assert !userInput.isEmpty();
        String[] sections = userInput.split("\\s+", 2);
        if (sections.length < 2) {
            throw new OzilException("Please input a task to find bro.");
        }
        this.keywords = sections[1];
    }

    @Override
    public String run(TaskList tasks) {
        return tasks.findTask(this.keywords);
    }
}
