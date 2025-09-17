package Dan.Command;

import Dan.Task.TaskList;
import Dan.Task.Task;
import Dan.Task.TaskType;

public class ToDoCommand extends Command {
    Task toDo;

    /**
     * Constructs a ToDoCommand with the specified task.
     *
     * @param t the task to be added to the task list
     */
    public ToDoCommand(Task t) {
        this.toDo = t;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.TODO indicating this is a todo command
     */
    public CommandType getType() {
        return CommandType.TODO;
    }

    /**
     * Executes the todo command by adding the task to the provided task list.
     * The task must be of type TODO.
     *
     * @param tasks the task list to add the task to
     * @return a confirmation message indicating the task was added and showing
     *         the current number of tasks in the list
     * @throws AssertionError if the task is not of type TODO
     */
    public String execute(TaskList tasks) {
        assert toDo.getTaskType() == TaskType.TODO;
        tasks.add(toDo);
        return "Got it. I've added this task: \n " + toDo + "\nNow you have " + tasks.size() + " tasks in the list.";
    }
}
