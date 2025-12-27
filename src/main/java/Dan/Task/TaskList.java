package Dan.Task;

import Dan.Storage.Storage;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

public class TaskList {
    Storage store;
    ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructs a new TaskList with the specified storage.
     * Initializes the task list by retrieving existing tasks from storage.
     *
     * @param store the Storage object used for data persistence
     */
    public TaskList(Storage store) {
        this.store = store;
        this.tasks = store.getTaskList();
    }

    /**
     * Adds a task to the task list and saves the updated list to storage.
     *
     * @param task the Task object to be added to the list
     */
    public void add(Task task) {
        tasks.add(task);
        store.writeData(tasks);
    }

    /**
     * Deletes a task at the specified index from the task list and saves the updated list to storage.
     * Uses 1-based indexing (index 1 corresponds to the first task).
     *
     * @param index the 1-based index of the task to be deleted
     */
    public void delete(int index) {
        tasks.remove(index - 1);
        store.writeData(tasks);
    }

    /**
     * Marks a task as done at the specified index.
     * Uses 1-based indexing (index 1 corresponds to the first task).
     * Prints an error message if the index is invalid.
     *
     * @param index the 1-based index of the task to be marked as done
     */
    public void mark(int index) {
        try {
            Task task = tasks.get(index - 1);
            task.mark();
            store.writeData(tasks);
        } catch(IndexOutOfBoundsException e) {
            System.out.println("Type in a valid index");
        }
    }

    /**
     * Retrieves a task at the specified index from the task list.
     * Uses 1-based indexing (index 1 corresponds to the first task).
     *
     * @param index the 1-based index of the task to retrieve
     * @return the Task object at the specified index
     */
    public Task getTask(int index) {
        return tasks.get(index - 1);
    }

    public ArrayList<Task> find(String searchStr) {
        return new ArrayList<>(this.tasks
                .stream()
                .filter(task -> task.getDescription().contains(searchStr))
                .toList());
    }

    public ArrayList<Task> getTasksToRemind(int daysFromNow) {
        LocalDate today = LocalDate.now();
        LocalDate cutOffDate = today.plusDays(daysFromNow);
        Task[] tasksToRemind = tasks.stream()
                .filter(task -> task.getTaskType() != TaskType.TODO)
                .filter(task -> !task.getReminderDate().isAfter(cutOffDate))
                .filter(task -> !task.getReminderDate().isBefore(today))
                .toArray(Task[]::new);
        return new ArrayList<>(Arrays.asList(tasksToRemind));
    }
    /**
     * Returns the number of tasks in the task list.
     *
     * @return the size of the task list
     */
    public int size() {
        return tasks.size();
    }
}
