package mambo.command;

import mambo.MamboException;
import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.DeadlineTask;
import mambo.task.TaskList;

/**
 * Represents a single "deadline" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class DeadlineCommand extends Command {

    public DeadlineCommand(String argument) {
        super(argument);
    }

    @Override
    public String[] splitArgumentIntoDetails() {
        return this.getArgument().split("/by");
    }

    /**
     * Adds a deadline task to the current list.
     * Gets description and deadline of task by splitting argument at "/by".
     * Returns confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when the argument of the deadline task does not follow the required format.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @return Chatbot message
     * @throws MamboException Throws exception when non-proper format is used to add task
     */
    @Override
    public String execute(Ui ui, TaskList tasks, TaskListFileManager file) throws MamboException {
        String[] taskDetails = splitArgumentIntoDetails();
        // if deadline is not formatted correctly, [description, deadline], throw an error message
        if (taskDetails.length != 2) {
            throw new MamboException("are you sure you are following the proper format for deadline tasks? "
                    + "it should look like this: \"deadline *description* /by *time/date*\"");
        }
        return tasks.addToList(new DeadlineTask(taskDetails[0].trim(),
                false, taskDetails[1].trim()));
    }
}
