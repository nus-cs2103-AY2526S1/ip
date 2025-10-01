package arn;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 */

public class TaskList {
    protected ArrayList<Task> taskList;

    /**
     * Constructs a TaskList with an initial set of tasks.
     *
     * @param taskList the list of tasks to initialize with
     */
    public TaskList(ArrayList<Task> taskList) {
        assert taskList != null : "task list must not be null";
        this.taskList = taskList;
    }

    public ArrayList<Task> get() {
        return taskList;
    }


    /**
     * Retrieves a task at the specified index.
     *
     * @param index the index of the task to retrieve
     * @throws ArnException if the index is out of bounds
     */
    public Task get(int index) throws ArnException {
        if (index < 0 || index >= taskList.size()) {
            throw new ArnException("Invalid task number");
        }
        return taskList.get(index);
    }

    /**
     * Adds a new task to the list.
     *
     * @param task the task to add
     */
    public void add(Task task) {
        taskList.add(task);
    }


    /**
     * Removes a task at the specified index.
     *
     * @param index the index of the task to remove
     * @throws ArnException if the index is out of bounds
     */
    public Task remove(int index) throws ArnException {
        if (index < 0 || index >= taskList.size()) {
            throw new ArnException("Invalid task number");
        }
        return taskList.remove(index);
    }

    /**
     * Returns a list of tasks whose description contains the given keyword.
     *
     * @param keyword the keyword to search for
     * @return a list of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> matchList = new ArrayList<>();
        for (Task task : taskList) {
            if (task.description.toLowerCase().contains(keyword.toLowerCase())) {
                matchList.add(task);
            }
        }
        return matchList;
    }

    public ArrayList<Task> sortByDate() {
        ArrayList<Task> sortList = new ArrayList<>();
        for (Task task: taskList) {
            if (task.getDate() != null) {
                sortList.add(task);
            }
        }

        sortList.sort(new DateComparator());
        return sortList;
    }

    public int size() {
        return taskList.size();
    }

}
