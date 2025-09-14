package stella;

import java.util.ArrayList;

import stella.exception.IncompleteInstructionException;
import stella.exception.UnknownInstructionException;
import stella.task.Task;

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
     *
     * @return Stella's response associated with printing of list
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
     * Delete the item from list, inform users of deletion and
     * ensure item deletion are updated in local storage
     *
     * @param index Index of item to be deleted
     * @return Stella's response associated with deletion of task
     */
    public String deleteItem(int index) {
        Task temp = tasks.remove(index);
        Storage.modifyTaskList(tasks);
        return "I have removed the following: \n" + temp +  "\n" +
                "Now you have " + tasks.size() + " task(s) in the list";
    }

    /**
     * When user want to mark or unmark tasks, function would
     * update the list, inform user of the change and ensure
     * local storage take note of the change
     *
     * @param index Index of item to be modified
     * @param description Either "mark" or "unmark"
     * @return Stella's response associated with modification of task
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
     * Add item to list and local storage, and inform user of the addition
     *
     * @param task Task to be added to the list
     * @return Stella's response associated with addition of task
     */
    public String addItem(Task task) {
        tasks.add(task);
        Storage.addTask(task);
        return "added: " + task + "\n"
                + "Now you have "  + tasks.size() + " task(s) in the list";
    }

    /**
     * Search for all the tasks whose description matches the identifier
     * @param description Contain find command and a keyword to identify tasks
     * @return A TaskList with tasks whose description matches the identifier
     */
    public String findItem(String description) throws IncompleteInstructionException {
        String command = "find ";

        if (description.length() <= command.length()) {
            throw new IncompleteInstructionException(description);
        }

        if (this.tasks.isEmpty()) {
            return "Empty task list. Cannot search for anything";
        }

        String keyword = "";
        int keywordLength = description.length() - command.length();
        int startIndexForKeyword = command.length();
        if (keywordLength == 1) {
            keyword += description.charAt(startIndexForKeyword);
        } else {
            keyword += description.substring(startIndexForKeyword);
        }

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

}


