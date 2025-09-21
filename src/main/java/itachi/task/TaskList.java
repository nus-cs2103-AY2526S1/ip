package itachi.task;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import itachi.Ui;

/**
 * Represents a collection of {@link Task} objects.
 * Provides operations to manage tasks such as adding,
 * removing, retrieving, and iterating over them.
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public void remove(int index) {
        tasks.remove(index);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int size() {
        return tasks.size();
    }

    /**
     *  Finds the tasks by matching the
     *  given keyword with the tasks stored in the {@link #tasks}.
     * @param keyword the keyword to search for in task descriptions
     * @return a new {@code TaskList} containing all matching tasks
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matched = tasks.stream()
                                  .filter(task -> task.getDescription().contains(keyword))
                                  .collect(Collectors.toCollection(ArrayList::new));
        return new TaskList(matched);
    }

    /**
     * if the {@link #tasks} is empty, then it will return true
     * else, it returns false
     * @return a boolean representing whether the {@link #tasks} is empty or not
     */
    public boolean isEmpty() {
        if (tasks.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * This prints out all the tasks in the {@link #tasks} in the given
     * format that they are saved as.
     * @param ui the {@link Ui} instance used to display the formatted task list
     */
    public void printAllTasks(Ui ui) {
        IntStream.range(0, tasks.size())
                 .forEach(i -> ui.showMessage((i + 1) + ". " + tasks.get(i).toSaveFormat()));
    }
}


