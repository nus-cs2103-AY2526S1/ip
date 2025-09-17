package Dan.Command;

import Dan.Task.Task;
import Dan.Task.TaskList;

import java.util.ArrayList;

public class RemindCommand extends Command {
    int daysFromNow;

    /**
     * Constructs a RemindCommand that will retrieve tasks due within the specified number of days.
     *
     * @param daysFromNow the number of days from today to search for tasks that need reminders
     */
    public RemindCommand(int daysFromNow) {
        this.daysFromNow = daysFromNow;
    }

    /**
     * Returns the command type for this command.
     *
     * @return CommandType.REMINDER indicating this is a reminder command
     */
    public CommandType getType() {
        return CommandType.REMINDER;
    }

    /**
     * Executes the reminder command by retrieving and formatting tasks that are due
     * within the specified number of days from now.
     *
     * @param tasks the task list to search for tasks needing reminders
     * @return a formatted string containing a numbered list of tasks that need reminders,
     *         or an empty string if no tasks are found
     */
    public String execute(TaskList tasks) {
        ArrayList<Task> tasksToRemind = tasks.getTasksToRemind(daysFromNow);
        String response = "";

        for(int i = 0; i < tasksToRemind.size(); i++) {
            response += i + 1 + "." + tasksToRemind.get(i) + "\n";
        }

        return  response;
    }
}
