package lynx.storage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import objectclasses.exception.LynxException;
import objectclasses.formatter.LynxDateManager;
import objectclasses.task.DeadlineTask;
import objectclasses.task.EventTask;
import objectclasses.task.Task;
import objectclasses.task.TodoTask;

/**
 * Contains methods for loading and unloading tasks in the task list, using a string representation.
 */
public abstract class LynxStorage {

    /**
     * Creates a list of string representations corresponding to the tasks in the task list.
     *
     * @return List of strings representations.
     */
    public static List<String> unloadTasks() {
        List<String> taskStrings = new ArrayList<>();
        List<Task> tasks = LynxTaskList.getAllTasks().toList();

        for (Task task : tasks) {
            StringBuilder taskString = new StringBuilder();

            taskString.append(task.getType().name());
            taskString.append("|").append(task.getStatus().name());
            taskString.append("|").append(task.getId());
            taskString.append("|").append(task.getName());

            if (task instanceof DeadlineTask deadlineTask) {
                taskString.append("|by:").append(LynxDateManager.defaultDateTime(deadlineTask.getDeadline()));
            } else if (task instanceof EventTask eventTask) {
                taskString.append("|from:").append(LynxDateManager.defaultDateTime(eventTask.getStart()));
                taskString.append("|to:").append(LynxDateManager.defaultDateTime(eventTask.getEnd()));
            }

            taskStrings.add(taskString.toString());
        }

        return taskStrings;
    }

    /**
     * Creates a list of tasks from a list of string representations and stores them in the task list.
     * <p>
     * Default value of completion status is <code>INCOMPLETE</code>.
     *
     * @param tasks List of tasks represented as strings, in the format "type|status|id|name|...".
     * @throws LynxException If errors occurred during task loading.
     */
    public static void loadTasks(List<String> tasks) throws LynxException {
        LynxTaskList.clearTasks(false);
        int errorCount = 0;

        for (String task : tasks) {
            try {
                if (task.isBlank()) {
                    continue;
                }
                String[] parts = task.split("\\|");
                String type = parts[0];
                switch (type) {
                case "TODO" -> loadTodo(parts);
                case "DEADLINE" -> loadDeadline(parts);
                case "EVENT" -> loadEvent(parts);
                default -> errorCount++;
                }
            } catch (PatternSyntaxException | ArrayIndexOutOfBoundsException | LynxException e) {
                errorCount++;
            }
        }

        if (errorCount > 0) {
            throw new LynxException("⚠️ Lynx skipped " + errorCount + " invalid task(s) during loading.");
        }
    }

    /**
     * Creates a <code>TodoTask</code> and adds it to the task list.
     *
     * @param parts Parsed representation of a <code>TodoTask</code>.
     * @throws LynxException If input is of invalid format.
     */
    private static void loadTodo(String[] parts) throws LynxException {
        if (parts.length < 4) {
            throw new LynxException("");
        }
        String status = parts[1];
        String name = parts[3];
        Task task = new TodoTask(name);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        LynxTaskList.addTask(task);
    }

    /**
     * Creates a <code>DeadlineTask</code> and adds it to the task list.
     *
     * @param parts Parsed representation of a <code>DeadlineTask</code>.
     * @throws LynxException If input is of invalid format or deadline is invalid.
     */
    private static void loadDeadline(String[] parts) throws LynxException {
        if (parts.length < 5) {
            throw new LynxException("");
        }
        String status = parts[1];
        String name = parts[3];
        LocalDateTime by = LynxDateManager.parseDateTime(parts[4].replace("by:", ""));
        Task task = new DeadlineTask(name, by);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        LynxTaskList.addTask(task);
    }

    /**
     * Creates an <code>EventTask</code> and adds it to the task list.
     *
     * @param parts Parsed representation of an <code>EventTask</code>.
     * @throws LynxException If input is of invalid format or start / end is invalid.
     */
    private static void loadEvent(String[] parts) throws LynxException {
        if (parts.length < 6) {
            throw new LynxException("");
        }
        String status = parts[1];
        String name = parts[3];
        LocalDateTime from = LynxDateManager.parseDateTime(parts[4].replace("from:", ""));
        LocalDateTime to = LynxDateManager.parseDateTime(parts[5].replace("to:", ""));
        Task task = new EventTask(name, from, to);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        LynxTaskList.addTask(task);
    }

}
