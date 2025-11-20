package tasklists;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import tasks.Deadline;
import tasks.Task;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list should not be null";
        this.tasks = tasks;
    }

    // Add a task to the list
    public void addTask(Task task) {
        assert task != null : "Task to add should not be null";
        tasks.add(task);
    }

    // Delete a task from the list
    public void deleteTask(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in deleteTask";
        tasks.remove(index);
    }

    // Get all tasks in the list
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    // Get task count
    public int getTaskCount() {
        return tasks.size();
    }

    // Mark a task as done
    public void markAsDone(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in markAsDone";
        tasks.get(index).markAsDone();
    }

    // Unmark a task as not done
    public void unmark(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in unmark";
        tasks.get(index).unmark();
    }

    public List<Task> find(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * Sorts only the Deadline tasks in place by their due date, replacing their positions in the list.
     * All other task types remain in their original positions.
     */
    public void sortDeadlinesChronologicallyInPlace() {
        // Collect indices and references to Deadline tasks
        List<Integer> deadlineIndices = new ArrayList<>();
        List<Deadline> deadlines = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            if (task instanceof Deadline) {
                deadlineIndices.add(i);
                deadlines.add((Deadline) task);
            }
        }
        // Sort the Deadline tasks by due date
        deadlines.sort(Comparator.comparing(Deadline::getDeadline));
        // Replace the original Deadline tasks in their positions with the sorted ones
        for (int i = 0; i < deadlineIndices.size(); i++) {
            tasks.set(deadlineIndices.get(i), deadlines.get(i));
        }
    }
}
