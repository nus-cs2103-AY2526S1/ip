package cookie.task;

import java.util.ArrayList;

/**
 * Represents list of tasks.
 * Provides methods to add, remove from list as well as get
 * information like list size and specific tasks in list.
 */
public class TaskList {

    private ArrayList<Task> listOfTasks;

    /**
     * Creates new empty TaskList.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Creates TaskList initialized with a list of tasks.
     *
     * @param listOfLoadedTasks List of tasks to be loaded into TaskList.
     */
    public TaskList(ArrayList<Task> listOfLoadedTasks) {
        this.listOfTasks = listOfLoadedTasks;
    }

    /**
     * Returns number of tasks in the TaskList.
     *
     * @return  Number of tasks in the TaskList.
     */
    public int size() {
        return listOfTasks.size();
    }

    /**
     * Returns task at specified index.
     *
     * @param index of task to be returned
     * @return Task at index provided.
     */
    public Task get(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Adds specified task to the Tasklist.
     *
     * @param taskToBeAdded Task to be added to the TaskList.
     */
    public void add(Task taskToBeAdded) {
        listOfTasks.add(taskToBeAdded);
    }

    /**
     * Removes task at the provided index from the TaskList.
     *
     * @param indexOfTaskToBeRemoved Index of task that is to be removed.
     */
    public void remove(int indexOfTaskToBeRemoved) {
        listOfTasks.remove(indexOfTaskToBeRemoved);
    }

    /**
     * Returns list of tasks with all tasks that match the specified task to find.
     *
     * @param taskToFind Specified task to find.
     * @return List of tasks that match the specified task to find.
     */
    public TaskList find(String taskToFind) {
        TaskList listOfMatchingTasks = new TaskList();
        for (Task currentTask : listOfTasks) {
            String currentTaskDescription = currentTask.getDescription();
            if (currentTaskDescription.contains(taskToFind.strip())) {
                listOfMatchingTasks.add(currentTask);
            }
        }
        return listOfMatchingTasks;
    }
}
