package alune.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private int total;
    private final List<Task> list;

    /**
     * This class contains a list of tasks with functions such as adding, removing
     * and filtering tasks.
     * 
     * @author nghnaomi
     */
    public TaskList(List<Task> database) {
        this.total = database.size();
        this.list = new ArrayList<>(database);
    }

    /**
     * Adds the given task.
     * 
     * @param task Task to add to the list.
     */
    public void addTask(Task task) {
        list.add(task);
        total++;
    }

    /**
     * Removes the specified task.
     * 
     * @param index Index of task to remove, starting from 0.
     * @return Removed task.
     */
    public Task removeTask(int index) {
        Task task = list.remove(index);
        total--;
        return task;
    }

    /**
     * Returns the list of tasks in the TaskList.
     * 
     * @return List as a List<Task>.
     */
    public List<Task> getTasks() {
        return list;
    }

    /**
     * Returns the size of the TaskList.
     * 
     * @return Size of TaskList.
     */
    public int size() {
        return total;
    }

    /**
     * Retrieves and returns a task from the list by index.
     * 
     * @param index Index of task to retrieve, starting from 0.
     * @return Retrieved task.
     */
    public Task getTask(int index) {
        if (index < 0 || index >= list.size()) {
            return null;
        }
        return list.get(index);
    }

    /**
     * Returns list of tasks as a properly formatted string.
     * 
     * @param sb StringBuilder of message to add onto.
     * @return String with added list of tasks.
     */
    public String printTasks(StringBuilder sb) {
        int num = 1;
        for (Task task : list) {
            sb.append(num + ". " + task + "\n");
            num++;
        }
        return sb.toString();
    }

    /**
     * Marks a task as done.
     * 
     * @param index Index of task to mark as done, starting from 0.
     */
    public void mark(int index) {
        list.get(index).markDone();
    }

    /**
     * Unmarks a task as done.
     * 
     * @param index Index of task to unmark as done, starting from 0.
     */
    public void unmark(int index) {
        list.get(index).markUndone();
    }

    /**
     * Checks if the TaskList is empty.
     * 
     * @return True if the TaskList is empty and false if it is not.
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    /**
     * Filters the TaskList by matching a given key and returns the filtered
     * TaskList.
     * 
     * @param key String to filter the TaskList swith.
     * @return Filtered TaskList.
     */
    public TaskList searchList(String key) {
        List<Task> filtered = this.list.stream().filter(t -> t.getName().contains(key))
                .collect(Collectors.toList());
        return new TaskList(filtered);
    }

    /**
     * Removes all tasks that are marked as done from the TaskList.
     * 
     * @return Number of tasks removed.
     */
    public int removeDoneTasks() {
        int removedCount = 0;
        for (int i = list.size() - 1; i >= 0; i--) {
            if (list.get(i).getStatus()) {
                list.remove(i);
                total--;
                removedCount++;
            }
        }
        return removedCount;
    }
}
