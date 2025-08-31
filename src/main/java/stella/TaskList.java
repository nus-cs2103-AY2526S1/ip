package stella;

import java.util.ArrayList;

/**
 * Represents a group of tasks
 */
public class TaskList {
    public ArrayList<Task> list;
    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    /**
     * Print out each task on the list. Inform user if list is empty
     */
    public void printList() {
        if (this.list.isEmpty()) {
            System.out.println("Task list is empty. Add one now: ");
        }

        for (int i = 1; i <= list.size(); i = i + 1) {
            System.out.println(" > " + i + ". " + list.get(i - 1));
        }
    }

    /**
     * Delete the item from list, inform users of deletion and
     * ensure item deletion are updated in local storage
     *
     * @param index Index of item to be deleted
     */
    public void deleteItem(int index) {
        Task temp = list.remove(index);
        System.out.println("I have removed the following: ");
        System.out.println("" + temp);
        System.out.println(" > Now you have " + list.size() + " task(s) in the list");
        Storage.modifyTaskList(list);
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
            list.get(index).markUndone();
            System.out.println(" > OK, I've marked this task as not done yet: ");
            System.out.println(" > " + list.get(index));
            Storage.modifyTaskList(list);
        }

        if (description == "mark") {
            list.get(index).markDone();
            System.out.println(" > Nice! I've marked this task as done: ");
            System.out.println(" > " + list.get(index));
            Storage.modifyTaskList(list);
        }
    }

    /**
     * Add item to list and local storage, and inform user of the addition
     *
     * @param task Task to be added to the list
     */
    public void addItem(Task task) {
        list.add(task);
        System.out.println(" > added: " + list.get(list.size() - 1));
        System.out.println(" > Now you have " + list.size() + " task(s) in the list");
        Storage.addTask(task);
    }
}
