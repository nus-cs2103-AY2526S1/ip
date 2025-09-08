package buddy;

import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Manages a list of tasks with operations for adding, deleting, and modifying tasks.
 * Provides functionality to mark tasks as done/undone and retrieve tasks by index.
 */
public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    /**
     * Adds a task to the task list.
     * 
     * @param task the task to be added
     */
    public void addTask(Task task) {
        assert task != null : "Task should not be null when adding to task list";
        tasks.add(task);
    }
    
    public void deleteTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task number is out of range.");
        }
        assert !tasks.isEmpty() : "Task list should not be empty when deleting";
        tasks.remove(index);
    }
    
    public Task getTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task number is out of range.");
        }
        return tasks.get(index);
    }
    
    public void markTask(int index) throws BuddyException {
        getTask(index).markAsDone();
    }
    
    public void unmarkTask(int index) throws BuddyException {
        getTask(index).markAsNotDone();
    }
    
    public int size() {
        return tasks.size();
    }
    
    public Task[] getTaskArray() {
        return tasks.toArray(new Task[tasks.size()]);
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    public Task[] findTasks(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(lowerKeyword))
                .toArray(Task[]::new);
    }
    
    public long getCompletedTaskCount() {
        return tasks.stream()
                .filter(Task::isDone)
                .count();
    }
    
    public Task[] getCompletedTasks() {
        return tasks.stream()
                .filter(Task::isDone)
                .toArray(Task[]::new);
    }
}