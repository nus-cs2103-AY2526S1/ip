package chatbot.io;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import chatbot.exception.HarryException;
import chatbot.task.Task;
/**
 * Represents a list of tasks managed by the Harry chatbot.
 * <p>
 * The {@code TaskList} class provides methods to add, remove,
 * update, snooze, and filter tasks, while automatically persisting
 * changes to disk through the {@link Storage} component.
 */
public class TaskList {
    private static final String FILE_PATH = "data/tasks.txt";
    private static final int MAXIMUM_CAPACITY = 100;

    protected ArrayList<Task> data;
    protected Storage loader;
    /**
     * Constructs a {@code TaskList} by loading tasks from storage.
     */
    public TaskList() {
        loader = new Storage(FILE_PATH, MAXIMUM_CAPACITY);
        data = loader.loadData();
    }

    /**
     * Marks the task at the specified index as completed.
     *
     * @param index the index of the task to complete
     */
    public void complete(int index) {
        data.get(index).complete();
        saveData();
    }

    /**
     * Marks the task at the specified index as uncompleted.
     *
     * @param index the index of the task to uncomplete
     */
    public void uncomplete(int index) {
        data.get(index).uncomplete();
        saveData();
    }

    /**
     * Snoozes the task, increasing deadline by 1 day
     *
     * @param index the index of the task to snooze
     */
    public void snooze(int index) {
        data.get(index).snooze();
        saveData();
    }

    /**
     * Returns the string representation of the task at the specified index.
     *
     * @param index the index of the task
     * @return task.toString()
     */
    public String print(int index) {
        return data.get(index).toString();
    }

    /**
     * Returns the string representation of the last task in the list.
     *
     * @return the string representation of the last task
     */
    public String printLast() {
        return data.get(data.size() - 1).toString();
    }

    /**
     * Removes the task at the specified index.
     *
     * @param index the index of the task to remove
     */
    public void remove(int index) {
        data.remove(index);
        saveData();
    }

    /**
     * Returns the number of tasks currently in the list.
     *
     * @return the size of the task list
     */
    public int getSize() {
        return data.size();
    }

    /**
     * Adds a task to the list and saves the updated data.
     * If the list reaches 100 tasks after adding, throws a HarryException.
     *
     * @param t the task to be added
     * @throws HarryException if the task list is full (size reaches 100)
     */
    public void add(Task t) {
        data.add(t);
        saveData();
        if (data.size() == 100) {
            throw new HarryException("I'm Full");
        }
    }

    /**
     * Filters the current Task list with a predicate and returns the filtered list
     *
     * @param predicate the task to be added
     * @return ArrayList containing filtered tasks
     */
    public ArrayList<Task> filter(Predicate<? super Task> predicate) {
        return data.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Saves the current list of tasks using the loader.
     */
    private void saveData() {
        loader.saveData(data);
    }

}
