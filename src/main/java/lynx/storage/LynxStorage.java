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

// Translates between the contents of task list and the data to be stored
public class LynxStorage {

    public static void loadTasks(List<String> tasks) {
        LynxTaskList.clearTasks(false);
        int errorCount = 0;
        for (String line : tasks) {
            try {
                if (line.isBlank()) continue;
                String[] parts = line.split("\\|");
                String type = parts[0];
                switch (type) {
                    case "TODO" -> loadTodo(parts);
                    case "DEADLINE" -> loadDeadline(parts);
                    case "EVENT" -> loadEvent(parts);
                    default -> errorCount++;
                }
            } catch (Exception e) {
                errorCount++;
            }
        }
        if (errorCount > 0) {
            LynxUI.line();
            System.out.println("⚠️ Lynx skipped " + errorCount + " invalid task(s) during loading.");
        }
    }

    private static void loadTodo(String[] parts) throws IllegalArgumentException {
        if (parts.length < 4) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        Task t = new TodoTask(name);
        if (status.equals("COMPLETE")) t.setCompleted();
        LynxTaskList.addTask(t, false);
    }

    private static void loadDeadline(String[] parts) throws IllegalArgumentException, LynxException {
        if (parts.length < 5) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        LocalDateTime by = LynxDateManager.parseDateTime(parts[4].replace("by:", ""));
        Task t = new DeadlineTask(name, by);
        if (status.equals("COMPLETE")) t.setCompleted();
        LynxTaskList.addTask(t, false);
    }

    private static void loadEvent(String[] parts) throws IllegalArgumentException, LynxException {
        if (parts.length < 6) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        LocalDateTime from = LynxDateManager.parseDateTime(parts[4].replace("from:", "")),
                to = LynxDateManager.parseDateTime(parts[5].replace("to:", ""));
        Task t = new EventTask(name, from, to);
        if (status.equals("COMPLETE")) t.setCompleted();
        LynxTaskList.addTask(t, false);
    }

    public static List<String> unloadTasks() {
        List<String> taskString = new ArrayList<>();
        List<Task> tasks = LynxTaskList.getAllTasks();
        for (Task task : tasks) {
            StringBuilder sb = new StringBuilder();

            sb.append(task.getType().name());
            sb.append("|").append(task.getStatus().name());
            sb.append("|").append(task.getId());
            sb.append("|").append(task.getName());

            if (task instanceof DeadlineTask dt) {
                sb.append("|by:").append(LynxDateManager.defaultDateTime(dt.getDeadline()));
            } else if (task instanceof EventTask et) {
                sb.append("|from:").append(LynxDateManager.defaultDateTime(et.getStart()));
                sb.append("|to:").append(LynxDateManager.defaultDateTime(et.getEnd()));
            }

            taskString.add(sb.toString());
        }
        return taskString;
    }

}
