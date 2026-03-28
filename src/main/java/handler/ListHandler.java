package handler;

import hachi.Task;
import hachi.TaskList;

/**
 * Handles the {@code list} command.
 *
 * <p>This handler displays all tasks in the {@link TaskList}. If the list is empty,
 * it returns a friendly message indicating that there are no tasks left.</p>
 *
 * <p>The output is formatted as a numbered list of tasks, each task represented
 * by its {@link Task#toString()} value.</p>
 */
public class ListHandler implements Manager {

    /**
     * Executes the list command by retrieving all tasks from the given
     * {@link TaskList} and formatting them into a numbered string.
     *
     * @param tasks the task list to display
     * @return a formatted string containing all tasks, or a message if the list is empty
     */
    @Override
    public String execute(TaskList tasks) {
        StringBuilder sb = new StringBuilder();
        if (tasks.getTasks().isEmpty()) {
            return "Looks like you are all done!";
        }
        sb.append("Here you go!\n");
        for (int i = 0; i < tasks.getTasks().size(); i++) {
            Task current = tasks.getTasks().get(i);
            sb.append(String.format("%d. %s", i + 1, current.toString()));
            sb.append("\n");
        }
        return sb.toString();
    }
}

