package mambo.command;

import mambo.MamboException;
import mambo.TaskListFileManager;
import mambo.Ui;
import mambo.task.EventTask;
import mambo.task.TaskList;

/**
 * Represents a single "event" command that has been passed into the chatbot.
 *
 * @author kentalim2
 */
public class EventCommand extends Command {

    public EventCommand(String argument) {
        super(argument);
    }

    @Override
    public String[] splitArgumentIntoDetails() {
        return this.getArgument().split("/from|/to");
    }

    /**
     * Adds an event task to the current list.
     * Gets description and deadline of task by splitting argument at "/from" and "/to".
     * Returns confirmation/failure message sent by chatbot when command is done executing.
     * Throws an exception when the argument of the event task does not follow the required format.
     *
     * @param tasks Task List that is being tracked by chatbot
     * @param file Saved local file containing tasks
     * @return Chatbot message
     * @throws MamboException Throws exception when non-proper format is used to add task
     */
    @Override
    public String execute(Ui ui, TaskList tasks, TaskListFileManager file) throws MamboException {
        String[] taskDetails = splitArgumentIntoDetails();
        // if event is not formatted correctly, [description, start, end], throw an error
        if (taskDetails.length != 3) {
            throw new MamboException("are you sure you are following the proper format for events? "
                    + "it should look like this: \"event *description* /from *time* /to time\"");
        }
        assert(taskDetails.length == 3);
        return tasks.addToList(new EventTask(taskDetails[0].trim(),
                false,
                taskDetails[1].trim(),
                taskDetails[2].trim()));
    }
}
