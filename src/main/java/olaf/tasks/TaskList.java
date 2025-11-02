package olaf.tasks;

import java.util.ArrayList;

import olaf.OlafException;
import olaf.Storage;

/**
 * Manages the list of tasks, with command methods returning strings describing updates.
 */
public class TaskList {

    private Storage storage;
    private ArrayList<Task> tasks = new ArrayList<>();
    private int count;

    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
        this.count = tasks.size();
    }

    public String addTask(Task task) throws OlafException{
        for (Task existing : tasks) {
            if (existing.equals(task)) {
                throw new OlafException("This task already exists in your list!");
            }
        }
        tasks.add(task);
        count++;
        storage.save(tasks);
        assert count == tasks.size() : "TaskList count mismatches tasks size";
        return  "Understood. I have added this task for you:\n  " + task + "\n" +
                "You currently have a total of " + this.count + " tasks in your list.\n";
    }

    public String listTasks() {
        if (count == 0) {
            return "You have no tasks in your list.\n";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks you have in your list:\n");
        for (int i = 0; i < count; i++) {
            sb.append(" ").append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }

    public String markTask(int pos) {
        assert pos > 0 && pos <= count : "Index out of bounds for task list";
        Task currTask = tasks.get(pos - 1);
        currTask.markDone();
        storage.save(tasks);
        return "Nicely done! I have marked this task as done:\n  " + currTask + "\n";
    }

    public String unmarkTask(int pos) {
        assert pos > 0 && pos <= count : "Index out of bounds for task list";
        Task currTask = tasks.get(pos - 1);
        currTask.markUndone();
        storage.save(tasks);
        return "Alright, I have marked this task as undone:\n  " + currTask + "\n";
    }

    public String deleteTask(int pos) {
        assert pos > 0 && pos <= count : "Index out of bounds for task list";
        Task deletedTask = tasks.remove(pos - 1);
        count--;
        storage.save(tasks);
        assert count == tasks.size() : "TaskList count mismatches tasks size";
        return "Noted. I have removed this task for you:\n  " + deletedTask + "\n" +
                "You currently have a total of " + this.count + " tasks in your list.\n";
    }

    public String findTask(String keyword) {
        StringBuilder sb = new StringBuilder();
        int foundCount = 0;
        sb.append("Here are the matching tasks in your list:\n");
        for (int i = 0; i < count; i++) {
            Task task = tasks.get(i);
            if (task.getDescription().toLowerCase().contains(keyword.toLowerCase())) {
                foundCount++;
                sb.append(" ").append(foundCount).append(". ").append(task).append("\n");
            }
        }
        if (foundCount == 0) {
            sb.append("No matching tasks found.\n");
        }
        return sb.toString();
    }

    public int getCount() {
        return this.count;
    }
}
