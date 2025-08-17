import java.util.Scanner;
import java.util.ArrayList;

public class TaskLynx {

    private static final String NAME = "TaskLynx";
    private static final String LINE = "____________________________________________________________";
    private static final ArrayList<Task> COMMANDS = new ArrayList<>(100);

    public static void main(String[] args) {
        hello();
        scanForCommands();
    }

    public static void hello() {
        System.out.println(LINE);
        System.out.println("Hello! I'm TaskLynx.");
        System.out.println("Your dependable assistant for tracking tasks, managing deadlines, and keeping your work organized.");
        System.out.println(LINE);
        System.out.println("How can I assist you with your tasks today?");
        System.out.println(LINE);
    }

    public static void bye() {
        printBox("Goodbye. I’ll be here whenever you need to stay on track.");
    }

    public static void scanForCommands() {
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Tasklynx is ready. Type your command:");

        while (true) {
            input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                bye();
                break;
            } else if (input.equalsIgnoreCase("list")) {
                printListBox(COMMANDS);
            } else if (input.startsWith("mark ")) {
                handleMarkUnmark(input.substring(5), true);
            } else if (input.startsWith("unmark ")) {
                handleMarkUnmark(input.substring(7), false);
            } else if (input.startsWith("todo ")) {
                String name = input.substring(5).trim();
                Task task = new TodoTask(name);
                COMMANDS.add(task);
                printBox("Added:\n     " + task + "\nNow you have " + COMMANDS.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split("/by", 2);
                if (parts.length < 2) {
                    printBox("Please specify a deadline using '/by'.");
                    continue;
                }
                String name = parts[0].trim();
                String by = parts[1].trim();
                Task task = new DeadlineTask(name, by);
                COMMANDS.add(task);
                printBox("Added:\n     " + task + "\nNow you have " + COMMANDS.size() + " tasks in the list.");
            } else if (input.startsWith("event ")) {
                String[] nameSplit = input.substring(6).split("/from", 2);
                if (nameSplit.length < 2) {
                    printBox("Please specify a start time using '/from'.");
                    continue;
                }
                String name = nameSplit[0].trim();
                String[] timeSplit = nameSplit[1].split("/to", 2);
                if (timeSplit.length < 2) {
                    printBox("Please specify an end time using '/to'.");
                    continue;
                }
                String from = timeSplit[0].trim();
                String to = timeSplit[1].trim();
                Task task = new EventTask(name, from, to);
                COMMANDS.add(task);
                printBox("Added:\n     " + task + "\nNow you have " + COMMANDS.size() + " tasks in the list.");
            } else if (!input.isEmpty()) {
                printBox("Sorry, I didn’t understand that command. Please try again or type 'list' to see available tasks.");
            }
        }

        scanner.close();
    }

    private static void handleMarkUnmark(String arg, boolean mark) {
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
                printBox("Sorry, that isn't a valid ID.");
                return;
            }
        } else {
            // Mark by position in list
            try {
                int pos = Integer.parseInt(arg);
                if (pos < 1 || pos > COMMANDS.size()) {
                    printBox("Sorry, no task at that position.");
                    return;
                }
                task = COMMANDS.get(pos - 1);
            } catch (NumberFormatException e) {
                printBox("Please provide a valid position number.");
                return;
            }
        }

        if (task == null) {
            printBox("Task not found.");
            return;
        }

        if (mark) {
            task.setCompleted();
            printBox("Excellent! Marked as done:\n     " + task.toString());
        } else {
            task.resetCompleted();
            printBox("Alright, marked as not done:\n     " + task.toString());
        }
    }

    private static void printBox(String message) {
        System.out.println(LINE);
        System.out.println(message);
        System.out.println(LINE);
    }

    private static void printListBox(ArrayList<Task> commands) {
        System.out.println(LINE);
        System.out.println("Here are the tasks in your list:");
        if (commands.isEmpty()) {
            System.out.println("     (No tasks yet)");
        }
        for (int i = 0; i < commands.size(); i++) {
            System.out.println("     " + (i+1) + "." + commands.get(i));
        }
        System.out.println(LINE);
    }

}

