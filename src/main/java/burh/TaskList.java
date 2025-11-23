package burh;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private static final Logger LOGGER = Logger.getLogger(TaskList.class.getName());
    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Creates an empty TaskList.
     */
    public TaskList() {
        // Intentionally left empty
    }
    
    /**
     * Creates a TaskList with the given list of tasks.
     * 
     * @param tasks The initial list of tasks.
     * @throws BurhException If the task list is null.
     */
    public TaskList(List<Task> tasks) {
        if (tasks == null) {
            throw new BurhException(BurhException.ErrorType.UNKNOWN_ERROR, "Task list cannot be null");
        }
        this.tasks.addAll(tasks);
    }

    /**
     * Adds a task to the list and prints confirmation to the user.
     *
     * @param task The task to add.
     * @return Task addition confimration string
     */
    /**
     * Adds a task to the list and returns a confirmation message.
     *
     * @param task The task to add.
     * @return Confirmation message.
     * @throws BurhException If the task is null or a duplicate.
     */
    public String addTask(Task task) {
        if (task == null) {
            throw new BurhException(BurhException.ErrorType.UNKNOWN_ERROR, "Task cannot be null");
        }
        
        // Check for duplicate tasks
        if (tasks.stream().anyMatch(t -> t.equals(task))) {
            throw new BurhException(BurhException.ErrorType.DUPLICATE_TASK);
        }
        
        int initialSize = tasks.size();
        tasks.add(task);
        int newSize = tasks.size();
        
        // Verify the task was added
        if (newSize != initialSize + 1) {
            LOGGER.log(Level.SEVERE, "Failed to add task: {0}", task);
            throw new BurhException(BurhException.ErrorType.UNKNOWN_ERROR, "Failed to add task");
        }
        
        LOGGER.log(Level.INFO, "Added task: {0}", task);
        
        String[] reactions = {
            "Burh... fine. Added: " + task + "\n"
                    + "Burh, now you have " + tasks.size() + " things to not do.",
                    
            "*sigh* Burh, if I must... " + task + "\n"
                    + "Burh, that's " + tasks.size() + " tasks now. Not that I care.",
                    
            "Burh, really? Another one? " + task + "\n"
                    + tasks.size() + " tasks, burh. You're really pushing it."
        };
        
        return reactions[(int)(Math.random() * reactions.length)];
    }

    /**
     * Adds a task to the list without any user feedback.
     * 
     * @param task The task to add.
     * @throws BurhException If the task is null or a duplicate.
     */
    public void addTaskQuiet(Task task) {
        if (task == null) {
            throw new BurhException(BurhException.ErrorType.UNKNOWN_ERROR, "Task cannot be null");
        }
        
        // Check for duplicate tasks
        if (tasks.stream().anyMatch(t -> t.equals(task))) {
            LOGGER.log(Level.INFO, "Skipping duplicate task: {0}", task);
            return; // Silently skip duplicates during loading
        }
        
        tasks.add(task);
    }

    /**
     * Deletes the task at the given index and returns confirmation string.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     * @return Task deletion confirmation string.
     */
    /**
     * Deletes the task at the given index and returns a confirmation message.
     *
     * @param index The 1-based index of the task to delete.
     * @return Confirmation message.
     * @throws BurhException If the index is invalid.
     */
    public String deleteTask(int index) {
        validateIndex(index);
        
        Task removedTask = tasks.get(index - 1);
        tasks.remove(index - 1);
        
        LOGGER.log(Level.INFO, "Deleted task at index {0}: {1}", new Object[]{index, removedTask});
        
        String[] reactions = {
            "Burh, deleted " + removedTask + 
            "\n" + tasks.size() + " tasks left, burh. Not that it matters.",
            
            "Burh, fine. Removed: " + removedTask + 
            "\nBurh, down to " + tasks.size() + " tasks. Whatever.",
            
            "Burh... *sigh* Deleted " + removedTask + 
            "\n" + tasks.size() + " tasks remaining, burh. Not that you care."
        };
        
        return reactions[(int)(Math.random() * reactions.length)];
    }

    /**
     * Completes task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     * @return Task completion confirmation string.
     */
    /**
     * Marks the task at the given index as complete and returns a confirmation message.
     *
     * @param index The 1-based index of the task to complete.
     * @return Confirmation message.
     * @throws BurhException If the index is invalid or the task is already completed.
     */
    public String completeTask(int index) {
        validateIndex(index);
        
        Task task = tasks.get(index - 1);
        
        if (task.isDone()) {
            throw new BurhException(BurhException.ErrorType.UNKNOWN_ERROR, 
                "Burh, this task is already done. What more do you want?");
        }
        
        task.complete();
        LOGGER.log(Level.INFO, "Completed task at index {0}: {1}", new Object[]{index, task});
        
        String[] reactions = {
            "Burh... you actually did something for once.\n    " + task + "\nBurh, I'm shocked.",
            "*checks watch* Burh, took you long enough.\n    " + task + "\nBurh, what's next?",
            "Burh... a completed task. You want a medal or something?\n    " + task + "\nBurh, whatever."
        };
        
        return reactions[(int)(Math.random() * reactions.length)];
    }

    /**
     * Marks the most recently added task as complete.
     * Used during loading to restore task completion status.
     * If the task list is empty, this method does nothing.
     */
    public void completeMostRecent() {
        if (!tasks.isEmpty()) {
            Task task = tasks.get(tasks.size() - 1);
            task.complete();
            LOGGER.log(Level.FINE, "Marked most recent task as complete: {0}", task);
        }
    }

    /**
     * Uncompletes task at the given index and prints confirmation.
     *
     * @param i Index of the task.
     * @throws BurhException If the index is invalid.
     * @return Task uncompletion confirmation string.
     */
    /**
     * Marks the task at the given index as incomplete and returns a confirmation message.
     *
     * @param index The 1-based index of the task to uncomplete.
     * @return Confirmation message.
     * @throws BurhException If the index is invalid or the task is already incomplete.
     */
    public String uncompleteTask(int index) {
        validateIndex(index);
        
        Task task = tasks.get(index - 1);
        
if (!task.isDone()) {
            throw new BurhException(BurhException.ErrorType.UNKNOWN_ERROR, 
                "Burh, this task isn't even done yet. What are you doing?");
        }
        
        task.uncomplete();
        LOGGER.log(Level.INFO, "Uncompleted task at index {0}: {1}", new Object[]{index, task});
        
        String[] reactions = {
            "Burh, really? Uncompleting tasks now?\n    " + task + "\nBurh, you're hopeless.",
            "*facepalm* Burh, I can't believe you're doing this.\n    " + task + "\nBurh, whatever.",
            "Burh... fine. Marked as not done.\n    " + task + "\nBurh, not that it matters."
        };
        
        return reactions[(int)(Math.random() * reactions.length)];
    }

    /**
     * Prints all tasks in the list in order.
     *
     * @return List of all task in string.
     */
    /**
     * Returns a formatted string listing all tasks.
     *
     * @return Formatted task list string.
     */
    public String listTasks() {
        if (tasks.isEmpty()) {
            return "Burh, your task list is empty. Finally, some peace and quiet.";
        }
        
        StringBuilder sb = new StringBuilder("Burh, here's your task list. Not that I care.\n");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append(i + 1).append(". ").append(tasks.get(i)).append("\n");
        }
        return sb.toString().trim();
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Checks if the task list contains the specified task.
     *
     * @param task The task to check for.
     * @return true if the task exists in the list, false otherwise.
     */
    public boolean containsTask(Task task) {
        return tasks.stream().anyMatch(t -> t.equals(task));
    }

    /**
     * Finds tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     * @throws BurhException If the keyword is empty or null.
     */
    /**
     * Finds tasks containing the given keyword in their description.
     *
     * @param keyword The keyword to search for.
     * @return A list of matching tasks.
     * @throws BurhException If the keyword is empty or null.
     */
    public List<Task> findTasks(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new BurhException(BurhException.ErrorType.MISSING_DESCRIPTION, 
                "Burh, you need to enter a search term");
        }
        
        String searchTerm = keyword.toLowerCase().trim();
        return tasks.stream()
            .filter(task -> task.getDescription().toLowerCase().contains(searchTerm))
            .collect(Collectors.toList());
    }
    
    /**
     * Searches for tasks containing the given keyword and returns a formatted string.
     * This is a convenience method that uses findTasks() and formats the results.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return Formatted string containing matching tasks, or a message if none found.
     * @throws BurhException If the keyword is empty or null.
     */
    public String findKeywordInTasks(String keyword) {
        // Create a new TaskList to store the filtered tasks
        TaskList results = new TaskList();

        // Filter the tasks by description and add them to the new TaskList
        this.tasks.stream()
                .filter(t -> t.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .forEach(results::addTaskQuiet);

        if (results.size() == 0) {
            return "Burh, no tasks found with '" + keyword + "'. Maybe try something else?";
        }
        
        // Return the filtered tasks as a formatted string
        return "Burh, here's what I found for '" + keyword + "':\n" + results.listTasks();
    }

    /**
     * Returns a list of strings representing tasks in their save format.
     *
     * @return List of task strings in save format.
     */
    public List<String> toFileStrings() {
        return tasks.stream()
            .map(Task::getSaveString)
            .collect(Collectors.toList());
    }
    
    /**
     * Returns a list of strings representing tasks in their save format.
     * This is an alias for toFileStrings() for backward compatibility.
     * 
     * @return List of task strings in save format.
     */
    public List<String> getStringList() {
        return toFileStrings();
    }

    /**
     * Validates that the given 1-based index is valid.
     *
     * @param index The index to validate.
     * @throws BurhException If the index is out of bounds.
     */
    private void validateIndex(int index) {
        if (index < 1 || index > tasks.size()) {
            throw new BurhException(BurhException.ErrorType.INVALID_INDEX, 
                String.format("Valid range is 1 to %d", tasks.size()));
        }
    }

    /**
     * Sorts the task list based on natural ordering (by description).
     *
     * @return Formatted string containing the sorted task list.
     */
    public String sortChrono() {
        Collections.sort(tasks);
        return listTasks();
    }

}
