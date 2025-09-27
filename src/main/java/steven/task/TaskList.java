package steven.task;

import java.util.ArrayList;

import steven.exception.EmptyDescriptionException;
import steven.exception.InvalidFixedDurationTimeException;
import steven.exception.InvalidMarkFormatException;
import steven.exception.InvalidTaskFormatException;
import steven.exception.MissingDeadlineException;
import steven.exception.MissingDurationException;
import steven.exception.MissingFindKeywordException;
import steven.exception.MissingStartAndEndTimeException;
import steven.exception.StevenException;
import steven.storage.Storage;

/**
 * Represents a list of tasks that can be modified by user commands.
 */
public class TaskList {
    private final ArrayList<Task> taskList;

    /**
     * Creates a TaskList with the given list of tasks.
     *
     * @param taskList the list of tasks to initialize with.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param input the raw user input containing the task description.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the added task and total task count.
     * @throws StevenException if the description is empty.
     */
    public String addToDoTask(String input, Storage storage) throws StevenException {
        String description = input.substring(4).trim();
        if (description.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        Task currentTask = new ToDo(description);
        taskList.add(currentTask);
        storage.saveToLocal(taskList);
        return "\tOK, I've added this task: " + currentTask + "\n\tNow there are "
                + taskList.size() + " tasks in your list";
    }

    /**
     * Adds a new ToDo task to the list.
     *
     * @param input the raw user input containing the task description.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the added task and total task count.
     * @throws StevenException if the description is empty.
     */
    public String addDeadlineTask(String input, Storage storage) throws StevenException {
        String descriptionAndDeadline = input.substring(8).stripLeading();
        if (descriptionAndDeadline.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        int byIndex = descriptionAndDeadline.indexOf(" /by ");
        if (byIndex == -1) {
            throw new InvalidTaskFormatException("deadline");
        }
        String description = descriptionAndDeadline.substring(0, byIndex);
        String deadline = descriptionAndDeadline.substring(byIndex + 5);
        if (deadline.trim().isEmpty()) {
            throw new MissingDeadlineException();
        }
        Task currentTask = new Deadline(description, deadline);
        taskList.add(currentTask);
        storage.saveToLocal(taskList);
        return "\tOK, I've added this task: " + currentTask + "\n\tNow there are "
                + taskList.size() + " tasks in your list";
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param input the raw user input containing the description, start time, and end time.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the added task and total task count.
     * @throws StevenException if the description is empty, start/end time is missing, or format is invalid.
     */
    public String addEventTask(String input, Storage storage) throws StevenException {
        String descriptionAndTime = input.substring(5).stripLeading();
        if (descriptionAndTime.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        int fromIndex = descriptionAndTime.indexOf(" /from ");
        int toIndex = descriptionAndTime.indexOf(" /to ");
        if (fromIndex == -1 || toIndex == -1 || toIndex <= fromIndex) {
            throw new InvalidTaskFormatException("event");
        }
        String description = descriptionAndTime.substring(0, fromIndex);
        String from = descriptionAndTime.substring(fromIndex + 7, toIndex);
        String to = descriptionAndTime.substring(toIndex + 5);
        if (from.isBlank() || to.isBlank()) {
            throw new MissingStartAndEndTimeException();
        }
        Task currentTask = new Event(description, from, to);
        taskList.add(currentTask);
        storage.saveToLocal(taskList);
        return "\tOK, I've added this task: " + currentTask + "\n\tNow there are "
                + taskList.size() + " tasks in your list";
    }

    /**
     * Adds a new FixedDuration task to the list.
     *
     * @param input the raw user input containing the description and duration.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the added task and total task count.
     * @throws StevenException if the description is empty, duration is missing, or format is invalid.
     */
    public String addFixedDurationTask(String input, Storage storage) throws StevenException {
        try {
            String descriptionAndDuration = input.substring(14).stripLeading();
            if (descriptionAndDuration.isEmpty()) {
                throw new EmptyDescriptionException();
            }
            int durationIndex = descriptionAndDuration.indexOf(" /duration ");
            if (durationIndex == -1) {
                throw new InvalidTaskFormatException("fixed duration");
            }
            String description = descriptionAndDuration.substring(0, durationIndex);
            String duration = descriptionAndDuration.substring(durationIndex + 11);
            if (duration.trim().isEmpty()) {
                throw new MissingDurationException();
            }
            Task currentTask = new FixedDuration(description, Float.parseFloat(duration));
            taskList.add(currentTask);
            storage.saveToLocal(taskList);
            return "\tOK, I've added this task: " + currentTask + "\n\tNow there are "
                    + taskList.size() + " tasks in your list";
        } catch (NumberFormatException e) {
            throw new InvalidFixedDurationTimeException();
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input the raw user input specifying the task index to delete.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the deleted task and remaining task count, or an error message.
     */
    public String deleteTask(String input, Storage storage) {
        if (!input.startsWith("delete ")) {
            return "Invalid input";
        }
        try {
            int number = Integer.parseInt(input.substring(7));
            if (number < 1 || number > taskList.size()) {
                return "\tYou only have " + taskList.size() + " tasks in your list";
            }
            Task deletedTask = taskList.remove(number - 1);
            storage.saveToLocal(taskList);
            return "\tOK, DELETE THIS ONE ALR:\n\t" + deletedTask + "\n\tNow ur list got "
                    + taskList.size() + " task";
        } catch (NumberFormatException e) {
            return "Invalid input";
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input the raw user input specifying the task index.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the marked task.
     * @throws InvalidMarkFormatException if the input format is invalid.
     */
    public String markTask(String input, Storage storage) throws InvalidMarkFormatException {
        if (!input.startsWith("mark ")) {
            throw new InvalidMarkFormatException();
        }
        try {
            int number = Integer.parseInt(input.substring(5));
            if (number > taskList.size() || number < 1) {
                return "\tYou only have " + taskList.size() + " tasks in your list";
            }
            taskList.get(number - 1).markAsDone();
            storage.saveToLocal(taskList);
            return "\tOK, I will mark this task as done \n\t" + taskList.get(number - 1);
        } catch (NumberFormatException e) {
            throw new InvalidMarkFormatException();
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param input the raw user input specifying the task index.
     * @param storage the storage object used to persist the task list.
     * @return confirmation message including the unmarked task.
     * @throws InvalidMarkFormatException if the input format is invalid.
     */
    public String unmarkTask(String input, Storage storage) throws InvalidMarkFormatException {
        if (!input.startsWith("unmark ")) {
            throw new InvalidMarkFormatException();
        }
        try {
            int number = Integer.parseInt(input.substring(7));
            if (number > taskList.size() || number < 1) {
                return "\tYou only have " + taskList.size() + " tasks in your list";
            }
            taskList.get(number - 1).markAsNotDone();
            storage.saveToLocal(taskList);
            return "\tOK, I will mark this task as not done \n\t" + taskList.get(number - 1);
        } catch (NumberFormatException e) {
            throw new InvalidMarkFormatException();
        }
    }

    /**
     * Returns all tasks as a formatted string.
     *
     * @return string containing all tasks in the list.
     */
    public String getToDoListString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\tHere are the tasks in your list: ");
        for (int i = 0; i < taskList.size(); i++) {
            sb.append("\n\t\t")
                    .append(i + 1)
                    .append(". ")
                    .append(taskList.get(i).toString());
        }
        return sb.toString();
    }

    /**
     * Finds tasks containing the given keyword.
     *
     * @param input the raw user input containing the keyword to search.
     * @return string listing all matching tasks or a message if none found.
     * @throws MissingFindKeywordException if the keyword is missing.
     */
    public String findTasks(String input) throws MissingFindKeywordException {
        String[] parts = input.trim().split("\\s+", 2);

        if (parts.length < 2 || parts[1].isBlank()) {
            throw new MissingFindKeywordException();
        }

        String keyword = parts[1];

        StringBuilder sb = new StringBuilder();
        sb.append("\tHere are the matching tasks in your list:\n");

        int i = 1;
        for (Task task : taskList) {
            if (task.toString().contains(keyword)) {
                sb.append("\t").append(i).append(". ").append(task).append("\n");
                i++;
            }
        }

        if (i == 1) {
            return "\tNo matching tasks found";
        }

        return sb.toString();
    }
}
