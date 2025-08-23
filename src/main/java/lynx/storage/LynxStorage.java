package lynx.storage;

import lynx.exception.LynxException;
import lynx.formatter.LynxDateManager;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.task.TodoTask;
import lynx.ui.LynxUI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

// Translates between the contents of task list and the data to be stored
public abstract class LynxStorage {

    public static List<String> unloadTasks() {
        List<String> taskStrings = new ArrayList<>();
        List<Task> tasks = LynxTaskList.getAllTasks();

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

    // When loading tasks, corrupted id and completion status are not treated as exceptions
    // The former does not matter since the ids of tasks are allocated on instantiation
    // The latter is defaulted to 'INCOMPLETE'.
    public static int loadTasks(List<String> tasks) {
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
            LynxUI.line();
            System.out.println("⚠️ Lynx skipped " + errorCount + " invalid task(s) during loading.");
        }
        return errorCount;
    }

    private static void loadTodo(String[] parts) throws LynxException {
        if (parts.length < 4) {
            throw new LynxException("");
        }
        String status = parts[1], name = parts[3];
        Task task = new TodoTask(name);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        LynxTaskList.addTask(task, false);
    }

    private static void loadDeadline(String[] parts) throws LynxException {
        if (parts.length < 5) {
            throw new LynxException("");
        }
        String status = parts[1], name = parts[3];
        LocalDateTime by = LynxDateManager.parseDateTime(parts[4].replace("by:", ""));
        Task task = new DeadlineTask(name, by);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        LynxTaskList.addTask(task, false);
    }

    private static void loadEvent(String[] parts) throws LynxException {
        if (parts.length < 6) {
            throw new LynxException("");
        }
        String status = parts[1], name = parts[3];
        LocalDateTime from = LynxDateManager.parseDateTime(parts[4].replace("from:", "")),
                to = LynxDateManager.parseDateTime(parts[5].replace("to:", ""));
        Task task = new EventTask(name, from, to);
        if (status.equals("COMPLETE")) {
            task.setComplete();
        }
        LynxTaskList.addTask(task, false);
    }

}
