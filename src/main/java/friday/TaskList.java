package friday;

import java.util.ArrayList;
import java.util.List;

import friday.task.Task;

public class TaskList {
    private final List<Task> tasks;

    public TaskList() {
        this.tasks = Storage.readFile();
    }

    public TaskList(List<Task> t) {
        this.tasks = t;
    }

    /**
     * Returns the number of tasks in the list
     * @return Integer count
     */
    public Integer count() {
        return this.tasks.size();
    }

    /**
     * Returns the task at the given index
     * @param index
     * @return
     */
    public Task get(int index) {
        return this.tasks.get(index);
    }

    /**
     * Adds a task to the list
     * @param task
     */
    public void add(Task task) {
        this.tasks.add(task);
    }

    /**
     * Deletes the task at the given index and returns it
     * @param index
     * @return
     */
    public Task delete(int index) {
        return this.tasks.remove(index);
    }

    /**
     * Saves the current list of tasks to the file
     */
    public void save() {
        Storage.saveToFile(this.tasks);
    }

    /**
     * Finds tasks that match the given keyword in their description
     * @param keyword The keyword to search for (case-insensitive)
     * @return A new TaskList containing matching tasks
     */
    public TaskList find(String keyword) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        if (keyword == null || keyword.trim().isEmpty()) {
            return new TaskList(matchedTasks); // Return empty list for invalid keyword
        }
        
        String searchTerm = keyword.toLowerCase().trim();
        for (Task task : this.tasks) {
            // Search in both description and full description (includes dates/times)
            if (task.getDesc().toLowerCase().contains(searchTerm) ||
                task.getFullDesc().toLowerCase().contains(searchTerm)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

    /**
     * Finds tasks by their completion status
     * @param isDone true to find completed tasks, false to find incomplete tasks
     * @return A new TaskList containing tasks with the specified completion status
     */
    public TaskList findByStatus(boolean isDone) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        for (Task task : this.tasks) {
            if (task.isDone() == isDone) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

    /**
     * Finds tasks by their type
     * @param taskType The type of task to find ("[T]", "[D]", "[E]")
     * @return A new TaskList containing tasks of the specified type
     */
    public TaskList findByType(String taskType) {
        ArrayList<Task> matchedTasks = new ArrayList<>();
        if (taskType == null) {
            return new TaskList(matchedTasks);
        }
        
        for (Task task : this.tasks) {
            if (task.getTypeOfTask().equals(taskType)) {
                matchedTasks.add(task);
            }
        }
        return new TaskList(matchedTasks);
    }

}
