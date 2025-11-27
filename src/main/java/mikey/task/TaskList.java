package mikey.task;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Initializes a TaskList instance
     * @param initial List of tasks to be instantiated
     */
    public TaskList(List<Task> initial) {
        if (initial != null) {
            tasks.addAll(initial);
        }
    }

    public List<Task> getList() {
        return tasks;
    }

    /**
     * Adds a task to the task list
     *
     * @param task Task to be added
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Marks a task as complete
     *
     * @param index Index of task to be marked
     */
    public Task markTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.markDone();
            return currTask;
        } else {
            return null;
        }
    }

    /**
     * Marks a task as incomplete
     *
     * @param index Index of a task to be unmarked
     */
    public Task unmarkTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.markUndone();
            return currTask;
        } else {
            return null;
        }
    }

    /**
     * Deletes a task
     *
     * @param index Index of task to be deleted
     */
    public Task deleteTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            tasks.remove(index);
            return currTask;
        } else {
            return null;
        }
    }
  
    /**
     * Finds the tasks that match the keyword
     * @param keyword Keyword used for searching
     * @return List of tasks that contain the keyword
    */
    public List<Task> findTasks(String keyword) {
        return tasks.stream().filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * Tags the task in the tasklist
     * @param index Index of task to be tagged
     * @param label Label of tag
     * @return
     */
    public Task tagTask(int index, String label) {
        if (index >= 0 && index < tasks.size()) {
            Task currTask = tasks.get(index);
            currTask.setTag(label);
            return currTask;
        } else {
            return null;
        }
    }

    /**
     * Returns the size of the task list
     * @return Size of task list
     */
    public int size() {
        return tasks.size();
    }
}
