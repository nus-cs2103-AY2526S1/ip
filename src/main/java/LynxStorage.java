import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LynxStorage {

    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

    public static void clearTasks() {
        COMMANDS.clear();
        LynxUI.printBox("Removed all tasks." +
                "\nNow you have 0 tasks in the list.");
    }

    public static void loadTasks(List<String> tasks) {
        COMMANDS.clear();
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
        COMMANDS.add(t);
    }

    private static void loadDeadline(String[] parts) throws IllegalArgumentException, LynxException {
        if (parts.length < 5) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        LocalDateTime by = LynxDateManager.parseDateTime(parts[4].replace("by:", ""));
        Task t = new DeadlineTask(name, by);
        if (status.equals("COMPLETE")) t.setCompleted();
        COMMANDS.add(t);
    }

    private static void loadEvent(String[] parts) throws IllegalArgumentException, LynxException {
        if (parts.length < 6) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        LocalDateTime from = LynxDateManager.parseDateTime(parts[4].replace("from:", "")),
                to = LynxDateManager.parseDateTime(parts[5].replace("to:", ""));
        Task t = new EventTask(name, from, to);
        if (status.equals("COMPLETE")) t.setCompleted();
        COMMANDS.add(t);
    }

    public static List<String> unloadTasks() {
        List<String> tasks = new ArrayList<>();
        for (Task task : COMMANDS) {
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

            tasks.add(sb.toString());
        }
        return tasks;
    }

    public static void addTask(Task task) {
        COMMANDS.add(task);
        LynxUI.printBox("Added:\n     " + task + "\nNow you have " + COMMANDS.size() + " tasks in the list.");
    }

    public static void removeTask(Task task) {
        COMMANDS.remove(task);
        LynxUI.printBox("Removed:\n     " + task +
                "\nNow you have " + COMMANDS.size() + " tasks in the list.");
    }

    public static Task findTaskById(int id) throws LynxException {
        for (Task t : COMMANDS) {
            if (t.getId() == id) {
                return t;
            }
        }
        throw new LynxException("Task not found.");
    }

    public static Task findTaskByPosition(int position) throws LynxException {
        if (position < 1 || position > COMMANDS.size()) {
            throw new LynxException("Sorry, no task at that position.");
        }
        return COMMANDS.get(position - 1);
    }

    public static void printTasks() {
        LynxUI.line();
        System.out.println("Here are the tasks in your list:");
        if (COMMANDS.isEmpty()) {
            System.out.println("     (No tasks yet)");
        }
        for (int i = 0; i < COMMANDS.size(); i++) {
            System.out.println("     " + (i+1) + "." + COMMANDS.get(i));
        }
        LynxUI.line();
    }

    public static void printTasksOnDate(LocalDateTime target) {
        LynxUI.line();
        System.out.println("Tasks occurring on " + LynxDateManager.textDateTime(target) + ":");
        boolean found = false;
        for (int i = 0; i < COMMANDS.size(); i++) {
            Task t = COMMANDS.get(i);
            if (t instanceof DeadlineTask dt) {
                // compare only date part if input has no time
                if (isSameDay(dt.getDeadline(), target)) {
                    System.out.println("     " + (i+1) + "." + t);
                    found = true;
                }
            } else if (t instanceof EventTask et) {
                // target date within event range
                if (!target.isBefore(et.getStart()) && !target.isAfter(et.getEnd())) {
                    System.out.println("     " + (i+1) + "." + t);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("     (No tasks for this date)");
        LynxUI.line();
    }

    // Helper: checks if two LocalDateTimes are on the same day
    private static boolean isSameDay(LocalDateTime a, LocalDateTime b) {
        return a.toLocalDate().equals(b.toLocalDate());
    }

}
