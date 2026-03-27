package marvin.task;

import java.io.Serializable;
import java.util.ArrayList;

import marvin.MarvinException;
import marvin.Personality;

/**
 * Contains the logic for managing the overall task list for Marvin.
 */
public class TaskList implements Serializable {
    private final ArrayList<Task> tasks;
    private int size = 0;
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a Task object to the Task List.
     *
     * @param task a Task object representing the task to be added.
     */
    public void addToList(Task task) {
        this.tasks.add(task);
        this.size++;
        assert (this.tasks.contains(task)) : "New Item not added to array!";
    }

    /**
     * Marks a task at a given index as the supplied done state.
     *
     * @param locator A string representation of the location of the task e.g. 1.12.4
     * @param isDone The state at which to set the task object.
     * @return The string representation of the object after the operation is complete.
     * @throws ArrayIndexOutOfBoundsException If index supplied is not a valid index for a task.
     */
    public String markTask(String locator, boolean isDone) {
        // Perform mark
        Task task = searchForTask(locator);

        Task parent = task.getParentTask();
        if (parent != null && !parent.getIsDone()) {
            throw new MarvinException(Personality.getPrerequisiteIncompleteText());
        }
        task.setIsDone(isDone);
        assert (searchForTask(locator).getIsDone() == isDone) : "Item not updated to correct isDone status";
        return task.toString();
    }

    /**
     * Removes a task at the given index in the task list.
     *
     * @return The string representation of the object deleted.
     * @throws ArrayIndexOutOfBoundsException If index supplied is not a valid index for a task.
     */
    public String removeTask(String locator) {
        Task task = this.searchForTask(locator);
        if (!task.getDependentTasks().isEmpty()) {
            throw new MarvinException(Personality.getDeleteWithDependentsText());
        }

        this.tasks.remove(task);
        task.unlinkParent();
        this.size--;

        assert (!this.tasks.contains(task)) : "Item not removed from array!";

        return task.toString();
    }

    /**
     * Filters tasks to find matching descriptions.
     *
     * @param query The filter to search the descriptions for.
     * @return String representation of the tasks that match the query.
     */
    public String searchTasks(String query) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            if (this.tasks.get(i).getDescription().contains(query)) {
                // i. [task string] \n
                sb.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
            }
        }
        return sb.toString();
    }

    /**
     * Sets a task to be dependent on another task's completion
     *
     * @param parentLocator The string representation of where the task that has to be completed first is
     * @param subTaskLocator The string representation of where the task that can be completed after is
     */
    public void setTaskToDoAfter(String parentLocator, String subTaskLocator) {
        Task parent = this.searchForTask(parentLocator);
        Task subTask = this.searchForTask(subTaskLocator);
        parent.setChildTask(subTask);
        this.tasks.remove(subTask);
    }

    /**
     * Returns the count of objects in the task list.
     *
     * @return How many objects are in the list.
     */
    public int getCount() {
        return this.size;
    }

    /**
     * Returns the stylized string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            // i. [task string] \n
            sb.append(i + 1).append(". ").append(this.tasks.get(i)).append("\n");
            recursePrintChild(String.valueOf(i + 1), 2, this.tasks.get(i), sb);
        }
        return sb.toString();
    }

    private void recursePrintChild(String preamp, int indentation, Task toPrint, StringBuilder sb) {
        for (int i = 0; i < toPrint.getDependentTasks().size(); i++) {
            Task child = toPrint.getDependentTasks().get(i);
            sb.append(" ".repeat(indentation)).append("---->")
                    .append(preamp).append(".").append(i + 1).append(" ").append(child).append("\n");


            recursePrintChild(preamp + "." + (i + 1), indentation + 2, child, sb);
        }
    }

    private Task searchForTask(String input) {
        // assume input is in 1.1.1.1
        ArrayList<Task> currentListToSearch = this.tasks;
        Task task = null;
        String[] parts = input.split("\\.");
        for (String part : parts) {
            int index;
            try {
                index = Integer.parseInt(part);
                task = currentListToSearch.get(index - 1);
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                throw new MarvinException(Personality.getInvalidIndexText());
            }
            currentListToSearch = task.getDependentTasks();
        }
        return task;
    }
}
