import java.util.Scanner;
import java.util.ArrayList;

public class Penguin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        System.out.println("Hello! I'm Penguin\nWhat can I do for you?\n");
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            try {
                if (line.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (line.equals("list")) {
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println((i + 1) + ". " + tasks.get(i));
                    }
                } else if (line.startsWith("mark ")) {
                    String num = line.substring(5).trim();
                    int idx = Integer.parseInt(num) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        Task task = tasks.get(idx);
                        task.setDone(true);
                        System.out.println("Nice! I've marked this task as done:\n" + task);
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("unmark ")) {
                    String num = line.substring(7).trim();
                    int idx = Integer.parseInt(num) - 1;
                    if (idx >= 0 && idx < tasks.size()) {
                        Task task = tasks.get(idx);
                        task.setDone(false);
                        System.out.println("Nice! I've marked this task as not done yet:\n" + task);
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("event")) {
                    String body = line.length() > 5 ? line.substring(5).trim() : "";
                    if (body.isEmpty()) {
                        throw new PenguinException("The description of an event cannot be empty.");
                    }
                    int fromPos = body.indexOf("/from");
                    int toPos = body.indexOf("/to");
                    if (fromPos != -1 && toPos != -1 && toPos > fromPos) {
                        String desc = body.substring(0, fromPos).trim();
                        String from = body.substring(fromPos + 5, toPos).trim();
                        String to = body.substring(toPos + 3).trim();
                        Task t = new Event(desc, from, to);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("deadline")) {
                    String body = line.length() > 8 ? line.substring(8).trim() : "";
                    if (body.isEmpty()) {
                        throw new PenguinException("The description of a deadline cannot be empty.");
                    }
                    int byPos = body.indexOf("/by");
                    if (byPos != -1) {
                        String desc = body.substring(0, byPos).trim();
                        String by = body.substring(byPos + 3).trim();
                        Task t = new Deadline(desc, by);
                        tasks.add(t);
                        System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                    } else {
                        System.out.println("invalid task");
                    }
                } else if (line.startsWith("todo")) {
                    String desc = line.length() > 4 ? line.substring(4).trim() : "";
                    if (desc.isEmpty()) {
                        throw new PenguinException("The description of a todo cannot be empty.");
                    }
                    Task t = new Todo(desc);
                    tasks.add(t);
                    System.out.println("Got it. I've added this task:\n  " + t + "\nNow you have " + tasks.size() + " tasks in the list.");
                } else {
                    throw new PenguinException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (PenguinException e) {
                System.out.println("OOPS!!! " + e.getMessage());
            }
        }
    }
}