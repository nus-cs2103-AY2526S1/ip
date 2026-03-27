package fish.task;

import java.util.ArrayList;

import fish.FishException;

/**
 * Maintains an ordered collection of {@link Task} instances.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs a task list backed by the given tasks.
     *
     * @param tasks initial tasks to populate the list
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void add(Task t) throws FishException {
        if (tasks.contains(t)) {
            throw new FishException("This task already exists: " + t);
        }
        tasks.add(t);
    }

    public Task delete(int i) {
        assert i <= tasks.size() && i >= 0 : "Index out of range";
        return tasks.remove(i);
    }

    public Task get(int i) {
        assert i <= tasks.size() && i >= 0 : "Index out of range";
        return tasks.get(i);
    }

    public int size() {
        return tasks.size();
    }
}
