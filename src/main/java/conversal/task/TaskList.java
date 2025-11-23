package conversal.task;

import java.util.ArrayList;

import conversal.exception.ConversalException;

/**
 * Represents a list of tasks in the Conversal chatbot.
 *
 * Provides operations to add, delete, find, mark, and unmark tasks.
 * Also includes operations to check the current size of the list and return the list itself.
 */
public class TaskList {

    private final ArrayList<Task> tasks;

    /**
     * Creates a TaskList object backed by the given ArrayList of tasks.
     *
     * @param tasks the list of tasks to initialise with
     */
    public TaskList(ArrayList<Task> tasks) {
        assert tasks != null : "tasks list must not be null";
        this.tasks = tasks;
        assert this.tasks.size() >= 0 : "size must be non-negative";
    }

    /**
     * Adds a new task to the task list.
     *
     * @param task the task to add
     */
    public void addTask(Task task) {
        assert task != null : "task to add must not be null";
        int prevSize = tasks.size();
        this.tasks.add(task);
        assert tasks.size() == prevSize + 1 : "size should increment by 1 after task is added";
        assert tasks.get(tasks.size() - 1) == task : "latest task should be task";
    }


    /**
     * Deletes and returns the task at the given index.
     *
     * @param index the index of the task to delete
     * @return the removed task
     * @throws ConversalException if the index is invalid
     */
    public Task deleteTask(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("To delete a task, enter: delete (task number)");
        }
        int prevSize = tasks.size();
        Task removed = this.tasks.remove(index);
        assert removed != null : "remove() should return (to be deleted) task";
        assert tasks.size() == prevSize - 1 : "size should decrement by 1 after task is deleted";
        return removed;
    }

    /**
     * Retrieves the task at the given index.
     *
     * @param index the index of the task to retrieve
     * @return the task at the given index
     * @throws ConversalException if the index is invalid
     */
    public Task get(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }
        Task t = this.tasks.get(index);
        assert t != null : "task in list cannot not be null";
        return t;
    }

    /**
     * Marks the task at the given index as complete.
     *
     * @param index the index of the task to mark as complete
     * @throws ConversalException if the index is invalid
     */
    public void markTaskComplete(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }
        this.tasks.get(index).markAsComplete();
        assert this.tasks.get(index).isDone() : "task should be marked complete";
    }

    /**
     * Marks the task at the given index as incomplete.
     *
     * @param index the index of the task to mark as incomplete
     * @throws ConversalException if the index is invalid
     */
    public void markTaskIncomplete(int index) throws ConversalException {
        if (index < 0 || index >= tasks.size()) {
            throw new ConversalException("Enter a valid task number between 1 and " + tasks.size());
        }
        this.tasks.get(index).markAsIncomplete();
        assert !this.tasks.get(index).isDone() : "task should be marked incomplete";
    }

    /**
     * Returns the list of tasks.
     *
     * @return the ArrayList of tasks
     */
    public ArrayList<Task> getList() {
        assert tasks != null : "tasks list cannot not be null";
        return this.tasks;
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the list
     */
    public int size() {
        int s = this.tasks.size();
        assert s >= 0 : "size must be non-negative";
        return s;
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword.
     *
     * @param keyword the keyword to search for (case-insensitive)
     * @return a list of matching tasks
     */
    public ArrayList<Task> find(String keyword) {
        assert keyword != null : "keyword cannot not be null";
        ArrayList<Task> result = new ArrayList<>();
        String k = keyword.toLowerCase();
        for (Task t : tasks) {
            assert t != null : "task in list cannot not be null";
            String desc = t.getDescription();
            if (desc.toLowerCase().contains(k)) {
                result.add(t);
            }
        }
        assert result != null : "result list cannot not be null";
        return result;
    }
}
