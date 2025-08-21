import java.util.Scanner;

public class Sam {
    private static void printAdded(Task t, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + t);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Sam");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;

            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("mark ")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[idx].markDone();
                System.out.println("____________________________________________________________");
                System.out.println(" Nice! I've marked this task as done:");
                System.out.println(" " + tasks[idx]);
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("unmark ")) {
                int idx = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[idx].unmark();
                System.out.println("____________________________________________________________");
                System.out.println(" OK, I've marked this task as not done yet:");
                System.out.println(" " + tasks[idx]);
                System.out.println("____________________________________________________________");

            } else if (input.startsWith("todo ")) {
                String descr = input.substring(5).trim();
                tasks[taskCount++] = new Todo(descr);
                printAdded(tasks[taskCount - 1], taskCount);

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split("/by");
                String descr = parts[0].trim();
                String by = parts[1].trim();
                tasks[taskCount++] = new Deadline(descr, by);
                printAdded(tasks[taskCount - 1], taskCount);

            } else if (input.startsWith("event ")) {
                String body = input.substring(6);
                String[] p1 = body.split("/from");
                String descr = p1[0].trim();
                String[] p2 = p1[1].split("/to");
                String from = p2[0].trim();
                String to = p2[1].trim();
                tasks[taskCount++] = new Event(descr, from, to);
                printAdded(tasks[taskCount - 1], taskCount);

            } else {
                tasks[taskCount++] = new Todo(input); // treat as Todo
                printAdded(tasks[taskCount - 1], taskCount);
            }

        }

        sc.close();
    }
}
