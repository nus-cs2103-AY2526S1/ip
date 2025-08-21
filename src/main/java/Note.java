import java.util.ArrayList;
import java.util.Scanner;

public class Note {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();

        String logo =
                " _   _       _        \n"
                        + "| \\ | | ___ | |_ ___  \n"
                        + "|  \\| |/ _ \\| __/ _ \\ \n"
                        + "| |\\  | (_) | || (_) |\n"
                        + "|_| \\_|\\___/ \\__\\___/ \n";
        System.out.println("Hello from\n" + logo);
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Note");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();

            System.out.println("____________________________________________________________");
            if (input.equals("bye")) {
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                if (tasks.isEmpty()) {
                    System.out.println(" No tasks yet.");
                } System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
            } else if (input.startsWith("mark ")) {
                // Add input to tasks
                int index = Integer.parseInt(input.split(" ")[1]) - 1;
                if (index >= 0 && index < tasks.size()) {
                    tasks.get(index).markAsDone();
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(index));
                } else {
                    System.out.println(" Invalid task number!");
                }
            } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (index >= 0 && index < tasks.size()) {
                        tasks.get(index).markAsNotDone();
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks.get(index));
                    } else {
                        System.out.println(" Invalid task number!");
                    }
            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5); // remove "todo "
                Task t = new Todo(desc);
                tasks.add(t);
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + t);
                System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ", 2);
                if (parts.length < 2) {
                    System.out.println(" Invalid deadline format!");
                } else {
                    Task t = new Deadline(parts[0], parts[1]);
                    tasks.add(t);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                }
            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ", 3);
                if (parts.length < 3) {
                    System.out.println(" Invalid event format!");
                } else {
                    Task t = new Event(parts[0], parts[1], parts[2]);
                    tasks.add(t);
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + t);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                }
            } else {
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
            }

            System.out.println("____________________________________________________________");
        }
    }
}
