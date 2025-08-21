import java.util.ArrayList;
import java.util.List;

public class LynxStorage {

    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

    public static void loadTasks(List<String> tasks) {
        for (int i = 0; i < tasks.size(); i++) {
            // Parse and make tasks
        }
    }

    public static List<String> unloadTasks() {
        List<String> tasks = new ArrayList<>();
        for (int i = 0; i < COMMANDS.size(); i++) {
            // Deconstruct tasks into lines
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
