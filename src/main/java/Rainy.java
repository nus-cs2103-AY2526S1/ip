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

            try {
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
                    if (input.split(" ").length < 2) {
                        throw new RainyException("OOPS!!! Please specify which task number to mark.");
                    }

                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new RainyException("OOPS!!! That task number doesn’t exist :-(");
                    }
                    tasks[taskNumber].markAsDone();
                    System.out.println(line
                            + "Nice! I've marked this task as done:\n  "
                            + tasks[taskNumber]
                            + "\n"
                            + line);

                } else if (input.startsWith("unmark")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("OOPS!!! Please specify which task number to unmark.");
                    }
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= taskCount) {
                        throw new RainyException("OOPS!!! That task number doesn’t exist :-(");
                    }
                    tasks[taskNumber].unmark();
                    System.out.println(line
                            + "OK, I've marked this task as not done yet:\n  "
                            + tasks[taskNumber]
                            + "\n"
                            +line);

                } else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new RainyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String desc = input.substring(4);
                    tasks[taskCount] = new Todo(desc);
                    taskCount++;
                    System.out.println(line
                            + "Got it. I've added this task:\n  "
                            + tasks[taskCount-1]
                            + "\nNow you have "
                            + taskCount
                            + " tasks in the list.\n"
                            + line);

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).split(" /by ");
                    if (parts.length < 2) {
                        throw new RainyException("OOPS!!! Please specify task and deadline.");
                    }
                    tasks[taskCount] = new Deadline(parts[0], parts[1]);
                    taskCount++;
                    System.out.println(line
                            + "Got it. I've added this task:\n  "
                            + tasks[taskCount-1] + "\nNow you have "
                            + taskCount
                            + " tasks in the list.\n"
                            + line);

                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new RainyException("OOPS!!! Please specify task from when to when.");
                    }
                    tasks[taskCount] = new Event(parts[0], parts[1], parts[2]);
                    taskCount++;
                    System.out.println(line
                            + "Got it. I've added this task:\n  "
                            + tasks[taskCount - 1]
                            + "\nNow you have "
                            + taskCount
                            + " tasks in the list.\n"
                            + line);
                } else {
                    throw new RainyException("OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (RainyException e) {
                System.out.println(line + e.getMessage() + "\n" + line);
            } catch (Exception e) {
                System.out.println(line + " Something went wrong: " + e.getMessage() + "\n" + line);
            }
        }
        sc.close();
    }
}
