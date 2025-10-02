package focus;

import java.util.ArrayList;

/**
 * Maintains the collection of tasks and provides operations to query and modify it.
 */
public class TaskList {

    private ArrayList<Task> tasks = new ArrayList<>();

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public Task get(int i) {
        return this.tasks.get(i);
    }

    public int size() {
        return this.tasks.size();
    }

    /**
     * Marks task at given single or multiple index as done.
     */
    public void markTasks(int... userIndexes) throws FocusException {

        if (userIndexes == null || userIndexes.length == 0) {
            throw new FocusException("     Index required.");
        }

        StringBuilder sb = new StringBuilder("     Nice! I've marked these tasks as done:\n");
        StringBuilder sb2 = new StringBuilder("Note that the following task indices are already marked as done:");

        if (userIndexes.length == 1) {
            int index = userIndexes[0] - 1;
            if (index < 0 || index >= this.tasks.size()) {
                throw new FocusException("     Index out of range: " + index);
            }
            Task singleTask = tasks.get(userIndexes[0] - 1);
            if (singleTask.isDone()) {
                System.out.printf("       This task is already marked as done!");
                return;
            } else {
                singleTask.markAsDone();
                sb.append(String.format("       %s\n", tasks.get(index)));
            }
            System.out.println(sb);
            return;
        }

        boolean allMarkedDone = true;
        boolean allMarkedNotDone = true;

        for (int userIndex : userIndexes) {
            int i = userIndex - 1; // convert 1-based to 0-based
            if (i < 0 || i >= this.tasks.size()) {
                throw new FocusException("     Index out of range: " + userIndex);
            }
            Task currentTask = tasks.get(i);
            if (!currentTask.isDone()) { // Current task isn't done
                allMarkedDone = false;
                currentTask.markAsDone();
                sb.append(String.format("       %s\n", tasks.get(i)));
            } else {
                sb2.append(String.format(" %d", i + 1));
                allMarkedNotDone = false;
            }
        }

        if (allMarkedDone) {
            System.out.printf("       All inputted tasks already marked as done!");
        } else if (allMarkedNotDone) {
            System.out.println(sb);
        } else {
            System.out.println(sb);
            System.out.println(sb2);
        }

    }

    /**
     * Marks task at given single or multiple index as done.
     */
    public void unmarkTasks(int... userIndexes) throws FocusException {

        if (userIndexes == null || userIndexes.length == 0) {
            throw new FocusException("     Index required.");
        }

        StringBuilder sb = new StringBuilder("     Nice! I've marked these tasks as not done:\n");
        StringBuilder sb2 = new StringBuilder("Note that the following task indices are already marked as not done:");

        if (userIndexes.length == 1) {
            int index = userIndexes[0] - 1;
            if (index < 0 || index >= this.tasks.size()) {
                throw new FocusException("     Index out of range: " + index);
            }
            Task singleTask = tasks.get(userIndexes[0] - 1);
            if (!singleTask.isDone()) {
                System.out.printf("       This task is already marked as not done!");
                return;
            } else {
                singleTask.markNotDone();
                sb.append(String.format("       %s\n", tasks.get(index)));
            }
            System.out.println(sb);
            return;
        }

        boolean allMarkedNotDone = true;
        boolean allMarkedDone = true;

        for (int userIndex : userIndexes) {
            int i = userIndex - 1; // convert 1-based to 0-based
            if (i < 0 || i >= this.tasks.size()) {
                throw new FocusException("     Index out of range: " + userIndex);
            }
            Task currentTask = tasks.get(i);
            if (currentTask.isDone()) { // Current task is done
                allMarkedNotDone = false;
                currentTask.markNotDone();
                sb.append(String.format("       %s\n", tasks.get(i)));
            } else { // Current task isn't done
                sb2.append(String.format(" %d", i + 1));
                allMarkedDone = false;
            }
        }

        if (allMarkedNotDone) {
            System.out.printf("       All inputted tasks already marked as not done!");
        } else if (allMarkedDone) {
            System.out.println(sb);
        } else {
            System.out.println(sb);
            System.out.println(sb2);
        }

    }

    /**
     * Prints all tasks to standard output in display format.
     */
    public void printTaskList() {

        if (this.tasks.isEmpty()) {
            System.out.println("     No tasks in your list.");
            return;
        }

        System.out.println("     Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); i++) {
            System.out.printf("     %d.%s\n", i + 1, this.tasks.get(i));
        }

    }

    /**
     * Prints all tasks to standard output based on keyword matching from FindCommand.
     */
    public void printMatchingWords(String keyword) {

        System.out.println("     Here are the matching tasks in your list:");
        String k = keyword.toLowerCase();
        int shown = 0;

        for (int i = 0; i < this.tasks.size(); i++) {
            Task currentTask = this.tasks.get(i);
            if (currentTask.getDescription().toLowerCase().contains(k)) {
                System.out.printf("     %d.%s\n", i + 1, currentTask);
                shown++;
            }
        }

        if (shown == 0) {
            System.out.println("     No matching tasks found.");
        }

    }

    /**
     * Adds a task to the list.
     *
     * @param task Task to add.
     * @param loading true if called during file loading, false if user-initiated.
     *                Used to print message
     */
    public void addTask(Task task, boolean loading) {

        tasks.add(task);

        if (!loading) {
            System.out.println("     Got it. I've added this task:");
            System.out.printf("  %s\n", task);
            System.out.printf("     Now you have %d tasks in the list.\n", this.tasks.size());
        }

    }

    /**
     * Deletes a task at the given zero-based index.
     *
     * @param i Zero-based index of the task to delete.
     */
    public void deleteTask(int i) {

        String deletedTaskString = this.tasks.get(i).toString();
        this.tasks.remove(i);
        System.out.println("     Noted. I've removed this task:");
        System.out.printf("       %s\n", deletedTaskString);
        System.out.printf("     Now you have %d tasks in the list.\n", this.tasks.size());

    }

}
