package ryuji.task;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Represents a list of {@link Task} objects with operations to add, delete, mark,
 * unmark, filter, and search tasks.
 * <p>The {@code TaskList} provides various functionalities to manage a collection
 * of tasks, including:</p>
 * <ul>
 *   <li>Adding a new task to the list</li>
 *   <li>Deleting a task by its index</li>
 *   <li>Marking or unmarking tasks as completed</li>
 *   <li>Searching for tasks based on a label</li>
 *   <li>Displaying all tasks in the list with their respective status</li>
 * </ul>
 */
public class TaskList {

    /**
     * The internal list that stores tasks.
     */
    private List<Task> tasks;

    /**
     * Constructs an empty {@code TaskList}.
     * <p>This constructor initializes the task list as an empty list.</p>
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Constructs a {@code TaskList} with the specified list of tasks.
     *
     * @param tasks the list of tasks to initialize the task list with
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public Task getTask(int position) {
        return tasks.get(position - 1);
    }

    /**
     * Adds a valid task to the list. If the task is invalid, it will not be added
     * and an error message will be returned.
     * <p>This method checks if the task is valid by calling its {@code checkValid()}
     * method. If valid, the task is added to the list and a success message is returned;
     * otherwise, an error message is returned.</p>
     *
     * @param item the task to add
     * @return a message indicating success or failure
     */
    public String addToList(Task item) {
        if (item.checkValid()) {
            tasks.add(item);
            return "I have added the following task from your task list master: " + item;
        } else {
            return "I am unable to add that task to your list master for it is in the wrong format";
        }
    }

    /**
     * Deletes a task at the specified 1-based index.
     * <p>If the index is out of bounds, an error message is returned. If valid,
     * the task is removed and a success message is returned.</p>
     *
     * @param index the 1-based index of the task to delete
     * @return a message indicating success or failure
     */
    public String deleteFromList(int index) {
        if (index < 1 || index > tasks.size()) {
            return "Master I can't find that item in your list";
        }
        Task removed = tasks.remove(index - 1);
        return "I have removed the following task from your task list master: " + removed;
    }

    /**
     * Marks the task at the specified 1-based index as completed.
     * <p>If the index is out of bounds, an error message is returned. If valid,
     * the task is marked and a success message is returned.</p>
     *
     * @param index the 1-based index of the task to mark as done
     * @return a message indicating success or failure
     */
    public String mark(int index) {
        if (index < 1 || index > tasks.size()) {
            return "Master I can't find that item in your list";
        }
        tasks.get(index - 1).mark();
        return "I have marked the following task as completed master: " + tasks.get(index - 1);
    }

    /**
     * Unmarks the task at the specified 1-based index as not completed.
     * <p>If the index is out of bounds, an error message is returned. If valid,
     * the task is unmarked and a success message is returned.</p>
     *
     * @param index the 1-based index of the task to unmark
     * @return a message indicating success or failure
     */
    public String unmark(int index) {
        if (index < 1 || index > tasks.size()) {
            return "Master I can't find that item in your list";
        }
        tasks.get(index - 1).unmark();
        return "I have unmarked the following task from your list master: " + tasks.get(index - 1);
    }

    /**
     * Finds and returns a new {@code TaskList} containing tasks whose labels match the
     * given search term.
     * <p>This method iterates through all tasks in the list and adds those whose labels
     * contain the given search term to a new list. A new {@code TaskList} object is
     * returned containing the matching tasks.</p>
     *
     * @param searchTerm the keyword to search for in task descriptions or labels
     * @return a {@code TaskList} of matching tasks
     */
    public TaskList find(String searchTerm) {
        ArrayList<Task> validTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (task.checkLabel(searchTerm)) {
                validTasks.add(task);
            }
        }
        return new TaskList(validTasks);
    }

    /**
     * Returns a string representation of all tasks in the list.
     * <p>Tasks are numbered starting from 1, and each task is formatted using its
     * {@code toString()} method. The list is prefixed with a header indicating the
     * user's task list.</p>
     *
     * @return a formatted string listing all tasks
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Here are the tasks you requested master\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString();
    }
}
