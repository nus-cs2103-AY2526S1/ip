package chatowo.task;

import java.util.ArrayList;

/**
 * Represents a list of tasks that can be managed by the chat bot.
 * Supports operations like adding, removing, and marking tasks.
 */
public class TaskList extends ArrayList<Task> {
    public void done(int index) {
        this.get(index).setDone();
    }

    public void undone(int index) {
        this.get(index).setNotDone();
    }

    /**
     * Adds a new task to the list.
     *
     * @param task Task to be added
     * @throws IllegalArgumentException if task is null
     */
    @Override
    public boolean add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException("Task is null");
        } else {
            return super.add(task);
        }
    }

    /**
     * Returns a TaskList containing tasks that match the specified query.
     *
     * @param phrase Phrase to find in task
     */
    public TaskList find(String phrase) {
        TaskList matchingTasks = new TaskList();
        this.forEach((task) -> {
            if (task.getName().contains(phrase)) {
                matchingTasks.add(task);
            }
        });
        return matchingTasks;
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.size(); i++) {
            String index = String.valueOf(i + 1);
            str += ("\n    " + index + "." + this.get(i).toString());
        }
        return str;
    }
}
