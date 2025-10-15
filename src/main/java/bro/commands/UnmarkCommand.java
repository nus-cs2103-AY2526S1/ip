package bro.commands;

import bro.tasks.Task;
import bro.tasks.Tasks;
import bro.utils.FileIo;

/**
 * Represents a command to unmark a task as not done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Creates a new UnmarkCommand.
     */
    @Override
    public String execute(Tasks tasks) {
        try {
            Task task = tasks.getEntry(taskIndex);
            String output = task.markAsUndone();
            FileIo.updateByDescription(task.toDataString());
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
