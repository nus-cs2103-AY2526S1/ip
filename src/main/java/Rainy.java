import java.util.ArrayList;
import java.util.Scanner;

public class Rainy {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = "____________________________________________________________\n";
        ArrayList<Task> tasks = new ArrayList<>();

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
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println(line);

                } else if (input.startsWith("mark")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("OOPS!!! Please specify which task number to mark.");
                    }

                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new RainyException("OOPS!!! That task number doesn’t exist :-(");
                    }
                    tasks.get(taskNumber).markAsDone();
                    System.out.println(line
                            + "Nice! I've marked this task as done:\n  "
                            + tasks.get(taskNumber)
                            + "\n"
                            + line);

                } else if (input.startsWith("unmark")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("OOPS!!! Please specify which task number to unmark.");
                    }
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new RainyException("OOPS!!! That task number doesn’t exist :-(");
                    }
                    tasks.get(taskNumber).unmark();
                    System.out.println(line
                            + "OK, I've marked this task as not done yet:\n  "
                            + tasks.get(taskNumber)
                            + "\n"
                            +line);

                } else if (input.startsWith("todo")) {
                    if (input.length() <= 4) {
                        throw new RainyException("OOPS!!! The description of a todo cannot be empty.");
                    }
                    String desc = input.substring(4);
                    tasks.add(new Todo(desc));
                    System.out.println(line
                            + "Got it. I've added this task:\n  "
                            + tasks.getLast()
                            + "\nNow you have "
                            + tasks.size()
                            + " tasks in the list.\n"
                            + line);

                } else if (input.startsWith("deadline")) {
                    String[] parts = input.substring(8).split(" /by ");
                    if (parts.length < 2) {
                        throw new RainyException("OOPS!!! Please specify task and deadline.");
                    }
                    tasks.add(new Deadline(parts[0], parts[1]));
                    System.out.println(line
                            + "Got it. I've added this task:\n  "
                            + tasks.getLast()
                            + "\nNow you have "
                            + tasks.size()
                            + " tasks in the list.\n"
                            + line);

                } else if (input.startsWith("event")) {
                    String[] parts = input.substring(5).split(" /from | /to ");
                    if (parts.length < 3) {
                        throw new RainyException("OOPS!!! Please specify task from when to when.");
                    }
                    tasks.add(new Event(parts[0], parts[1], parts[2]));
                    System.out.println(line
                            + "Got it. I've added this task:\n  "
                            + tasks.getLast()
                            + "\nNow you have "
                            + tasks.size()
                            + " tasks in the list.\n"
                            + line);

                } else if (input.startsWith("delete")) {
                    if (input.split(" ").length < 2) {
                        throw new RainyException("OOPS!!! Please specify which task number to delete.");
                    }
                    int taskNumber = Integer.parseInt(input.split(" ")[1]) - 1;
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new RainyException("OOPS!!! That task number doesn’t exist.");
                    }
                    Task removedTask = tasks.remove(taskNumber);
                    System.out.println(line
                            + "Noted. I've removed this task:\n  "
                            + removedTask
                            + "\nNow you have "
                            + tasks.size()
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
