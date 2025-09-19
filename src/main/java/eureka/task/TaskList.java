package eureka.task;

import java.util.ArrayList;

/**
 * Manages a collection of tasks with operations for adding, removing,
 * marking, and searching tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }
    public TaskList() {
        this.taskList = new ArrayList<>();
    }
    public Task getTask(int i) {
        return taskList.get(i);
    }
    public void markTask(int i) {
        taskList.get(i).setIsDone(true);
    }
    public void unmarkTask(int i) {
        taskList.get(i).setIsDone(false);
    }
    public void deleteTask(int i) {
        taskList.remove(i);
    }
    public void addTask(Task task) {
        taskList.add(task);
    }
    public int getCount() {
        return taskList.size();
    }

    /**
     * Searches for tasks containing the specified keyword in their description.
     *
     * @param keyword the search term to match against task descriptions
     * @return a list of tasks that contain the keyword
     */
    public ArrayList<Task> find(String keyword) {
        ArrayList<Task> results = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            Task t = getTask(i);
            if (t.contains(keyword)) {
                results.add(t);
            }
        }
        return results;
    }
}
