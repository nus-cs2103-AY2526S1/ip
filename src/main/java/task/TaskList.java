package task;
import java.time.LocalDate;
import java.util.ArrayList;
import java.lang.StringBuilder;

public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Uses the provided list as the backing store for this {@code TaskList}.
     * The list is not defensively copied; mutations are reflected both ways.
     *
     * @param tasks existing list to back this {@code TaskList}
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public Task get(int idx) {
        return this.tasks.get(idx);
    }

    public int size() {
        return tasks.size();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Appends a task to the end of the list.
     *
     * @param task task to add
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Removes the task at the specified zero-based index.
     * Subsequent elements shift left (decrement their indices by one).
     *
     * @param idx zero-based index of the task to remove
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public void delete(int idx) {
        tasks.remove(idx);
    }

    public void sortTasks() {
        tasks.sort((t1, t2) -> {
            LocalDate d1 = t1.getDate();
            LocalDate d2 = t2.getDate();
            return d1.compareTo(d2);
        });
    }


    /**
     * Returns a human-friendly multi-line summary of tasks,
     * or a short message if the list is empty.
     *
     * @return string summary intended for display
     */
    @Override
    public String toString() {
        if (tasks.isEmpty()) {
            return "no tasks yet. get to work";
        }
        StringBuilder sb = new StringBuilder("Your Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n")
                    .append(i + 1)
                    .append(". ")
                    .append(tasks.get(i).toString());
        }
        return sb.toString();
    }
}
