package stella;

import java.util.ArrayList;

import stella.exception.IncompleteInstructionException;
import stella.exception.UnknownInstructionException;

import stella.task.Task;

/**
 * Represents a group of tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a new TaskList with the specified tasks.
     *
     * @param tasks The tasks.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Prints out each task on the list. Informs user if list is empty.
     *
     * @return Stella's response associated with printing of list.
     */
    public String printList() {
        if (this.tasks.isEmpty()) {
            return "Task list is empty. Add one now: ";
        }

        String result = "";
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            result = result + i + ". " + tasks.get(i - 1) + "\n";
        }
        return result;
    }

    /**
     * Deletes the item from list, informs users of deletion,
     * and ensures item deletion are updated in local storage.
     *
     * @param index Index of item to be deleted.
     * @return Stella's response associated with deletion of task.
     */
    public String deleteItem(int index) {
        Task temp = tasks.remove(index);
        Storage.modifyTaskList(tasks);
        return "I have removed the following: \n" + temp + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list";
    }

    /**
     * Updates the list, informs user of the change and ensures
     * local storage take note of the change when tasks are marked or unmarked.
     *
     * @param index Index of item to be modified.
     * @param description Either "mark" or "unmark".
     * @return Stella's response associated with modification of task.
     * @throws UnknownInstructionException If description is not "mark" or "unmark".
     */
    public String modifyItem(int index, String description) throws UnknownInstructionException {
        if (description == "unmark") {
            tasks.get(index).markUndone();
            Storage.modifyTaskList(tasks);
            return "OK, I've marked this task as not done yet: " + "\n" + tasks.get(index);
        } else if (description == "mark") {
            tasks.get(index).markDone();
            Storage.modifyTaskList(tasks);
            return "Nice! I've marked this task as done: " + "\n" + tasks.get(index);
        } else {
            throw new UnknownInstructionException("expected mark/unmark, but received " + description);
        }
    }

    /**
     * Adds item to list and local storage, and informs user of the addition.
     *
     * @param task Task to be added to the list.
     * @return Stella's response associated with addition of task.
     */
    public String addItem(Task task) {
        tasks.add(task);
        Storage.addTask(task);
        return "added: " + task + "\n"
                + "Now you have " + tasks.size() + " task(s) in the list";
    }

    /**
     * Searches for all the tasks whose description matches the identifier.
     *
     * @param description Contains find command and a keyword to identify tasks.
     * @return A TaskList with tasks whose description matches the identifier.
     * @throws IncompleteInstructionException If the description is incomplete.
     */
    public String findItem(String description) throws IncompleteInstructionException {
        String command = "find ";

        if (description.length() <= command.length()) {
            throw new IncompleteInstructionException(description);
        }
        if (this.tasks.isEmpty()) {
            return "Empty task list. Cannot search for anything";
        }

        String keyword = this.findKeyword(description);

        TaskList result = new TaskList(new ArrayList<>());
        for (int i = 1; i <= tasks.size(); i = i + 1) {
            Task currentTask = tasks.get(i-1);
            String taskDescription = currentTask.toString();
            if (taskDescription.contains(keyword)) {
                result.tasks.add(currentTask);
            }
        }

        if (result.tasks.isEmpty()) {
            return "No items found";
        } else {
            return result.printList();
        }
    }

    private String findKeyword(String description) {
        String command = "find ";
        int keywordLength = description.length() - command.length();
        int startIndexForKeyword = command.length();
        String keyword = "";

        if (keywordLength == 1) {
            keyword += description.charAt(startIndexForKeyword);
        } else {
            keyword += description.substring(startIndexForKeyword);
        }

        return keyword;
    }
}
