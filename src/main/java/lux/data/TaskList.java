package lux.data;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Container for an ordered list of {@link Task} objects.
 *
 * <p>This class provides simple list operations used throughout the
 * application, including adding tasks and searching for tasks that contain
 * a substring. The list preserves insertion order and is serializable for
 * persistence via {@link lux.storage.Storage}.
 */
public class TaskList implements Serializable {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Add a task to the end of the list.
     *
     * @param task task to add (must not be null)
     */
    public void addTasks(Task task) {
        tasks.add(task);
    }

    /**
     * Returns a new {@link TaskList} containing tasks whose descriptions
     * include the given search term.
     *
     * @param searchTerm substring to match inside task descriptions
     * @return a TaskList with matching tasks (may be empty)
     */
    public TaskList find(String searchTerm) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.contains(searchTerm)) {
                foundTasks.addTasks(task);
            }
        }
        return foundTasks;
    }

    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "  (No tasks yet!)";
        }

        String outString = "";
        for (int i = 0; i < tasks.size(); i++) {
            String item = String.format("  %d. %s\n", i, tasks.get(i));
            outString += item;
        }
        return outString;
    }
}
