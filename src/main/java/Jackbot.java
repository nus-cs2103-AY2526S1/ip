import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Jackbot {

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

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<Task> tasklist = new ArrayList<>();

        // Start session
        printFramed("Hello! I'm Jackbot\nWhat can I do for you?\n");

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
                Integer idx = Integer.parseInt(input.substring(5).trim());
                Task task = tasklist.get(idx - 1);
                task.mark();
                printFramed("Nice, I've marked this task as done:\n"
                          + "  " + task);
            } else if (input.toLowerCase().startsWith("unmark ")) {
                Integer idx = Integer.parseInt(input.substring(7).trim());
                Task task = tasklist.get(idx - 1);
                task.unmark();
                printFramed("OK, I've marked this task as not done:\n"
                          + "  " + task);
            } else if (input.toLowerCase().startsWith("todo ")) {
                input = input.substring(5);
                addTask(tasklist, new Todo(input));
            } else if (input.toLowerCase().startsWith("deadline ")) {
                input = input.substring(9);
                addTask(tasklist, new Deadline(input));
            } else if (input.toLowerCase().startsWith("event ")) {
                input = input.substring(6);
                addTask(tasklist, new Event(input));
            }
        }

        // End session
        printFramed(" Bye. Hope to see you again soon!\n");
        sc.close();
    }
}
