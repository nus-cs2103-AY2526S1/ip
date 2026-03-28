package ozil.command;

import ozil.exception.ErrorMessages;
import ozil.main.Messages;
import ozil.exception.OzilException;
import ozil.main.TaskList;
import ozil.task.TodoTask;

/**
 * Handles the command ot add a todo task
 */
public class AddTodoTaskCommand extends Command {
    private String userInputDescription;

    /**
     * Constructor for todo command
     * @param userInput input by user
     * @throws OzilException
     */
    public AddTodoTaskCommand(String userInput) throws OzilException {
        assert !userInput.isEmpty();
        String[] sections = userInput.split("\\s+", 2);
        if (sections.length < 2) {
            throw new OzilException(ErrorMessages.taskDescriptionError("todo"));
        }
        this.userInputDescription = sections[1];
    }

    @Override
    public String run(TaskList tasks) {
        TodoTask task = new TodoTask(this.userInputDescription);
        tasks.addTaskToList(task);
        return Messages.printTaskAddMessage(task, tasks.getNumberOfTasks());
    }
}
