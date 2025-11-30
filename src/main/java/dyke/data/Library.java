package dyke.data;

import java.util.ArrayList;

import dyke.tasks.Task;

/**
 * Houses methods for manipulating Tasks inside the Library
 */
public class Library {
    private ArrayList<Task> library;

    public Library() {
        this.library = new ArrayList<Task>();
    }

    private Library(ArrayList<Task> library) {
        this.library = library;
    }

    /**
     * Adds tasks into Library.
     * @param task Valid task.
     * @return The message after the addTask operation.
     */
    public String addTask(Task task) {

        String res = " Great! I'm keeping track :D:\n"
                + "\t" + task.toString();
        library.add(task);
        int size = library.size();
        return res + String.format("\n There %s %d %s in the Library.", (
                size > 1 ? "are" : "is"),
                size,
                "task" + (size > 1 ? "s" : ""));

    }

    /**
     * Adds tasks silently into Library when loading from file in disk.
     *
     * @param task A valid task.
     */
    public void silentAdd(Task task) {
        library.add(task);
    }

    /**
     * Returns {@code Tasks} in the {@code Library}
     * @return Library
     */
    public ArrayList<Task> getTasks() {
        return this.library;
    }
    private Task getTask(int index) {
        assert index >= 0 && index <= library.size();
        return this.library.get(index);
    }

    /**
     * Marks tasks with "done" in the Library.
     * @param index Task number; non-zero indexing.
     * @return The message after the markTask operation.
     */
    public String markTask(int index) {
        if (index < 0 || index >= this.library.size()) {
            return "Hey... That doesn't exist!";
        } else {
            Task task = this.getTask(index).isDone();
            this.library.set(index, task);
            return "Nice Work! I have marked this task as done:"
                    + "\n" + (index + 1) + ". " + task;
        }
    }

    /**
     * Deletes task from Library.
     * @param index Task number; non-zero indexing.
     * @return The message after the deleteTask operation.
     */
    public String deleteTask(int index) {
        if (index < 0 || index >= this.library.size()) {
            return "Hey... That doesn't exist!";
        }

        Task task = this.getTask(index);
        library.remove(index);
        return "As you wish... Begone Task!:"
                + "\n\t" + task + "\nNow you have " + this.size() + " tasks.";

    }

    /**
     * Checks if Library is empty.
     * @return boolean
     */
    public boolean isEmpty() {
        return this.library.isEmpty();
    }

    /**
     * Returns number of tasks in Library.
     * @return int Number of Tasks
     */
    public int size() {
        return this.library.size();
    }

    /**
     * @return A view of the Library in a readable format.
     */
    public String printList() {
        if (this.isEmpty()) {
            return "list is empty :(";
        } else {
            StringBuilder res = new StringBuilder("Here it is!");
            for (int i = 0; i < this.size(); i++) {
                res.append("\n").append(i + 1).append(". ").append(this.getTask(i));
            }
            return res.toString();
        }
    }
}
