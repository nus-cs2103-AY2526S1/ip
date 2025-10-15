package bro.commands;

import bro.tasks.Task;
import bro.tasks.Tasks;
import bro.utils.FileIo;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Creates a new DeleteCommand with the given task index.
     *
     * @param taskIndex The index of the task to be deleted (1-based).
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes the command and returns the result as a string.
     *
     * @return The result of executing the command.
     */
    @Override
    public String execute(Tasks tasks) {
        try {
            Task task = tasks.getEntry(taskIndex);
            String deletedTaskData = task.toDataString();
            String output = tasks.deleteTask(taskIndex);
            FileIo.deleteByEntry(deletedTaskData);
            return output;
        } catch (IndexOutOfBoundsException e) {
            return "Sorry bro, can you give me a valid task number?";
        } catch (NumberFormatException e) {
            return "Sorry bro, can you give me a number?";
        } catch (Exception e) {
            return "Hmmmm, something's not right bro!";
        }
    }
}

