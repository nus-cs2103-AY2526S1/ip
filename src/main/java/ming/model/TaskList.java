package ming.model;

import java.time.LocalDateTime;
import java.util.List;

import ming.exception.MingException;

/**
 * Represents a list of tasks and provides methods to manipulate them.
 */
public class TaskList {
    private final List<Task> tasks;

    /**
     * Initializes an empty TaskList.
     */
    public TaskList() {
        this.tasks = new java.util.ArrayList<>();
    }

    /**
     * Initializes a TaskList with the given list of tasks.
     *
     * @param tasks List of tasks to initialize the TaskList with.
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param index The index of the task to mark as done (0-based).
     * @return The task that was marked as done.
     * @throws MingException If the index is out of bounds.
     */
    public Task mark(int index) throws MingException {
        checkIndex(index);
        Task task = tasks.get(index);
        task.markAsDone();
        return task;
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param index The index of the task to mark as not done (0-based).
     * @return The task that was marked as not done.
     * @throws MingException If the index is out of bounds.
     */
    public Task unmark(int index) throws MingException {
        checkIndex(index);
        Task task = tasks.get(index);
        task.markAsNotDone();
        return task;
    }

    /**
     * Adds a new Todo task to the list.
     *
     * @param description The description of the Todo task.
     * @return The newly added Todo task.
     */
    public Task addTodo(String description, List<String> tags) {
        Task task = new Todo(description, tags);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a new Deadline task to the list.
     *
     * @param description The description of the Deadline task.
     * @param by          The deadline date and time.
     * @return The newly added Deadline task.
     */
    public Task addDeadline(String description, LocalDateTime by, List<String> tags) {
        Task task = new Deadline(description, by, tags);
        tasks.add(task);
        return task;
    }

    /**
     * Adds a new Event task to the list.
     *
     * @param description The description of the Event task.
     * @param from        The start date and time of the event.
     * @param to          The end date and time of the event.
     * @return The newly added Event task.
     */
    public Task addEvent(String description, LocalDateTime from, LocalDateTime to, List<String> tags) {
        Task task = new Event(description, from, to, tags);
        tasks.add(task);
        return task;
    }

    /**
     * Deletes the task at the specified index from the list.
     *
     * @param index The index of the task to delete (0-based).
     * @return The task that was deleted.
     * @throws MingException If the index is out of bounds.
     */
    public Task delete(int index) throws MingException {
        System.out.println("Task size: " + tasks.size());
        checkIndex(index);
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Finds and returns a list of tasks that contain the specified keyword in their description.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks containing the keyword.
     */
    public List<Task> findByName(String keyword) {
        return tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .toList();
    }

    /**
     * Finds and returns a list of tasks that are tagged with the specified keyword.
     *
     * @param keyword The tag to search for in task tags.
     * @return A list of tasks containing the specified tag.
     */
    public List<Task> findByTag(String keyword) {
        return tasks.stream()
                .filter(task -> task.getTags().contains(keyword))
                .toList();
    }


    private void checkIndex(int i) throws MingException {
        if (i < 0 || i >= tasks.size()) {
            throw new MingException("Index out of bounds: " + (i + 1));
        }
    }
}
