package Dan.Command;

import Dan.Task.Task;
import Dan.Task.TaskList;
import Dan.Task.TaskType;

public class DeadlineCommand extends Command {
    Task deadline;

    /**
     * Constructs a DeadlineCommand with the specified deadline task.
     *
     * @param d the deadline task to be added to the task list
     */
    public DeadlineCommand(Task d) {
        this.deadline = d;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.DEADLINE indicating this is a deadline command
     */
    public CommandType getType() {
        return  CommandType.DEADLINE;
    }

    /**
     * Executes the deadline command by adding the deadline task to the provided task list.
     * The task must be of type DEADLINE.
     *
     * @param tasks the task list to add the deadline to
     * @return a confirmation message indicating the deadline was added and showing
     *         the current number of tasks in the list
     * @throws AssertionError if the task is not of type DEADLINE
     */
    public String execute(TaskList tasks) {
        assert deadline.getTaskType() == TaskType.DEADLINE;
        tasks.add(deadline);
        return "Got it. I've added this task: \n " + deadline + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
