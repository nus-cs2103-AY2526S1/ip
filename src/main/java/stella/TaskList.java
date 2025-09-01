package stella;

import java.util.ArrayList;

/**
 * Represents a group of tasks
 */
public class TaskList {
    public ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Print out each task on the list. Inform user if list is empty
     */
    public void printList() {
        if (this.tasks.isEmpty()) {
            System.out.println("Task list is empty. Add one now: ");
        }

        for (int i = 1; i <= tasks.size(); i = i + 1) {
            System.out.println(" > " + i + ". " + tasks.get(i - 1));
        }
    }

    /**
     * Delete the item from list, inform users of deletion and
     * ensure item deletion are updated in local storage
     *
     * @param index Index of item to be deleted
     */
    public void deleteItem(int index) {
        Task temp = tasks.remove(index);
        System.out.println("I have removed the following: ");
        System.out.println("" + temp);
        System.out.println(" > Now you have " + tasks.size() + " task(s) in the list");
        Storage.modifyTaskList(tasks);
    }

    /**
     * When user want to mark or unmark tasks, function would
     * update the list, inform user of the change and ensure
     * local storage take note of the change
     *
     * @param index Index of item to be modified
     * @param description Either "mark" or "unmark"
     */
    public void modifyItem(int index, String description) {
        if (description == "unmark") {
            tasks.get(index).markUndone();
            System.out.println(" > OK, I've marked this task as not done yet: ");
            System.out.println(" > " + tasks.get(index));
            Storage.modifyTaskList(tasks);
        }

        if (description == "mark") {
            tasks.get(index).markDone();
            System.out.println(" > Nice! I've marked this task as done: ");
            System.out.println(" > " + tasks.get(index));
            Storage.modifyTaskList(tasks);
        }
    }

    /**
     * Add item to list and local storage, and inform user of the addition
     *
     * @param task Task to be added to the list
     */
    public void addItem(Task task) {
        tasks.add(task);
        System.out.println(" > added: " + tasks.get(tasks.size() - 1));
        System.out.println(" > Now you have " + tasks.size() + " task(s) in the list");
        Storage.addTask(task);
    }

    public ArrayList<Task> getList() {
        return tasks;
    }

    /**
     * Search for all the tasks whose description matches the identifier
     * @param identifier A keyword that find tasks whose description matches the identifier
     * @return A TaskList with tasks whose description matches the identifier
     */
    public TaskList findItem(String identifier) {
        TaskList result = new TaskList(new ArrayList<>());
        if (this.tasks.isEmpty()) {
            return result;
        }
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            if (tasks.get(i - 1).getDescription().contains(identifier)) {
                result.tasks.add(tasks.get(i - 1));
            }
        }
        return result;
    }

}
