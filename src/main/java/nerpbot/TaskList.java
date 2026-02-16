package nerpbot;

import nerpbot.task.Task;

import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;
    private final Storage storage;

    /**
     * Constructs an empty TaskList with the given storage.
     *
     * @param storage The storage handler for saving/loading tasks.
     */
    public TaskList(Storage storage) {
        this.tasks = new ArrayList<>();
        this.storage = storage;
    }

    /**
     * Constructs a TaskList with the given tasks and storage.
     *
     * @param tasks   The initial list of tasks.
     * @param storage The storage handler for saving/loading tasks.
     */
    public TaskList(ArrayList<Task> tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     * @return Confirmation message of the added task.
     * @throws IOException If saving to storage fails.
     */
    public String addTask(Task task) throws IOException {
        tasks.add(task);
        storage.save(tasks);
        return "Got it. I've added this task:\n  " + task + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Deletes a task from the task list by its index.
     *
     * @param idx The index of the task to be deleted (0-based).
     * @return Confirmation message of the deleted task.
     * @throws IOException If saving to storage fails.
     */
    public String deleteTask(int idx) throws IOException {
        if (idx < 0 || idx >= tasks.size()) {
            return "That task number doesn't exist.";
        }
        Task removed = tasks.remove(idx);
        storage.save(tasks);
        return "Noted. I've removed this task:\n  " + removed + "\nNow you have " + tasks.size() + " tasks in the list.";
    }

    /**
     * Marks a task as done by its index.
     *
     * @param idx The index of the task to be marked (0-based).
     * @return Confirmation message of the marked task.
     * @throws IOException If saving to storage fails.
     */
    public String markTask(int idx) throws IOException {
        assert idx >= 0 && idx < tasks.size() : "Index out of bounds";
        if (idx < 0 || idx >= tasks.size()) {
            return "That task number doesn't exist.";
        }
        Task task = tasks.get(idx);
        task.markAsDone();
        storage.save(tasks);
        return "Nice! I've marked this task as done:\n  " + task;
    }

    public String unmarkTask(int idx) throws IOException {
        if (idx < 0 || idx >= tasks.size()) {
            return "That task number doesn't exist.";
        }
        Task task = tasks.get(idx);
        task.unmarkAsDone();
        storage.save(tasks);
        return "OK, I've marked this task as not done yet:\n  " + task;
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Your task list is empty.";
        }
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append((i + 1)).append(".").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    public String findTasks(String keyword) {
        ArrayList<Task> matches = new ArrayList<>();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                matches.add(task);
            }
        }
        if (matches.isEmpty()) {
            return "No matching tasks found!";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < matches.size(); i++) {
            sb.append((i + 1)).append(".").append(matches.get(i)).append("\n");
        }
        return sb.toString().stripTrailing();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }
}
