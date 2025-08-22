package lynx.storage;

import lynx.exception.LynxException;
import lynx.formatter.LynxDateManager;
import lynx.task.DeadlineTask;
import lynx.task.EventTask;
import lynx.task.Task;
import lynx.ui.LynxUI;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// All methods directly interacting with the lynx.task.Task List
public class LynxTaskList {
    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

    public static int getCount() {
        return COMMANDS.size();
    }

    public static void clearTasks(boolean dialogue) {
        COMMANDS.clear();
        if (dialogue) {
            LynxUI.printBox("Removed all tasks." +
                    "\nNow you have 0 tasks in the list.");
        }
    }

    public static void addTask(Task task, boolean dialogue) {
        COMMANDS.add(task);
        if (dialogue) {
            LynxUI.printBox("Added:\n     " + task + "\nNow you have " + COMMANDS.size() + " tasks in the list.");
        }
    }

    public static void removeTask(Task task, boolean dialogue) {
        COMMANDS.remove(task);
        if (dialogue) {
            LynxUI.printBox("Removed:\n     " + task +
                    "\nNow you have " + COMMANDS.size() + " tasks in the list.");
        }
    }

    public static List<Task> getAllTasks() {
        return new ArrayList<>(COMMANDS);
    }

    public static Task findTaskById(int id) throws LynxException {
        for (Task t : COMMANDS) {
            if (t.getId() == id) {
                return t;
            }
        }
        throw new LynxException("lynx.task.Task not found.");
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

    public static boolean printTasksOnDate(LocalDateTime target) {
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
        return found;
    }

    // Helper: checks if two LocalDateTimes are on the same day
    private static boolean isSameDay(LocalDateTime a, LocalDateTime b) {
        return a.toLocalDate().equals(b.toLocalDate());
    }
}
