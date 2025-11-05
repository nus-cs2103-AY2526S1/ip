package sunoo.command;

import java.util.ArrayList;

import sunoo.task.Task;
import sunoo.task.TaskList;
import sunoo.ui.Ui;

/**
 * Represents an executable command that lists all the tasks in the tasklist.
 */
public class ListCommand extends Command {

    /**
     * {@inheritDoc}
     * <p>Instructs Ui to show all the tasks in the tasklist to the user.</p>
     */
    @Override
    public String execute(TaskList tasks) {
        ArrayList<String> tasksToShow = new ArrayList<>();
        ArrayList<Task> taskList = tasks.getTasks();
        for (int i = 1; i <= taskList.size(); i++) {
            tasksToShow.add(i + ". " + taskList.get(i - 1));
        }
        String response = Ui.joinLines(tasksToShow);
        response = Ui.joinLines(
                "ENGENE, here are the tasks recorded by ddeonu:",
                response);
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
