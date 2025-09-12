package application;

import java.util.ArrayList;
import java.util.List;

import tasks.Task;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public TaskList(List<Task> initial) {
        this.tasks = new ArrayList<>(initial);
    }

    public int size() {
        return tasks.size();
    }

    public Task get(int idx) {
        return tasks.get(idx);
    }

    public void add(Task t) {
        tasks.add(t);
    }

    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    public ArrayList<Task> retreive() {
        return tasks;
    }
    
    /**
     * Checks if the task list contains a duplicate of the given task.
     * Uses the equals() method to compare tasks based on their content.
     * 
     * @param task The task to check for duplicates
     * @return true if a duplicate exists, false otherwise
     */
    public boolean containsDuplicate(Task task) {
        for (Task existingTask : tasks) {
            if (existingTask.equals(task)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Finds the index of the first duplicate task.
     * 
     * @param task The task to find duplicates for
     * @return The index of the duplicate (1-based for user display), or -1 if no duplicate exists
     */
    public int findDuplicateIndex(Task task) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).equals(task)) {
                return i + 1; 
            }
        }
        return -1;
    }
    
    /**
     * Finds all tasks whose descriptions contain the specified keyword.
     * Returns a list of IndexedTask objects containing both the task and its original index.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return ArrayList of IndexedTask objects containing matching tasks and their indices.
     */
    public ArrayList<IndexedTask> findTasks(String keyword) {
        ArrayList<IndexedTask> matchingTasks = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(new IndexedTask(tasks.get(i), i + 1)); // 1-based index for display
            }
        }
        return matchingTasks;
    }
    
    /**
     * Helper class to store a task along with its original index for search results.
     */
    public static class IndexedTask {
        private final Task task;
        private final int index;
        
        public IndexedTask(Task task, int index) {
            this.task = task;
            this.index = index;
        }
        
        public Task getTask() {
            return task;
        }
        
        public int getIndex() {
            return index;
        }
    }
}
