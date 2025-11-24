package woop.logic;

import java.util.ArrayList;

/**
 * Class to keep track of all Task objects in current Woop run.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Initialises a TaskList object with an empty list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the list.
     *
     * @param t Task to be added.
     * @return Added task converted to string format for output.
     */
    public String addTask(Task t) {
        assert t != null : "Cannot insert null Task!";
        this.tasks.add(t);
        return t.toString();
    }

    /**
     * Checks if list is empty.
     *
     * @return true if list is empty.
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Marks the Task at the given index.
     *
     * @param index The index of the Task to be marked.
     * @return The marked Task converted to string for output.
     */
    public String markTask(int index) throws MarkedTaskException {
        this.tasks.get(index).mark();
        return this.getTaskName(index);
    }

    /**
     * Unmarks the Task at the given index.
     *
     * @param index The index of the Task to be unmarked.
     * @return The unmarked Task converted to string for output.
     */
    public String unmarkTask(int index) throws UnmarkedTaskException {
        this.tasks.get(index).unmark();
        return this.getTaskName(index);
    }

    /**
     * Deletes the Task at the given index.
     *
     * @param index The index of the Task to be deleted.
     * @return The deleted Task converted to string for output.
     */
    public String deleteTask(int index) {
        Task t = this.tasks.get(index);
        this.tasks.remove(index);
        return t.toString();
    }

    /**
     * Tags the Task at the given index.
     *
     * @param index The index of the Task to be tagged
     * @param tag The tag to be assigned to the Task
     * @return The tagged Task converted to String for output.
     */
    public String tagTask(int index, String tag) {
        Task t = this.tasks.get(index);
        t.setTag(tag);
        return t.toString();
    }

    /**
     * Converts all Tasks in the list to the save format for save state.
     *
     * @return All Tasks in save format.
     */
    public String getTasksSaveFormat() {
        StringBuffer sb = new StringBuffer();
        if (isEmpty()) {
            return "";
        }
        for (int i = 0; i < getSize(); i++) {
            sb.append(this.tasks.get(i).getSaveInfo());
            sb.append("\n");
        }

        deleteNewLine(sb);
        return sb.toString();
    }

    public TaskList findKeyword(String keyword) {
        TaskList resList = new TaskList();
        for (Task task : this.tasks) {
            if (task.containsText(keyword)) {
                resList.addTask(task);
            }
        }
        return resList;
    }

    public int getSize() {
        return this.tasks.size();
    }

    private String getTaskName(int index) {
        return this.tasks.get(index).toString();
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < getSize(); i++) {
            String newLine = (i + 1) + " " + this.tasks.get(i); //increment i by 1 to start at 1
            sb.append(newLine);
            sb.append("\n");
        }
        deleteNewLine(sb);
        return sb.toString();
    }

    private static void deleteNewLine(StringBuffer sb) {
        int offset = 1;
        sb.deleteCharAt(sb.length() - offset);
    }
}
