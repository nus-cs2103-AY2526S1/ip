package benn.tasks;

import benn.exceptions.BennException;
import benn.storage.TaskStorage;

import java.io.IOException;
import java.util.List;

/**
 * Manages the user's tasks in Benn the Chatbot.
 *
 * <p>The {@code TaskManager} serves as a high-level interface between
 * chatbot commands and the underlying {@link benn.storage.TaskStorage}.
 * It provides methods to add, mark, unmark, delete, and list tasks,
 * ensuring that all changes are persisted to disk.</p>
 */
public class TaskManager {
    private final TaskStorage taskStorage;

    /**
     * Constructs a new {@code TaskManager} and initializes the storage system.
     *
     * @throws IOException if the storage file cannot be created or accessed
     * @throws BennException if the storage file path is invalid
     */
    public TaskManager() throws IOException, BennException {
        this.taskStorage = TaskStorage.start();
    }

    /**
     * Adds a new {@link Todo} task with the given description.
     *
     * @param description the description of the todo task
     * @return the newly created {@code Todo}
     * @throws IOException if writing to storage fails
     */
    public Todo addTodo(String description) throws IOException {
        Todo todo = new Todo(description, false);
        this.taskStorage.add(todo);
        return todo;
    }

    /**
     * Adds a new {@link Deadline} task with the given description and due date/time.
     *
     * @param description the description of the deadline
     * @param datetimeDue the due date/time string in {@code dd/MM/yyyy HH:mm} format
     * @return the newly created {@code Deadline}
     * @throws IOException if writing to storage fails
     * @throws BennException if the date/time string is invalid
     */
    public Deadline addDeadline(String description, String datetimeDue) throws IOException, BennException {
        Deadline deadline = new Deadline(description, datetimeDue, false);
        this.taskStorage.add(deadline);
        return deadline;
    }

    /**
     * Adds a new {@link Event} task with the given description, start date/time, and end date/time.
     *
     * @param description the description of the event
     * @param startDateTime the start date/time string in {@code dd/MM/yyyy HH:mm} format
     * @param endDateTime the end date/time string in {@code dd/MM/yyyy HH:mm} format
     * @return the newly created {@code Event}
     * @throws IOException if writing to storage fails
     * @throws BennException if either date/time string is invalid
     */
    public Event addEvent(String description, String startDateTime, String endDateTime) throws IOException, BennException {
        Event event = new Event(description, startDateTime, endDateTime, false);
        this.taskStorage.add(event);
        return event;
    }

    /**
     * Returns the total number of tasks currently stored.
     *
     * @return the number of tasks
     */
    public int size() {
        return this.taskStorage.getTaskCount();
    }

    /**
     * Marks the specified task as done.
     *
     * @param taskNumber the 1-based index of the task
     * @return the updated task
     * @throws BennException if the index is invalid
     * @throws IOException if persisting the change fails
     */
    public Task markAsDone(int taskNumber) throws BennException, IOException {
        int index = this.retrieveIndexFrom(taskNumber);
        Task task = this.taskStorage.getTaskLocatedAt(index);
        task.markAsDone();
        this.taskStorage.flush();
        return task;
    }

    /**
     * Marks the specified task as not done.
     *
     * @param taskNumber the 1-based index of the task
     * @return the updated task
     * @throws BennException if the index is invalid
     * @throws IOException if persisting the change fails
     */
    public Task unmarkAsDone(int taskNumber) throws BennException, IOException {
        int index = this.retrieveIndexFrom(taskNumber);
        Task task = this.taskStorage.getTaskLocatedAt(index);
        task.unmarkAsDone();
        this.taskStorage.flush();
        return task;
    }

    /**
     * Deletes the specified task from the task list.
     *
     * @param taskNumber the 1-based index of the task
     * @return the deleted task
     * @throws IOException if writing to storage fails
     * @throws BennException if the index is invalid
     */
    public Task deleteTaskAt(int taskNumber) throws IOException, BennException {
        int index = retrieveIndexFrom(taskNumber);
        return this.taskStorage.removeTaskLocatedAt(index);
    }

    /**
     * Finds all tasks whose descriptions contain the given keyword
     * (case-insensitive).
     *
     * <p>This method delegates the search to the underlying
     * {@link benn.storage.TaskStorage}, returning all matching tasks
     * in the order they appear in storage.</p>
     *
     * @param keyword the keyword to search for within task descriptions
     * @return a list of {@link benn.tasks.Task} objects whose descriptions
     *         contain the keyword; the list may be empty if no matches are found
     */
    public List<Task> findAllTasksContaining(String keyword) {
        return this.taskStorage.findAllTasksContaining(keyword);
    }

    /**
     * Returns a string representation of the current task list.
     *
     * <p>If there are no tasks, a placeholder message is returned.
     * Otherwise, each task is listed with its 1-based index and formatted
     * string representation.</p>
     *
     * @return the formatted task list as a string
     */
    @Override
    public String toString() {
        int taskCount = this.taskStorage.getTaskCount();
        if (taskCount == 0) {
            return "     (no tasks yet)\n";
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskCount; i++) {
            Task task = this.taskStorage.getTaskLocatedAt(i);
            sb.append("     ")
                    .append(i + 1)
                    .append(". ")
                    .append(task)
                    .append("\n");
        }
        return sb.toString();
    }

    /**
     * Validates and converts a user-facing task number into a 0-based index.
     *
     * @param taskNumber the 1-based task number
     * @return the corresponding 0-based index
     * @throws BennException if the task number is less than 1 or greater than the task count
     */
    private int retrieveIndexFrom(int taskNumber) throws BennException {
        int taskCount = this.taskStorage.getTaskCount();
        if (taskNumber < 1) {
            throw new BennException("Invalid index; index must be >= 1");
        } else if (taskNumber > taskCount) {
            throw new BennException("Invalid index; index must be <= " + taskCount);
        }
        return taskNumber - 1;
    }
}
