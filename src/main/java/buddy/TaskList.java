package buddy;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    
    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    
    public void addTask(Task task) {
        tasks.add(task);
    }
    
    public void deleteTask(int index) throws BuddyException {
        if (index < 0 || index >= tasks.size()) {
            throw new BuddyException("Task number is out of range.");
        }
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
        return tasks.toArray(new Task[0]);
    }
    
    public ArrayList<Task> getTasks() {
        return tasks;
    }
    
    public Task[] findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks.toArray(new Task[0]);
    }
}