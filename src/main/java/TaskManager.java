import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

    public static void addTodo(String input) throws LynxException {
        if (input.length() <= 4) {
            throw new MissingArgumentException("todo");
        }
        String name = input.substring(5).trim();
        addTask(new TodoTask(name));
    }

    public static void addDeadline(String input) throws LynxException {
        if (input.length() <= 8) {
            throw new MissingArgumentException("deadline");
        }
        String[] parts = input.substring(9).split("/by", 2);
        if (parts.length < 2) {
            throw new LynxException("Please specify a deadline using '/by'.");
        }
        String name = parts[0].trim();
        String by = parts[1].trim();
        addTask(new DeadlineTask(name, by));
    }

    public static void addEvent(String input) throws LynxException {
        if (input.length() <= 5) {
            throw new MissingArgumentException("event");
        }
        String[] nameSplit = input.substring(6).split("/from", 2);
        if (nameSplit.length < 2) {
            throw new LynxException("Please specify a start time using '/from'.");
        }
        String name = nameSplit[0].trim();
        String[] timeSplit = nameSplit[1].split("/to", 2);
        if (timeSplit.length < 2) {
            throw new LynxException("Please specify an end time using '/to'.");
        }
        String from = timeSplit[0].trim();
        String to = timeSplit[1].trim();
        addTask(new EventTask(name, from, to));
    }

    private static void addTask(Task task) {
        COMMANDS.add(task);
        LynxUI.printBox("Added:\n     " + task + "\nNow you have " + COMMANDS.size() + " tasks in the list.");
    }

    public static void handleMarkUnmark(String arg, boolean mark) throws LynxException {
        Task task = null;

        arg = arg.trim();
        if (arg.startsWith("id:")) {
            // Mark by unique ID
            try {
                int id = Integer.parseInt(arg.substring(3).trim());
                for (Task t : COMMANDS) {
                    if (t.getId() == id) {
                        task = t;
                        break;
                    }
                }
            } catch (NumberFormatException e) {
                throw new LynxException("Sorry, that isn't a valid ID.");
            }
        } else {
            // Mark by position in list
            try {
                int pos = Integer.parseInt(arg);
                if (pos < 1 || pos > COMMANDS.size()) {
                    throw new LynxException("Sorry, no task at that position.");
                }
                task = COMMANDS.get(pos - 1);
            } catch (NumberFormatException e) {
                throw new LynxException("Please provide a valid position number.");
            }
        }

        if (task == null) {
            throw new LynxException("Task not found.");
        }

        if (mark) {
            task.setCompleted();
            LynxUI.printBox("Excellent! Marked as done:\n     " + task.toString());
        } else {
            task.resetCompleted();
            LynxUI.printBox("Alright, marked as not done:\n     " + task.toString());
        }
    }

    public static void printListBox() {
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
