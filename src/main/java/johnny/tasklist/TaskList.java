package johnny.tasklist;

import java.util.ArrayList;

import johnny.tasks.Task;

/**
 * A class that wraps an ArrayList<Task>.
 * This class provides many operations to edit the nested ArrayList
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList instance using the ArrayList<Task> passed in
     * 
     * @param tasks ArrayList<Task> to be wrapped in the instance
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constucts a new TaskList instance with an empty ArrayList
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Gets a Task at the given index of the wrapped ArrayList
     * 
     * @param index The index to search at
     * @return The task at that index
     */
    public Task getTask(int index) {
        return this.tasks.get(index);
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public Task deleteTask(int index) {
        Task deleted = this.tasks.get(index);
        this.tasks.remove(index);
        return deleted;
    }

    public void markComplete(int index) {
        this.tasks.get(index).markComplete();
    }

    public void markIncomplete(int index) {
        this.tasks.get(index).markIncomplete();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Returns the string format of the task that is used to store in the saved file
     * 
     * @param index The index to search at
     * @return The stored string format
     */
    public String getStoredString(int index) {
        return this.tasks.get(index).getStoredString();
    }

    /**
     * Returns an ArrayList of tasks that have their name containing the given
     * substring
     * 
     * @param subString
     * @return ArrayList of tasks
     */
    public ArrayList<Task> findTasks(String subString) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task t : tasks) {
            if (t.contains(subString)) {
                result.add(t);
            }
        }

        return result;
    }

    /**
     * Returns a String of tasks that have their name containing the given substring
     * 
     * @param subString
     * @return String describing all matching tasks
     */
    public String findTasksToString(String subString) {
        ArrayList<Task> matching = this.findTasks(subString);
        TaskList temp = new TaskList(matching);
        return temp.toString();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        if (this.tasks.isEmpty()) {
            return "No tasks in list";
        }

        for (int i = 0; i < tasks.size(); i++) {
            int num = i + 1;
            String str = String.format(". %s", tasks.get(i).toString());
            result.append(num);
            result.append(str);
            if (i < tasks.size() - 1) {
                result.append("\n");
            }
        }

        return result.toString();
    }
}
