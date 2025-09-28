package alpaca.command;

import alpaca.Storage;
import alpaca.task.TaskList;
import alpaca.Ui;

/**
 * Command to list all tasks in the task list.
 * Displays tasks with their index numbers, or an informative message if the list is empty.
 */
public class ListCommand extends Command {

    /**
     * Executes the list command: shows all tasks in the task list with their indices.
     *
     * @param tasks   The task list to display; must not be null.
     * @param ui      The UI handler for displaying messages; must not be null.
     * @param storage The storage handler (not used in this command, can be null).
     * @return A string representation of the task list or a message if empty.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";

        StringBuilder response = new StringBuilder();

        ui.showLine();

        if (tasks.isEmpty()) {
            String msg = "No tasks yet. Maybe add one?";
            response.append(msg).append("\n");
            System.out.println(msg);
        } else {
            String header = "Here are the tasks in your list:";
            response.append(header).append("\n");
            System.out.println(header);

            for (int i = 0; i < tasks.size(); i++) {
                String line = (i + 1) + ". " + tasks.get(i);
                response.append(line).append("\n");
                System.out.println(line);
            }
        }

        ui.showLine();
        return response.toString().trim();
    }
}
