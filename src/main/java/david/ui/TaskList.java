package david.ui;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import david.task.Task;

/**
 * Stores all tasks.
 */
public class TaskList {
    private ArrayList<Task> list;
    private Deque<ArrayList<Task>> history = new ArrayDeque<>();

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Overloads the constructor, returns an empty list.
     */
    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    /**
     * Saves the deep copy of tasklist into a deque.
     */
    public void saveState() {
        ArrayList<Task> snapshot = new ArrayList<>();
        for (Task t : list) {
            snapshot.add(t.copy());
        }
        history.push(snapshot);
    }

    /**
     * Adds new tasks to the list.
     *
     * @param t A task to be added.
     */
    public void add(Task t) {
        saveState();
        this.list.add(t);
    }

    /**
     * Deletes tasks from the list.
     *
     * @param index Index of the task to be deleted.
     */
    public void delete(int index) {
        assert index >= 0 && index < size() : "Index must be within bound";
        saveState();
        this.list.remove(index);
    }

    /**
     * Prints the string representation of all tasks from the list.
     */
    public void printList() {
        System.out.print(buildString("\n Here are the tasks in your list: ").indent(4));
        System.out.println();
    }

    /**
     * Executes list command from GUI.
     *
     * @return The string representation of all tasks from the list.
     */
    public String printListString() {
        return buildStringGui("\n Here are the tasks in your list: ");
    }

    /**
     * Prints the string representation of all matching tasks from the find command.
     */
    public void printMatchList() {
        System.out.print(buildString("\n Here are the matching tasks in your list: ")
                                                                                    .indent(4));
        System.out.println();
    }

    /**
     * Executes find command from GUI.
     *
     * @return The string representation of all matching tasks from the find command.
     */
    public String printMatchListString() {
        return buildStringGui("\n Here are the matching tasks in your list: ");
    }

    private String buildString(String header) {
        String tasks = IntStream.range(0, list.size())
                .mapToObj(i -> String.format(" %d. %s", i + 1, list.get(i)))
                .collect(Collectors.joining("\n"));
        return Formatter.NEWLINE + header + "\n" + tasks + "\n" + Formatter.NEWLINE;
    }

    private String buildStringGui(String header) {
        return header + IntStream.range(0, list.size())
                .mapToObj(i -> String.format("\n %d. %s", i + 1, list.get(i)))
                .reduce("", String::concat);
    }

    /**
     * Gets the task given an index.
     *
     * @param index The index of a task.
     * @return The desired task.
     */
    public Task get(int index) {
        assert index >= 0 && index < size() : "Index must be within bound";
        return list.get(index);
    }

    public ArrayList<Task> getList() {
        return this.list;
    }

    /**
     * @return The size of task list.
     */
    public int size() {
        return list.size();
    }

    /**
     * @return True if undo command is successful.
     */
    public boolean undo() {
        if (history.isEmpty()) {
            return false;
        }
        this.list = history.pop();
        return true;
    }
}
