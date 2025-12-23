package george.task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import george.exceptions.GeorgeException;
import george.utils.Storage;

/**
 * Manages the collection of tasks including adding, deleting, marking, and listing tasks.
 * Handles persistence of tasks through the Storage class.
 */
public class TaskManager {
    private List<Task> tasksList;
    private Storage storage;

    /**
     * Constructs a TaskManager with the specified data file name for storage.
     *
     * @param dataFileName The name of the file to store tasks data
     */
    public TaskManager(String dataFileName) {
        this.tasksList = new ArrayList<>();
        this.storage = new Storage(dataFileName);
    }

    /**
     * Validates task index and throws appropriate exception if invalid.
     *
     * @param taskNumber The task number to validate (1-based index)
     * @throws GeorgeException If the task number is invalid
     */
    private void validateTaskIndex(int taskNumber) throws GeorgeException {
        if (taskNumber < 1 || taskNumber > tasksList.size()) {
            throw new GeorgeException("Task " + taskNumber + " does not exist!");
        }
    }

    /**
     * Adds a new todo task to the task list.
     *
     * @param description The description of the todo task
     * @return The display text of adding To Do Task
     * @throws GeorgeException If the task creation fails
     */
    public String addToDoTask(String description) throws GeorgeException {
        if (description == null || description.trim().isEmpty()) {
            throw new GeorgeException("Todo description cannot be empty!");
        }

        Task task = new ToDoTask(description.trim());
        tasksList.add(task);
        saveTasks();
        return getAddTaskMessage(task);
    }

    /**
     * Adds a new deadline task to the task list.
     *
     * @param description The description of the deadline task
     * @param date The deadline date string
     * @return The display text of adding Deadline Task.
     * @throws GeorgeException If the task creation fails
     */
    public String addDeadlineTask(String description, String date) throws GeorgeException {
        if (description == null || description.trim().isEmpty()) {
            throw new GeorgeException("Deadline description cannot be empty!");
        }
        if (date == null || date.trim().isEmpty()) {
            throw new GeorgeException("Deadline date cannot be empty!");
        }

        Task task = new DeadlineTask(description.trim(), date.trim());
        tasksList.add(task);
        saveTasks();
        return getAddTaskMessage(task);
    }

    /**
     * Adds a new event task to the task list.
     *
     * @param description The description of the event task
     * @param startTime The start time string of the event
     * @param endTime The end time string of the event
     * @return The display text of adding Event Task.
     * @throws GeorgeException If the task creation fails
     */
    public String addEventTask(String description, String startTime, String endTime) throws GeorgeException {
        if (description == null || description.trim().isEmpty()) {
            throw new GeorgeException("Event description cannot be empty!");
        }
        if (startTime == null || startTime.trim().isEmpty()) {
            throw new GeorgeException("Event start time cannot be empty!");
        }
        if (endTime == null || endTime.trim().isEmpty()) {
            throw new GeorgeException("Event end time cannot be empty!");
        }

        Task task = new EventTask(description.trim(), startTime.trim(), endTime.trim());
        tasksList.add(task);
        saveTasks();
        return getAddTaskMessage(task);
    }

    /**
     * Generates the success message for adding a task.
     *
     * @param task The task that was added
     * @return The formatted success message
     */
    private String getAddTaskMessage(Task task) {
        return "You get a task. I get a banana!\n"
                + task.getDisplayText() + "\nYou now have "
                + tasksList.size() + " things to do!!!\n"
                + "Remember to do them NOW!!!";
    }

    /**
     * Deletes a task from the task list by its number.
     *
     * @param taskNumber The number of the task to delete (1-based index)
     * @return The display message of deleting a task.
     * @throws GeorgeException If the task number is invalid
     */
    public String deleteTask(int taskNumber) throws GeorgeException {
        validateTaskIndex(taskNumber);
        Task removedTask = tasksList.remove(taskNumber - 1);
        saveTasks();

        return "George will turn this task into a banana:\n"
                + removedTask.getDisplayText() + "\nYou now have "
                + tasksList.size() + " tasks in the list.";
    }

    /**
     * Lists all tasks in the task list with their status and descriptions.
     *
     * @return A formatted string containing all tasks or a message if the list is empty
     */
    public String listTasks() {
        if (tasksList.isEmpty()) {
            return "Wow you have no tasks! Here is a banana!";
        }

        StringBuilder result = new StringBuilder();
        result.append("OOO OOO AAA AAA here is all your tasks\n");
        for (int i = 0; i < tasksList.size(); i++) {
            result.append((i + 1) + "." + tasksList.get(i).getDisplayText() + "\n");
        }
        result.append("EEE EEE AAA AAA remember to do them all");

        return result.toString();
    }

    /**
     * Marks a task as done by its number.
     *
     * @param taskNumber The number of the task to mark as done (1-based index)
     * @return A success message with the marked task details
     * @throws GeorgeException If the task number is invalid or task is already done
     */
    public String markTaskAsDone(int taskNumber) throws GeorgeException {
        validateTaskIndex(taskNumber);
        Task task = tasksList.get(taskNumber - 1);

        if (task.isDone()) {
            throw new GeorgeException("Task " + taskNumber + " is already marked as done!");
        }

        task.markAsDone();
        saveTasks();

        return "Good job! Here is a banana for you!\n"
                + "[X] " + task.getDescription();
    }

    /**
     * Marks a task as not done by its number.
     *
     * @param taskNumber The number of the task to mark as not done (1-based index)
     * @return An encouragement message with the unmarked task details
     * @throws GeorgeException If the task number is invalid or task is already not done
     */
    public String markTaskAsNotDone(int taskNumber) throws GeorgeException {
        validateTaskIndex(taskNumber);
        Task task = tasksList.get(taskNumber - 1);

        if (!task.isDone()) {
            throw new GeorgeException("Task " + taskNumber + " is already not done!");
        }

        task.markAsNotDone();
        saveTasks();

        return "Come on! You can do it!\n"
                + "[ ] " + task.getDescription();
    }

    /**
     * Saves the current task list to storage.
     */
    private void saveTasks() {
        try {
            storage.saveTasks(tasksList);
        } catch (IOException e) {
            System.err.println("Error while saving tasks: " + e.getMessage());
        }
    }

    /**
     * Finds tasks containing the specified keyword in their description.
     *
     * @param keyword The keyword to search for
     * @return A formatted string containing matching tasks or a message if no matches found
     */
    public String findTasks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return "Please provide a keyword to search for!";
        }

        List<Task> matchingTasks = new ArrayList<>();
        String searchTerm = keyword.trim().toLowerCase();

        for (Task task : tasksList) {
            if (task.getDescription().toLowerCase().contains(searchTerm)) {
                matchingTasks.add(task);
            }
        }

        if (matchingTasks.isEmpty()) {
            return "No tasks found containing: " + keyword;
        }

        StringBuilder result = new StringBuilder();
        result.append("OOO OOO AAA AAA found " + matchingTasks.size() + " matching tasks:\n");
        for (int i = 0; i < matchingTasks.size(); i++) {
            result.append((i + 1) + "." + matchingTasks.get(i).getDisplayText() + "\n");
        }

        return result.toString();
    }

    /**
     * Loads tasks from storage into the task list.
     *
     * @throws IOException If an I/O error occurs during loading
     */
    public void load() throws IOException {
        tasksList = storage.loadTasks();
    }
}
