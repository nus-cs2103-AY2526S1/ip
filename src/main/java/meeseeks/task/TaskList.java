package meeseeks.task;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Creates a TaskList with the specified tasks.
     * 
     * @param tasks the initial list of tasks (cannot be null)
     * @throws AssertionError if tasks is null
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "Task list cannot be null";
        this.tasks = tasks;
    }

    /**
     * Adds a new task to the task list.
     * 
     * @param task the task to add (cannot be null)
     * @throws AssertionError if task is null
     */
    public void add(Task task) {
        assert task != null : "Task cannot be null";
        tasks.add(task);
    }

    /**
     * Removes and returns the task at the specified index.
     * 
     * @param index the index of the task to remove (0-based)
     * @return the removed task
     * @throws AssertionError if index is out of bounds
     */
    public Task delete(int index) {
        assert index >= 0 : "Index must be non-negative";
        assert index < tasks.size() : "Index must be within bounds of task list";
        return tasks.remove(index);
    }

    /**
     * Gets the task at the specified index without removing it.
     * 
     * @param index the index of the task to retrieve (0-based)
     * @return the task at the specified index
     * @throws AssertionError if index is out of bounds
     */
    public Task get(int index) {
        assert index >= 0 : "Index must be non-negative";
        assert index < tasks.size() : "Index must be within bounds of task list";
        return tasks.get(index);
    }

    /**
     * Gets the total number of tasks in the list.
     * 
     * @return the number of tasks
     */
    public int size() {
        return tasks.size();
    }
    
    /**
     * Checks if the task list is empty.
     * 
     * @return true if the list is empty, false otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets the underlying ArrayList of tasks.
     * 
     * @return the ArrayList containing all tasks
     */
    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Prints all tasks to the console with their index numbers.
     * This method is deprecated in favor of getTasksAsString() for better separation of concerns.
     */
    @Deprecated
    public void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
    }
    
    
    public String getTasksAsString() {
        if (tasks.isEmpty()) {
            return "Your task list is empty!";
        }
        
        StringBuilder result = new StringBuilder("Here are the tasks in your list:\n");
        IntStream.range(0, tasks.size())
                .mapToObj(i -> (i + 1) + "." + tasks.get(i))
                .forEach(task -> result.append(task).append("\n"));
        return result.toString().trim();
    }
    
    /**
     * Finds tasks that contain the given keyword in their name (case-insensitive).
     * Uses Java streams for efficient filtering.
     * 
     * @param keyword the keyword to search for
     * @return a list of tasks that match the keyword
     */
    public List<Task> findTasksByKeyword(String keyword) {
        String lowerKeyword = keyword.toLowerCase();
        return tasks.stream()
                .filter(task -> task.getName().toLowerCase().contains(lowerKeyword))
                .collect(Collectors.toList());
    }
    
    /**
     * Gets the count of completed tasks using streams.
     * 
     * @return the number of completed tasks
     */
    public long getCompletedTaskCount() {
        return tasks.stream()
                .filter(Task::isDone)
                .count();
    }
    
    /**
     * Gets the count of pending tasks using streams.
     * 
     * @return the number of pending tasks
     */
    public long getPendingTaskCount() {
        return tasks.stream()
                .filter(task -> !task.isDone())
                .count();
    }
}
