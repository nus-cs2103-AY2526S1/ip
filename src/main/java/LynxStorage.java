import java.util.ArrayList;
import java.util.List;

public class LynxStorage {

    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

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

    private static void loadTodo(String[] parts) {
        if (parts.length < 4) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        Task t = new TodoTask(name);
        if (status.equals("COMPLETE")) t.setCompleted();
        COMMANDS.add(t);
    }

    private static void loadDeadline(String[] parts) {
        if (parts.length < 5) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3], by = parts[4].replace("by:", "");
        Task t = new DeadlineTask(name, by);
        if (status.equals("COMPLETE")) t.setCompleted();
        COMMANDS.add(t);
    }

    private static void loadEvent(String[] parts) {
        if (parts.length < 6) throw new IllegalArgumentException();
        String status = parts[1], name = parts[3];
        String from = parts[4].replace("from:", ""),
                to = parts[5].replace("to:", "");
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
                sb.append("|by:").append(dt.getDeadline());
            } else if (task instanceof EventTask et) {
                sb.append("|from:").append(et.getStart());
                sb.append("|to:").append(et.getEnd());
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

}
