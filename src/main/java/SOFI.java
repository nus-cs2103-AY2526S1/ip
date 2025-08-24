import java.util.Scanner;

public class SOFI {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;

        String greet = "____________________________________________________________\n" +
                "Hello! I'm SOFI\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            }

            if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println((i + 1) + ". " + tasks[i].toString());
                }
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("todo")) {
                String taskDescription = userInput.substring(5);
                tasks[taskCount++] = new Todo(taskDescription);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:\n   " + tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("deadline")) {
                String[] parts = userInput.split(" /by ");
                String taskDescription = parts[0].substring(9);
                String by = parts[1];
                tasks[taskCount++] = new Deadline(taskDescription, by);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:\n   " + tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("event")) {
                String[] parts = userInput.split(" /from ");
                String taskDescription = parts[0].substring(6);
                String fromTo = parts[1];
                String[] fromToParts = fromTo.split(" /to ");
                String from = fromToParts[0];
                String to = fromToParts[1];
                tasks[taskCount++] = new Event(taskDescription, from, to);
                System.out.println("____________________________________________________________");
                System.out.println("Got it. I've added this task:\n   " + tasks[taskCount - 1].toString());
                System.out.println("Now you have " + taskCount + " tasks in the list.");
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("mark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = tasks[taskNumber];
                task.markAsDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done:\n" + "   " + task.toString());
                System.out.println("____________________________________________________________");
            }

            else if (userInput.startsWith("unmark")) {
                int taskNumber = Integer.parseInt(userInput.split(" ")[1]) - 1;
                Task task = tasks[taskNumber];
                task.markAsNotDone();
                System.out.println("____________________________________________________________");
                System.out.println("OK, I've marked this task as not done yet:\n" + "   " + task.toString());
                System.out.println("____________________________________________________________");
            }
        }
    }
}
