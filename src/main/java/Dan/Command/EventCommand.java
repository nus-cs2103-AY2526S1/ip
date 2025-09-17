package Dan.Command;

import Dan.Task.TaskList;
import Dan.Task.Task;
import Dan.Task.TaskType;

public class EventCommand extends Command {
    Task event;

    /**
     * Constructs an EventCommand with the specified event task.
     *
     * @param e the event task to be added to the task list
     */
    public EventCommand(Task e) {
        this.event = e;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.EVENT indicating this is an event command
     */
    public CommandType getType() {
        return  CommandType.EVENT;
    }

    /**
     * Executes the event command by adding the event task to the provided task list.
     * The task must be of type EVENT.
     *
     * @param tasks the task list to add the event to
     * @return a confirmation message indicating the event was added and showing
     *         the current number of tasks in the list
     * @throws AssertionError if the task is not of type EVENT
     */
    public String execute(TaskList tasks) {
        assert event.getTaskType() == TaskType.EVENT;
        tasks.add(event);
        return "Got it. I've added this task: \n " + event + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
