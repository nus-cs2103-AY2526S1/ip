package sunoo.command;

import sunoo.exception.SunooException;
import sunoo.task.Task;
import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that adds a task to the tasklist.
 */
public class AddCommand extends Command {

    /** Task to be added */
    private final Task taskToAdd;

    /**
     * Creates an AddCommand with the task to be added.
     *
     * @param taskToAdd Task to be added.
     */
    public AddCommand(Task taskToAdd) {
        this.taskToAdd = taskToAdd;
    }

    /**
     * {@inheritDoc}
     * <p>Adds the task to the tasklist.</p>
     */
    @Override
    public String execute(TaskList tasks) {
        if (tasks.contains(taskToAdd)) {
            throw new SunooException("ENGENE, the same task already exists!");
        }
        int numTasksBefore = tasks.getNumTasks();
        String response = Ui.joinLines(
                "Got it! Ddeonu has added this task for you:",
                tasks.addTask(taskToAdd).toString(),
                "Now you have " + tasks.getNumTasks() + " task(s) in the list, hwaiting!");
        int numTasksAfter = tasks.getNumTasks();
        assert numTasksBefore + 1 == numTasksAfter;
        return Ui.wrapWithHorizontalLines(response);
    }

    /**
     * {@inheritDoc}
     *
     * @return false.
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
