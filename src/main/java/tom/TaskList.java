package tom;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents the list of tasks to be done by the user.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    public TaskList(ArrayList<Task> tasks) {
        this.taskList = tasks;
    }

    public ArrayList<Task> getTasks() {
        return taskList;
    }

    /**
     * Returns the message to be displayed for list command.
     * @return String for the UI to print.
     * @throws IOException If an error occurs when writing to the txt file.
     */
    public String list() throws IOException {
        Storage.writeLines(taskList);
        return Ui.list(taskList);
    }

    /**
     * Returns the message to be displayed for add command.
     * @return String for the UI to print.
     */
    public String add(Task task) {
        taskList.add(task);
        return Ui.add(task, this);
    }

    /**
     * Returns the message to be displayed for delete command.
     * @return String for the UI to print.
     */
    public String delete(int n) throws TomException {
        if (n <= 0) {
            throw new TomException("Index must be a positive integer");
        }
        if (n > taskList.size()) {
            throw new TomException("Index should be less than the number of tasks!");
        }
        Task removed = taskList.get(n - 1);
        taskList.remove(removed);
        return Ui.delete(removed, taskList);
    }

    /**
     * Returns the message to be displayed for mark command.
     * @return String for the UI to print.
     */
    public String mark(int n) throws TomException {
        if (n <= 0) {
            throw new TomException("Index must be a positive integer");
        }
        if (n > taskList.size()) {
            throw new TomException("Index should be less than the number of tasks!");
        }
        Task cur = taskList.get(n - 1);
        cur.mark();
        return Ui.mark(cur);
    }

    /**
     * Returns the message to be displayed for unmark command.
     * @return String for the UI to print.
     */
    public String unmark(int n) throws TomException {
        if (n <= 0) {
            throw new TomException("Index must be a positive integer");
        }
        if (n > taskList.size()) {
            throw new TomException("Index should be less than the number of tasks!");
        }
        Task cur = taskList.get(n - 1);
        cur.unmark();
        return Ui.unmark(cur);
    }

    /**
     * Returns the message to be displayed for prioritise command.
     * @return String for the UI to print.
     */
    public String prioritise(int n) throws TomException {
        if (n <= 0) {
            throw new TomException("Index must be a positive integer");
        }
        if (n > taskList.size()) {
            throw new TomException("Index should be less than the number of tasks!");
        }
        Task cur = taskList.get(n - 1);
        cur.prioritise();
        return Ui.prioritise(cur);
    }

    /**
     * Returns the message to be displayed for find command.
     * @return String for the UI to print.
     */
    public String find(String keyword) {
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : taskList) {
            if (t.description.contains(keyword)) {
                found.add(t);
            }
        }
        return Ui.find(found);
    }
}
