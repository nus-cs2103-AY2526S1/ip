package state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import tasks.Task;

/**
 * Class that represents the tasks in the Pepe application.
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a TaskList with the provided list of tasks.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Returns the size of the TaskList
     * @return how many items are in the TaskList
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Predicate method to check if TaskList is empty.
     * @return true if TaskList is empty
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Adds the task to the TaskList.
     * @param task to be added to TaskList
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Gets the task at the specified index of the TaskList
     * @param index to get task at
     * @return the task at the specified index
     */
    public Task get(int index) {
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index of the TaskList
     * @param index to remove task at
     */
    public void remove(int index) {
        tasks.remove(index);
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }

    /**
     * Filters the TaskList for tasks that match the provided match phrase.
     * @param matchPhrase the phrase that task descriptions should match on
     * @return a TaskList with only tasks that match the match phrase
     */
    public TaskList filter(String matchPhrase) {
        return new TaskList(tasks.stream()
                .filter(task -> task.matchesPhrase(matchPhrase)).toList());
    }

    /**
     * Sort the TaskList according to when the tasks are due by. Todos are always at the front.
     */
    public void sort() {
        Collections.sort(tasks);
    }
}
