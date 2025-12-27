package ip.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * Represents the List of tasks
 */
public class TaskList implements Iterable<Task> {

    private final ArrayList<Task> list;

    public TaskList() {
        list = new ArrayList<>();
    }

    /**
     * Adds a task to the TaskList
     * @param task Task to be added
     */
    public void addTask(Task task) {
        list.add(task);
    }

    /**
     * Returns the size of the TaskList
     * @return number of tasks in TaskList
     */
    public int size() {
        return list.size();
    }

    /**
     * Removes a task from the TaskList
     * @param index Index of task to be removed
     */
    public void remove(int index) {
        list.remove(index);
    }

    /**
     * Returns a task in the TaskList based on
     * its index
     * @param index Index of task
     * @return Task with that index
     */
    public Task get(int index) {
        return list.get(index);
    }

    /**
     * Checks if TaskList is empty
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }


    /**
     * Checks if TaskList contains Task
     * @param t Task to check
     * @return true if contains task, false otherwise
     */
    public boolean contains(Task t) {
        return list.contains(t);
    }

    /**
     * @inheritDoc
     */
    @Override
    public void forEach(Consumer<? super Task> action) {
        list.forEach(action);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Iterator<Task> iterator() {
        return list.iterator();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Spliterator<Task> spliterator() {
        return list.spliterator();
    }
}
