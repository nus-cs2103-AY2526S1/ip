package talkgpt.tasklist;

import java.util.ArrayList;

import talkgpt.task.Task;

/**
 * Represents a list of Task objects in the TalkGPT application.
 * Extends ArrayList to provide additional methods for marking, unmarking,
 * deleting, adding, and searching tasks.
 */
public class TaskList extends ArrayList<Task> {

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {}

    /**
     * Marks the task at the given index as done.
     *
     * @param index The index of the task to be marked.
     * @return The marked task.
     */
    public Task markTask(int index) {
        Task task = super.get(index);
        task.mark();

        return task;
    }

    /**
     * Unmarks the task at the given index (marks as not done).
     *
     * @param index The index of the task to be unmarked.
     * @return The unmarked task.
     */
    public Task unmarkTask(int index) {
        Task task = super.get(index);
        task.unmark();

        return task;
    }

    /**
     * Deletes the task at the given index from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The deleted task.
     */
    public Task deleteTask(int index) {
        Task task = super.get(index);
        super.remove(index);

        return task;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        super.add(task);
    }

    /**
     * Returns a string representation of the task list,
     * with each task on a new line and numbered.
     *
     * @return The string representation of the task list.
     */
    @Override
    public String toString() {
        StringBuilder msg = new StringBuilder();

        for (int i = 0; i < super.size(); i++) {
            String adding = String.format("%d. %s\n", i + 1, super.get(i).toString());
            msg.append(adding);
        }

        return msg.toString();
    }

    /**
     * Finds and returns a TaskList of tasks whose descriptions contain the given keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A TaskList containing matching tasks.
     */
    public TaskList findTask(String keyword) {
        TaskList results = new TaskList();

        for (int i = 0; i < this.size(); i++) {
            Task task = super.get(i);
            String description = task.getDescription();

            if (description.contains(keyword)) {
                results.add(task);
            }
        }

        return results;
    }

    /**
     * Finds and returns a TaskList of tasks that have the specified tag.
     *
     * @param tag The tag to search for in tasks.
     * @return A TaskList containing tasks with the specified tag.
     */
    public TaskList getTasksByTag(String tag) {
        TaskList results = new TaskList();

        for (int i = 0; i < this.size(); i++) {
            Task task = super.get(i);
            String taskTag = task.getTag();

            if (taskTag.equals(tag)) {
                results.add(task);
            }
        }

        return results;
    }
}
