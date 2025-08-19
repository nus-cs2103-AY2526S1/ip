import java.util.Scanner;

public class Rainy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        Task[] tasks = new Task[100];
        int taskCount = 0;

        System.out.println(line
                + "Hello! I'm Rainy\n"
                + "What can I do for you?\n"
                + line);

        while (true) {
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println(line + " Bye. Hope to see you again soon!\n" + line);
                break;

            } else if (input.equals("list")) {
                System.out.println(line + "Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i]);
                }
                System.out.println(line);

            } else if (input.startsWith("mark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println(line
                        + "Nice! I've marked this task as done:\n  "
                        + tasks[taskNumber]
                        + "\n"
                        + line);

            } else if (input.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                tasks[taskNumber].unmark();
                System.out.println(line
                        + "OK, I've marked this task as not done yet:\n  "
                        + tasks[taskNumber]
                        + "\n"
                        +line);

            } else if (input.startsWith("todo ")) {
                String desc = input.substring(5);
                tasks[taskCount] = new Todo(desc);
                taskCount++;
                System.out.println(line
                        + "Got it. I've added this task:\n  "
                        + tasks[taskCount-1]
                        + "\nNow you have "
                        + taskCount
                        + " tasks in the list.\n"
                        + line);

            } else if (input.startsWith("deadline ")) {
                String[] parts = input.substring(9).split(" /by ");
                tasks[taskCount] = new Deadline(parts[0], parts[1]);
                taskCount++;
                System.out.println(line
                        + "Got it. I've added this task:\n  "
                        + tasks[taskCount-1] + "\nNow you have "
                        + taskCount
                        + " tasks in the list.\n"
                        + line);

            } else if (input.startsWith("event ")) {
                String[] parts = input.substring(6).split(" /from | /to ");
                tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                taskCount++;
                System.out.println(line
                        + "Got it. I've added this task:\n  "
                        + tasks[taskCount-1]
                        + "\nNow you have "
                        + taskCount
                        + " tasks in the list.\n"
                        + line);

            } else {
                tasks[taskCount] = new Task(input);
                taskCount++;
                System.out.println(line + " added: " + input +"\n" + line);
            }
        }
        sc.close();
    }
}
