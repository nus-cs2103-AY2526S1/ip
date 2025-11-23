package Mithrandir;

import java.util.ArrayList;

import Mithrandir.task.Task;

public class TaskList {
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task the task to be added to the list
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves a task from the task list at the specified index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks a task as done at the specified index.
     *
     * @param index the index of the task to mark as done
     */
    public void Mark(int index) {
        tasks.get(index).markDone();
    }

    /**
     * Marks a task as undone at the specified index.
     *
     * @param index the index of the task to mark as undone
     */
    public void Unmark(int index) {
        tasks.get(index).markUndone();
    }

    /**
     * Removes and returns a task from the task list at the specified index.
     *
     * @param index the index of the task to remove
     * @return the task that was removed from the list
     */
    public Task DeleteTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Generates a string representation of all tasks in the list formatted for file storage.
     * Each task is represented on a separate line using its toFileString() method.
     *
     * @return a string containing all tasks formatted for file storage
     */
    public String generateFileStrings() {
        StringBuilder str = new StringBuilder();
        for (Task task : tasks) {
            str.append(task.toFileString()).append(System.lineSeparator());
        }
        return str.toString();
    }

    public TaskList findTasks(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    public int getSize() {
        return this.tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int i = 1;
        for (Task task : tasks) {
            str.append(String.format("%d. ", i)).append(task.toString()).append("\n");
            i += 1;
        }
        return str.toString().strip();
    }

}
