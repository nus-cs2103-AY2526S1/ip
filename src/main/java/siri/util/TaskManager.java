package siri.util;

import java.util.ArrayList;
import java.util.List;

import siri.exception.SiriException;
import siri.task.Task;



/**
 * Manages the in-memory list of tasks.
 * Provides operations to add, remove, retrieve, and update tasks.
 */
public class TaskManager {
    private List<Task> list;
    private int count;

    /**
     * Construct an empty TaskManager
     */
    public TaskManager() {
        list = new ArrayList<>();
        count = 0;
    }

    /**
     * Find tasks with the given description
     * @param description description of the task
     * @return A list of matching tasks
     */
    public List<Task> findTask(String description) {
        return list.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(description.toLowerCase()))
                .toList();
    }

    /**
     * Gets task list
     * @return task list
     */
    public List<Task> getTasks() {
        return list;
    }

    /**
     * Gets total number of tasks
     * @return total number of tasks
     */
    public int getCount() {
        return count;
    }

    /**
     * add one task in the list and increase the count by one
     * @param task task added
     */
    public void addTask(Task task) {
        list.add(count, task);
        count++;
    }

    /**
     * update the status of one task in the list
     * @param index the index of the task in the list
     * @param status the new status of the list
     */
    public void updateTask(int index, Boolean status) {
        list.get(index).setDone(status);
    }

    /**
     * delete a task from the list
     * @param index the index of the task
     */
    public void deleteTask(int index) {
        assert index >= 0 && index < count : "Index out of bounds";
        list.remove(index);
        count--;
    }

    /**
     * Remove the most recent task in the list
     * @throws SiriException throws an exception when the list is empty
     */
    public void undo() throws SiriException {
        if (count == 0) {
            throw new SiriException("There is no task in the list");
        }
        list.remove(count - 1);
        count--;
    }

    /**
     * Undo the task with the index
     * @param index index of the task in the task list
     * @throws SiriException throws an exception when the index is out of bound
     */
    public void undoTask(int index) throws SiriException {
        if (index <= 0 || index > count) {
            throw new SiriException("invalid index");
        } else {
            list.remove(index - 1);
            count--;
        }
    }


}
