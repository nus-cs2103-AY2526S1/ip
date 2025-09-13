package hermione.utils;

import hermione.storage.TaskStorage;
import hermione.tasks.Task;
import hermione.tasks.TaskList;

/**
 * Utility class for UI-related functionalities in the Hermione application.
 */
public class UiUtils {

    /**
     * Returns a Hermione-style string to indicate that a task has been added to the
     * task list.
     * This string includes the task details and the total number of tasks in the
     * list.
     *
     * @param task    The task that has been added.
     * @param storage The storage containing the task list.
     * @return Formatted string with Hermione's personality indicating the task has
     *         been added and the current
     *         number of tasks in the list.
     */
    public static String getAddTaskString(Task task, TaskStorage storage) {
        TaskList tasks = storage.getTasks();
        return "Brilliant! I've added this task to your list:\n"
                + task.toString()
                + "\nNow you have %d tasks to complete. Time to get organized!".formatted(tasks.getSize());
    }

    /**
     * Returns a Hermione-style greeting message for the application.
     *
     * @param name The name of the application or bot.
     * @return Formatted greeting message with Hermione's personality.
     */
    public static String getGreeting(String name) {
        return "Hello! I'm %s, your personal assistant!\nWhat magical tasks can I help you organize today?"
                .formatted(name);
    }
}
