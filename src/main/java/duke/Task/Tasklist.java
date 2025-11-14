package duke.task;

import java.util.ArrayList;

/**
 * Manages a list of tasks in the Duke application.
 */
public class Tasklist {

    private static final ArrayList<Task> lst = new ArrayList<>();

    /**
     * Adds a task to the list.
     *
     * @param t the task to add
     */
    public void addToList(Task t) {
        lst.add(t);
    }

    /**
     * Removes a task from the list.
     *
     * @param i the index (1-based) of the task to remove
     */
    public void removeFromList(int i) {
        lst.remove(i - 1);
    }

    /**
     * Marks a task in the list as completed.
     *
     * @param i the index (1-based) of the task to mark done
     */
    public void tickbox(int i) {
        Task t = lst.get(i - 1);
        t.markDone();
    }

    /**
     * Marks a task in the list as not completed.
     *
     * @param i the index (1-based) of the task to mark undone
     */
    public void untickbox(int i) {
        Task t = lst.get(i - 1);
        t.markUndone();
    }

    /**
     * Returns the task at the given index.
     *
     * @param i the index (0-based) of the task
     * @return the task at that index
     */
    public static Task peekList(int i) {
        return lst.get(i);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return the size of the list
     */
    public static int listSize() {
        return lst.size();
    }

    /**
     * sets priority of task.
     *
     */
    public void setPriorityList(int index, String priority) {
        Task t = lst.get(index - 1);
        t.setPriority(priority);
    }

    /**
     * Displays all the tasks in the list.
     */
    public String displayList() {
        StringBuilder sb = new StringBuilder();
        sb.append("    ____________________________________\n");
        sb.append("     Here are the tasks in your list:\n");
        for (int i = 0; i < listSize(); i++) {
            sb.append("     ").append(i + 1).append(".").append(peekList(i)).append("\n");
        }
        sb.append("    ____________________________________");
        return sb.toString();
    }

    /**
     * Finds and displays tasks that match the given keyword.
     *
     * @param keyword the search keyword
     */
    public String find(String keyword) {
        StringBuilder sb = new StringBuilder();
        sb.append("    ____________________________________\n");
        sb.append("     Here are the matching tasks in your list:\n");
        for (int i = 0; i < listSize(); i++) {
            Task t = peekList(i);
            if (t.toString().contains(keyword)) {
                sb.append("     ").append(i + 1).append(".").append(t).append("\n");
            }
        }
        sb.append("    ____________________________________");
        return sb.toString();
    }
}
