package Dan.Command;

import Dan.Task.TaskList;

public class ListCommand extends Command {
    /**
     * Returns the command type for this command.
     *
     * @return CommandType.LIST indicating this is a list command
     */
    public CommandType getType() {
        return  CommandType.LIST;
    }

    /**
     * Executes the list command by retrieving all tasks from the task list
     * and formatting them as a numbered list for display.
     *
     * @param tasks the task list containing all tasks to be displayed
     * @return a formatted string containing all tasks as a numbered list,
     *         or an empty string if the task list is empty
     */
    public String execute(TaskList tasks) {
        String response = "";
        for(int i = 1; i < tasks.size() + 1; i++) {
            response += i + "." + tasks.getTask(i) + "\n";
        }
        return response;
    }
}
