package LeeKuanYew.Task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        this.tasks.add(task);
    }

    public Task get(int index) {
        return this.tasks.get(index);
    }

    public void remove(int index) {
        this.tasks.remove(index);
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Searches for tasks that contain the given keyword in their string representation.
     *
     * @param keyword the keyword to search for
     * @return a TaskList containing all matching tasks
     */
    public TaskList find(String keyword) {
        TaskList results = new TaskList();

        for (Task task : tasks) {
            if (task.toString().contains(keyword)) {
                results.add(task);
            }
        }
        return results;
    }

}
