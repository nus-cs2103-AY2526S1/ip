import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Sam {
    private static void printAdded(Task t, int count) {
        System.out.println("____________________________________________________________");
        System.out.println(" Got it. I've added this task:");
        System.out.println(" " + t);
        System.out.println(" Now you have " + count + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    private static void printError(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(" " + msg);
        System.out.println("____________________________________________________________");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Task> tasks = new ArrayList<>();

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Sam");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            String input = sc.nextLine().trim();
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;

                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("mark")) {
                    String[] tok = input.split("\\s+");
                    int idx = Integer.parseInt(tok[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) throw new SamException("OOPS!!! Invalid task number.");
                    tasks.get(idx).markDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println(" " + tasks.get(idx));
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("unmark")) {
                    String[] tok = input.split("\\s+");
                    int idx = Integer.parseInt(tok[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) throw new SamException("OOPS!!! Invalid task number.");
                    tasks.get(idx).unmark();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println(" " + tasks.get(idx));
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("delete")) {               // Level 6
                    String[] tok = input.split("\\s+");
                    int idx = Integer.parseInt(tok[1]) - 1;
                    if (idx < 0 || idx >= tasks.size()) throw new SamException("OOPS!!! Invalid task number.");
                    Task removed = tasks.remove(idx);                  // ArrayList shifts for you
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println(" " + removed);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");

                } else if (input.startsWith("todo")) {
                    String descr = input.substring(4).trim();
                    if (descr.isEmpty()) throw new EmptyDescriptionException("todo");
                    tasks.add(new Todo(descr));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("deadline")) {
                    String body = input.substring(8).trim();
                    if (body.isEmpty() || !body.contains("/by"))
                        throw new SamException("OOPS!!! Use: deadline <description> /by <time>");
                    String[] parts = body.split("/by", 2);
                    String descr = parts[0].trim();
                    String by = parts[1].trim();
                    if (descr.isEmpty() || by.isEmpty())
                        throw new SamException("OOPS!!! Use: deadline <description> /by <time>");
                    tasks.add(new Deadline(descr, by));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else if (input.startsWith("event")) {
                    String body = input.substring(5).trim();
                    if (body.isEmpty() || !body.contains("/from") || !body.contains("/to"))
                        throw new SamException("OOPS!!! Use: event <description> /from <start> /to <end>");
                    String[] p1 = body.split("/from", 2);
                    String[] p2 = p1[1].split("/to", 2);
                    String descr = p1[0].trim();
                    String from = p2[0].trim();
                    String to = p2[1].trim();
                    if (descr.isEmpty() || from.isEmpty() || to.isEmpty())
                        throw new SamException("OOPS!!! Use: event <description> /from <start> /to <end>");
                    tasks.add(new Event(descr, from, to));
                    printAdded(tasks.get(tasks.size() - 1), tasks.size());

                } else {
                    throw new UnknownCommandException();
                }
            } catch (SamException e) {
                printError(e.getMessage());
            } catch (NumberFormatException e) {
                printError("OOPS!!! Task number must be an integer.");
            }
        }

        sc.close();
    }
}
