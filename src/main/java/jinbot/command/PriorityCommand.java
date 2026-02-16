package jinbot.command;

import jinbot.JinBotException;
import jinbot.task.Priority;
import jinbot.task.Task;
import jinbot.task.TaskList;
import jinbot.ui.Ui;

/**
 * Represents the command to edit priority of specific task.
 */
public class PriorityCommand implements Command {
    private final int index;
    private final Priority priority;

    /**
     * Constructs a priority command by taking index of the task with new value of priority.
     * @param indexString The index of the task for updating its priority.
     * @param priorityString The new priority value to be updated for the task.
     * @throws JinBotException If indexString or priorityString is not valid.
     */
    public PriorityCommand(String indexString, String priorityString) throws JinBotException {
        try {
            this.index = Integer.parseInt(indexString) - 1;
            this.priority = Priority.valueOf(priorityString.toUpperCase());
        } catch (NumberFormatException e) {
            throw new JinBotException("Error! Index must be a number.");
        } catch (IllegalArgumentException e) {
            throw new JinBotException("Error! Priority must be LOW, MEDIUM, or HIGH.");
        }
    }

    /**
     * Executes the priority command by updating the priority of the specific task.
     *
     * @param ui       The UI instance that handles user interaction.
     * @param taskList The list of tasks to be modified or displayed.
     * @return         Returns response.
     */
    @Override
    public String execute(Ui ui, TaskList taskList) {
        Task task = taskList.getTask(index);
        task.setPriority(priority);

        String response = "Set priority of task:\n  " + task;
        ui.printBox(response);
        return response;
    }
}
