package leo.functions.commands;

import java.io.IOException;

import leo.display.Messages;
import leo.functions.task.Task;
import leo.functions.task.TaskList;

/**
 * Represents the function that is called when the user wants to delete a task.
 */
public class DeleteTaskCommand implements Command {
    /**
     * Deletes a task from the list.
     *
     * @param indexToDel Index of task to be deleted.
     * @param listItems List of tasks.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    public static void deleteTask(int indexToDel, TaskList listItems) throws IndexOutOfBoundsException, IOException {
        try {
            Task taskToDel = listItems.getItemAtIndex(indexToDel - 1);
            listItems.deleteItemAtIndex(indexToDel - 1);
            System.out.println("Understood, I've removed the task:");
            System.out.println("\t" + taskToDel.toString());
            Messages.taskCount(listItems.getSize());
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Task to delete is out of the list length.");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param indexToDel Index of task to be deleted.
     * @param listItems List of tasks.
     * @return Formatted string of output.
     * @throws IndexOutOfBoundsException If index given is more than the list length.
     * @throws IOException If the file storing data cannot be found.
     */
    public static String deleteTaskReturnOutput(int indexToDel, TaskList listItems)
            throws IndexOutOfBoundsException, IOException {
        try {
            Task taskToDel = listItems.getItemAtIndex(indexToDel - 1);
            listItems.deleteItemAtIndex(indexToDel - 1);

            return String.format("Understood, I've removed the task:"
                    + "\n\t"
                    + taskToDel.toString()
                    + "\n\t"
                    + Messages.taskCountReturnOutput(listItems.getSize()));
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("Task to delete is out of the list length.");
        }
    }
}
