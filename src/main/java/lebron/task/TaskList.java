package lebron.task;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Manages a list of tasks, allowing for addition, removal, and retrieval of tasks.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Constructor to initialize the TaskList with an existing list of tasks.
     *
     * @param tasks An ArrayList of Task objects to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Default constructor to initialize an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }
    /**
     * Adds a new task to the task list.
     *
     * @param task The Task object to be added to the list.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    public int getSize() {
        return tasks.size();
    }
    public boolean isEmpty() {
        return tasks.isEmpty();
    }
    public Task getTask(int index) {
        return tasks.get(index);
    }
    /**
     * Removes tasks from the task list based on the provided list of indices.
     *
     * @param tasksToDelete An ArrayList of integers representing the indices of tasks to be removed.
     */
    public void removeTasks(ArrayList<Integer> tasksToDelete) {
        // Sort indices in descending order to avoid shifting issues during removal
        tasksToDelete.sort(Comparator.reverseOrder());
        for (int index : tasksToDelete) {
            tasks.remove(index);
        }
    }
}
