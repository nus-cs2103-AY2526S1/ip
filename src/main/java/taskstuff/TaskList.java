package taskstuff;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    
    public TaskList(ArrayList<Task> a) {
        this.taskList = a;
    }
    
    public void clear() {
        this.taskList.clear();
    }
    
    /**
     * Adds a task to the end of the list.
     *
     * @param task the task to add.
     */
    public void addTask(Task task) {
        taskList.add(task);
    }
    
    /**
     * Removes and returns the task at the specified 0-based index.
     *
     * @param i index of the task to remove.
     * @return the removed task.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task deleteTask(int i) {
        return taskList.remove(i);
    }
    
    /**
     * Returns the task at the specified 0-based index.
     *
     * @param i index of the task to retrieve.
     * @return the task at the given index.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Task get(int i) {
        return taskList.get(i);
    }
    
    /**
     * Returns the number of tasks currently stored.
     *
     * @return task count.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * Returns all tasks whose descriptions contain the given keyword (case-insensitive).
     *
     * @param keyword the search keyword; leading/trailing spaces are ignored.
     * @return a new list containing matching tasks in their original order.
     * @throws IllegalArgumentException if {@code keyword} is null or blank.
     */
    public ArrayList<Task> findContaining(String keyword) {
        ArrayList<Task> out = new ArrayList<>();
        // getDescription(), use that instead of toString()
        for (Task t : taskList) {
            String desc = t.description;
            if (desc != null && desc.toLowerCase().contains(keyword)) {
                out.add(t);
            }
        }
        return out;
    }
}
