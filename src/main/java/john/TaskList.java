package john;

import java.util.ArrayList;

import john.tasks.Task;

/**
 * List of tasks managed by the chatbot.
 */
public class TaskList {
    private ArrayList<Task> list;
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        list = new ArrayList<>();
    }
    /**
     * Constructor for TaskList that creates a deep copy of another TaskList.
     */
    public TaskList(TaskList other) {
        list = new ArrayList<>();
        for (Task t : other.list) {
            this.list.add(t.copy()); // copy each task
        }
    }

    /**
     * Adds a task to the list.
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null";
        list.add(task);
    }
    /**
     * Removes a task from the list.
     */
    public Task deleteTask(int taskIndex) {
        Task task = list.get(taskIndex - 1);
        list.remove(taskIndex - 1);
        return task;
    }
    /**
     * Retrieves a task from the list.
     */
    public Task get(int index) {
        return this.list.get(index - 1);
    }
    /**
     * Gives the current number of tasks in the list.
     */
    public int size() {
        return this.list.size();
    }
    /**
     * Returns an ArrayList of the indices of all tasks whose name contains the keyword.
     */
    public ArrayList<Integer> search(String keyword) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        for (int i = 1; i <= this.size(); i++) {
            if (this.get(i).nameContains(keyword)) {
                res.add(i);
            }
        }
        return res;
    }
}
