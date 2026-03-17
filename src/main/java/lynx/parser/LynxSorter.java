package lynx.parser;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import objectclasses.task.Task;

/**
 * Contains methods that sort task streams.
 */
public abstract class LynxSorter {

    /**
     * Returns the task with a given id.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param id Id of task to be retrieved.
     * @return Stream containing a single task.
     */
    public static Stream<Task> filterTasksById(Stream<Task> tasks, int id) {
        return tasks.filter(task -> task.getId() == id);
    }

    /**
     * Returns all tasks in a stream with a given keyword in its name.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param keyword Keyword used to filter tasks by name.
     * @return Task stream filtered by keyword.
     */
    public static Stream<Task> filterTasksByKeyword(Stream<Task> tasks, String keyword) {
        return tasks.filter(task -> task.getName().replaceAll("\\s+", "")
                .toLowerCase().contains(keyword.toLowerCase()));
    }

    /**
     * Returns all tasks in a stream with a given priority.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param priority Priority used to filter tasks.
     * @return Task stream filtered by priority.
     */
    public static Stream<Task> filterTasksByPriority(Stream<Task> tasks, int priority) {
        return tasks.filter(task -> task.getPriority() == (priority));
    }

    /**
     * Returns all tasks in a stream that are active on a given date.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param dateTime <code>LocalDateTime</code> object to filter tasks by date.
     * @return Task stream filtered by date.
     */
    public static Stream<Task> filterTasksByDate(Stream<Task> tasks, LocalDateTime dateTime) {
        return tasks.filter(task -> task.isActive(dateTime));
    }

    /**
     * Returns all tasks in a stream that match a given <code>Status</code>.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param status <code>Status</code> used to filter tasks.
     * @return Task stream filtered by status.
     */
    public static Stream<Task> filterTasksByStatus(Stream<Task> tasks, Task.Status status) {
        return tasks.filter(task -> task.getStatus().equals(status));
    }

    /**
     * Returns all tasks in a stream that match a given <code>TaskType</code>.
     *
     * @param tasks Stream of tasks to be filtered.
     * @param type <code>TaskType</code> used to filter tasks.
     * @return Task stream filtered by type.
     */
    public static Stream<Task> filterTasksByType(Stream<Task> tasks, Task.TaskType type) {
        return tasks.filter(task -> task.getType().equals(type));
    }

}
