package moon.models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Represents a list of {@link Task} objects.
 * <p>
 * Provides basic operations for adding, retrieving, deleting,
 * and checking tasks. Serves as the in-memory storage structure
 * for the Moon chatbot.
 *
 * @see Task
 * @see Todo
 * @see Deadline
 * @see Event
 */
public class TaskList {
    private final ArrayList<Task> tasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Appends the specified task to the end of this task list.
     *
     * @param task Task to be appended
     */
    public void add(Task task) {
        tasks.add(task);
    }

    /**
     * Returns the task at the specified position in this list.
     *
     * @param index Zero-based index of the task to return
     * @return Task at the specified position
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public Task get(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in TaskList.get()";
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified position in this list.
     *
     * @param index Zero-based index of the task to remove
     * @return The removed task
     * @throws IndexOutOfBoundsException If the index is out of range
     */
    public Task delete(int index) {
        assert index >= 0 && index < tasks.size() : "Index out of bounds in TaskList.delete()";
        return tasks.remove(index);
    }

    /**
     * Returns the number of tasks in this list.
     *
     * @return Number of tasks
     */
    public int size() {
        return tasks.size();
    }

    /**
     * Returns whether this task list is empty.
     *
     * @return {@code true} if the list contains no tasks, {@code false} otherwise
     */
    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Finds all tasks whose names contain the given substring, ignoring case.
     * <p>
     * This method returns a list of {@link TaskMatch} objects, where each match
     * contains both the original 1-based index of the task in the list and the task itself.
     * <ul>
     *   <li>If the search string is empty, an empty list is returned.</li>
     *   <li>Matching is performed case-insensitively by converting both the
     *   task name and the query to lowercase.</li>
     * </ul>
     *
     * @param s the substring to search for
     * @return a list of {@link TaskMatch} objects representing tasks whose names include {@code s}
     */
    public List<TaskMatch> findByName(String s) {
        String query = s.toLowerCase();
        List<TaskMatch> matches = new ArrayList<>();

        if (s.isEmpty()) {
            return matches;
        }

        return IntStream.range(0, this.size())
                .filter(i -> this.get(i).getName().toLowerCase().contains(query))
                .mapToObj(i -> new TaskMatch(i + 1, tasks.get(i)))
                .toList();
    }

    /**
     * Formats a list of {@link TaskMatch} objects into a user-readable string.
     * <p>
     * Each task is displayed on its own line, showing its original index
     * and the task details (via {@link TaskMatch#toString()}).
     * If no matches are provided, a default "not found" message is returned.
     *
     * @param matches the list of matches to format
     * @return a formatted string representation of the matches, or a "not found" message if empty
     */
    public static String formatTaskMatchList(List<TaskMatch> matches) {
        if (matches.isEmpty()) {
            return "No matching tasks found.";
        }
        return matches.stream()
                .map(TaskMatch::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Returns a formatted string representation of this task list
     * for display to the user.
     * <p>
     * Format:
     * <pre>
     *   1. [T][ ] read book
     *   2. [D][X] return book (by: June 6th)
     *   3. [E][ ] project meeting (from: Aug 6th 2pm to: 4pm)
     * </pre>
     *
     * @return Formatted string of tasks with indices
     */
    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            output.append(String.format("  %d. %s\n", i + 1, tasks.get(i)));
        }
        return output.toString();
    }
}
