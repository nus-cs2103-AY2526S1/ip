package broccoli.Command;

import broccoli.Storage;
import broccoli.TaskList;
import broccoli.Tasks.Task;
import broccoli.Ui;

public class AddCommand extends Command {
    private final String taskDescription;

    public AddCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Executes the add command to create and store a new task.
     * Parses the task description to determine the task type, creates the appropriate
     * Task object, adds it to the task list, saves to storage, and returns a
     * confirmation message with the updated task count.
     *
     * @param taskList The task list to add the new task to
     * @param ui The user interface for displaying messages
     * @param storage The storage system for persisting tasks
     * @return A confirmation message indicating the task was added successfully,
     *         or an error message if the task format is invalid
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task newTask = Task.checkTask(taskDescription);
            taskList.add(newTask);
            storage.writeToFile();
            String displayContent1 = "Got it. I've added this task:\n" + newTask.toString();
            String displayContent2 = "Now you have " + taskList.getList().size() + " tasks in the list.";
            String output = displayContent1 + "\n" + displayContent2;
            return output;
        } catch (IllegalArgumentException e) {
            return e.getMessage();
        }
    }
}


