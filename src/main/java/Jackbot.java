import java.io.*;
import java.util.*;

public class Jackbot {

    private static final String TASK_FILE_PATH = "./tasks.txt";

    // Helper function to print framed messages
    private static void printFramed(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    // Helper function to add task
    private static void addTask(List<Task> tasklist, Task task) {
        tasklist.add(task);
        printFramed("Got it. I've added this task\n"
                  + "  " + task + "\n"
                  + "Now you have " + tasklist.size() + " tasks in the list.");
    }

    // Helper function to handle empty description
    private static boolean checkDescription(String description) {
        if (description.length() == 0) {
          printFramed("ERROR: Task description cannot be empty");
          return false;
        }
        return true;
    }

    private static List<Task> loadTaskFileIfExists() {
        List<Task> tasklist = new ArrayList<>();
        File file = new File(TASK_FILE_PATH);
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            printFramed("Previously saved task file detected. Loading task list from file...");
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    Task t = Task.deserialize(line);
                    tasklist.add(t);
                } catch (Exception e) {
                    printFramed("ERROR: Failed to load tasks from task file, continuing with empty task list.");
                }
            }
        } catch (IOException e) {}
        return tasklist;
    }

    private static void saveTaskFile(List<Task> tasklist) {
        File file = new File(TASK_FILE_PATH);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Task t : tasklist) {
                writer.write(t.serialize());
                writer.newLine();
            }
        } catch (IOException e) {}
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasklist = loadTaskFileIfExists();

        // Start session
        printFramed("Hello! I'm Jackbot\nWhat can I do for you?");

        // Event loop
        while (true) {
            String input = sc.nextLine();

            if (input.equalsIgnoreCase("bye")) {
                break;
            } else if (input.equalsIgnoreCase("list")) {
                StringBuilder sb = new StringBuilder("Your previous entries:");
                for (int i = 0; i < tasklist.size(); i++) {
                    sb.append("\n").append(i + 1).append(". ").append(tasklist.get(i));
                }
                printFramed(sb.toString());
            } else if (input.toLowerCase().startsWith("mark ")) {
                Integer idx;

                // Parse index number
                try {
                    idx = Integer.parseInt(input.substring(5).trim());
                } catch (Exception e) {
                    printFramed("ERROR: Failed to parse task index number");
                    continue;
                }

                Task task;
                try {
                    task = tasklist.get(idx - 1);
                } catch (Exception e) {
                    printFramed("ERROR: Task not found");
                    continue;
                }
                task.mark();
                printFramed("Nice, I've marked this task as done:\n"
                          + "  " + task);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                Integer idx;
                try {
                    idx = Integer.parseInt(input.substring(5).trim());
                } catch (Exception e) {
                    printFramed("ERROR: Failed to parse task index number");
                    continue;
                }

                Task task;
                try {
                    task = tasklist.get(idx - 1);
                } catch (Exception e) {
                    printFramed("ERROR: Task not found");
                    continue;
                }
                task.unmark();
                printFramed("OK, I've marked this task as not done:\n"
                          + "  " + task);
            } else if (input.toLowerCase().startsWith("delete ")) {
                Integer idx;
                try {
                    idx = Integer.parseInt(input.substring(7).trim());
                } catch (Exception e) {
                    printFramed("ERROR: Failed to parse task index number");
                    continue;
                }

                try {
                    Task task = tasklist.remove(idx - 1);
                    printFramed("Noted. I've removed this task:\n"
                              + "  " + task + "\n"
                              + "Now you have " + tasklist.size() + " tasks in the list.");
                } catch (Exception e) {
                    printFramed("ERROR: Task not found");
                    continue;
                }
            } else if (input.toLowerCase().startsWith("todo ")) {
                input = input.substring(5);
                if (!checkDescription(input)) continue;
                addTask(tasklist, new Todo(input));
            } else if (input.toLowerCase().startsWith("deadline ")) {
                input = input.substring(9);
                if (!checkDescription(input)) continue;
                addTask(tasklist, new Deadline(input));
            } else if (input.toLowerCase().startsWith("event ")) {
                input = input.substring(6);
                if (!checkDescription(input)) continue;
                addTask(tasklist, new Event(input));
            } else {
                printFramed("ERROR: Command doesn't exist");
            }

            saveTaskFile(tasklist);
        }

        // End session
        printFramed(" Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
