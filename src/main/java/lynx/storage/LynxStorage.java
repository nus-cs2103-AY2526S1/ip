package lynx.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import objectclasses.exception.LynxException;
import objectclasses.exception.LynxFileException;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.Task;
import objectclasses.task.TodoTask;

/**
 * Contains methods for loading and unloading tasks in the task list, using a string representation.
 */
public abstract class LynxStorage {

    /**
     * Creates a list of string representations corresponding to the tasks in a task list.
     *
     * @param taskList List of tasks to unload.
     * @return List of strings representations.
     */
    public static List<String> unloadTasks(LynxTaskList taskList) {
        List<String> taskStrings = new ArrayList<>();
        List<Task> tasks = taskList.getAllTasks().toList();

        for (Task task : tasks) {
            taskStrings.add(task.storageRepresentation());
        }
        return taskStrings;
    }

    /**
     * Creates a list of tasks from a list of string representations and stores them in a task list.
     * <p>
     * Default value of completion status is <code>INCOMPLETE</code>.
     * @param tasks Tasks represented as strings, in the format "type|status|id|name|...".
     * @param taskList List to store tasks.
     * @throws LynxException If errors occurred during task loading.
     */
    public static void loadTasks(List<String> tasks, LynxTaskList taskList) throws LynxException {
        taskList.clearTasks();
        assert(taskList.getCount() == 0);
        int errorCount = 0;

        for (String task : tasks) {
            if (task.isBlank()) {
                continue;
            }
            try {
                String[] parts = task.split("\\|");
                String type = parts[0];
                switch (type) {
                case "TODO" -> taskList.addTask(TodoTask.of(parts));
                case "DEADLINE" -> taskList.addTask(DeadlineTask.of(parts));
                case "EVENT" -> taskList.addTask(EventTask.of(parts));
                default -> errorCount++;
                }
            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | LynxException e) {
                errorCount++;
            }
        }

        if (errorCount > 0) {
            throw LynxFileException.loadError(errorCount);
        }
    }

}
