package baymax.task;

import java.util.ArrayList;

import baymax.exception.BaymaxException;

/**
 * A mutable collection of {@link Task} objects with convenience methods
 * for listing, adding, updating, and deleting tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public ArrayList<Task> getAll() {
        return tasks;
    }

    /**
     * Adds a task to the list and returns a confirmation message.
     *
     * @param task The task to add.
     * @return A confirmation string including the task and count.
     */
    public String addTask(Task task) {
        this.tasks.add(task);
        return String.format("""
                        Got it. I've added this task:
                        \t%s
                        Now you have %d tasks in the list.""",
                task,
                tasks.size());

    }

    @Override
    public String toString() {
        if (this.tasks.isEmpty()) {
            return "Your list is empty. I will be here when you are ready to add something.";
        }

        StringBuilder str = new StringBuilder("""
                Here are the tasks I found in your list.
                Let’s take care of them together:""");

        for (int i = 0; i < tasks.size(); i++) {
            str.append("\n\t%d. %s".formatted(i + 1, tasks.get(i)));
        }

        return str.toString();
    }

    /**
     * Marks the task at the provided index and returns a confirmation message.
     *
     * @param index The 0-based index of the task to mark.
     * @return A confirmation string including the task.
     * @throws BaymaxException.InvalidIndexException If the index is out of bounds.
     */
    public String mark(int index) throws BaymaxException.InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new BaymaxException.InvalidIndexException(index);
        }
        return this.tasks.get(index).mark();
    }

    /**
     * Unmarks the task at the provided index and returns a confirmation message.
     *
     * @param index The 0-based index of the task to unmark.
     * @return A confirmation string including the task.
     * @throws BaymaxException.InvalidIndexException If the index is out of bounds.
     */
    public String unmark(int index) throws BaymaxException.InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new BaymaxException.InvalidIndexException(index);
        }
        return this.tasks.get(index).unmark();
    }

    /**
     * Deletes the task at the provided index and returns a confirmation message.
     *
     * @param index The 0-based index of the task to delete.
     * @return A confirmation string including the removed task.
     * @throws BaymaxException.InvalidIndexException If the index is out of bounds.
     */
    public String delete(int index) throws BaymaxException.InvalidIndexException {
        if (index < 0 || index >= tasks.size()) {
            throw new BaymaxException.InvalidIndexException(index);
        }

        Task task = tasks.get(index);
        tasks.remove(index);

        return String.format("""
                        Noted. I've removed this task:
                        \t%s
                        Now you have %d tasks in the list.""",
                task,
                this.tasks.size());
    }

    /**
     * Searches the task list for tasks whose description contains the given keyword.
     * <p>
     * If one or more matches are found, they are returned in a numbered list.
     * If no matches are found, a message is returned instead.
     * </p>
     *
     * @param keyword The keyword to search for within task descriptions.
     * @return A message listing the matching tasks,
     *         or a plain message if no matches were found.
     */
    public String find(String keyword) {
        StringBuilder str = new StringBuilder("""
                I have scanned your list and found these tasks for you:""");

        int count = 0;

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.description.contains(keyword)) {
                str.append("\n\t%d. %s".formatted(count + 1, task));
                count++;
            }
        }

        if (count == 0) {
            return """
                I scanned your list thoroughly, but I could not find any tasks matching your request.
                Do not worry, I am still here to assist you.""";
        }

        return str.toString();
    }
}
