import java.util.Scanner;
import java.util.ArrayList;

public class SOFI {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<>();

        String greet = "____________________________________________________________\n" +
                "Hello! I'm SOFI\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);

        Scanner scanner = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = scanner.nextLine();

            try {
                if (userInput.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                }

                if (userInput.equals("list")) {
                    System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + ". " + tasks.get(i).toString());
                }
                    System.out.println("____________________________________________________________");
                }

                else if (userInput.startsWith("todo")) {
                    String taskDescription = userInput.length() >= 5 ? userInput.substring(5).trim() : "";
                    if (taskDescription.isEmpty()) {
                        throw new SofiException("A todo needs a description. Try: todo read book");
                    }
                    tasks.add(new Todo(taskDescription));
                    System.out.println("____________________________________________________________");
                    System.out.println("Added this task:\n   " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                else if (userInput.startsWith("deadline")) {
                    if (!userInput.contains(" /by ")) {
                        throw new SofiException("Deadlines must include /by. Example: deadline return book /by Sunday");
                    }
                    String[] parts = userInput.split(" /by ", 2);
                    String taskDescription = parts[0].length() >= 9 ? parts[0].substring(9).trim() : "";
                    String by = parts[1].trim();
                    if (taskDescription.isEmpty()) {
                        throw new SofiException("The deadline description cannot be empty.");
                    }
                    if (by.isEmpty()) {
                        throw new SofiException("The /by time cannot be empty.");
                    }
                    tasks.add(new Deadline(taskDescription, by));
                    System.out.println("____________________________________________________________");
                    System.out.println("Added this task:\n   " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                else if (userInput.startsWith("event")) {
                    if (!userInput.contains(" /from ") || !userInput.contains(" /to ")) {
                        throw new SofiException("Events need /from and /to. Example: event team meeting /from Mon 2pm /to Mon 3pm");
                    }
                    String[] parts = userInput.split(" /from ", 2);
                    String taskDescription = parts[0].length() >= 6 ? parts[0].substring(6).trim() : "";
                    String fromTo = parts[1];
                    String[] fromToParts = fromTo.split(" /to ", 2);
                    String from = fromToParts[0].trim();
                    String to = fromToParts[1].trim();
                    if (taskDescription.isEmpty()) {
                        throw new SofiException("The event description cannot be empty.");
                    }
                    if (from.isEmpty() || to.isEmpty()) {
                        throw new SofiException("Both /from and /to times must be provided.");
                    }
                    tasks.add(new Event(taskDescription, from, to));
                    System.out.println("____________________________________________________________");
                    System.out.println("Added this task:\n   " + tasks.get(tasks.size() - 1).toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                else if (userInput.startsWith("mark")) {
                    String[] tokens = userInput.split(" ", 2);
                    if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                        throw new SofiException("Please provide a task number to mark. Example: mark 2");
                    }
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(tokens[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SofiException("That doesn't look like a number. Try: mark 2");
                    }
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new SofiException("Task number out of range. You have " + tasks.size() + " task(s).");
                    }
                    Task task = tasks.get(taskNumber);
                    task.markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("Nice! I've marked this task as done:\n" + "   " + task.toString());
                    System.out.println("____________________________________________________________");
                }

                else if (userInput.startsWith("unmark")) {
                    String[] tokens = userInput.split(" ", 2);
                    if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                        throw new SofiException("Please provide a task number to unmark. Example: unmark 2");
                    }
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(tokens[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SofiException("That doesn't look like a number. Try: unmark 2");
                    }
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new SofiException("Task number out of range. You have " + tasks.size() + " task(s).");
                    }
                    Task task = tasks.get(taskNumber);
                    task.markAsNotDone();
                    System.out.println("____________________________________________________________");
                    System.out.println("OK, I've marked this task as not done yet:\n" + "   " + task.toString());
                    System.out.println("____________________________________________________________");
                }

                else if (userInput.startsWith("delete")) {
                    String[] tokens = userInput.split(" ", 2);
                    if (tokens.length < 2 || tokens[1].trim().isEmpty()) {
                        throw new SofiException("Please provide the task number to delete. Example: delete 3");
                    }
                    int taskNumber;
                    try {
                        taskNumber = Integer.parseInt(tokens[1].trim()) - 1;
                    } catch (NumberFormatException e) {
                        throw new SofiException("That doesn't look like a number. Try: delete 3");
                    }
                    if (taskNumber < 0 || taskNumber >= tasks.size()) {
                        throw new SofiException("Task number out of range. You have " + tasks.size() + " task(s).");
                    }
                    Task removed = tasks.remove(taskNumber);
                    System.out.println("____________________________________________________________");
                    System.out.println("Noted. I've removed this task:\n  " + removed.toString());
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }

                else {
                    throw new SofiException("I don't recognize that command. Try: list, todo, deadline, event, mark, unmark, bye");
                }
            } catch (SofiException e) {
                System.out.println("____________________________________________________________");
                System.out.println("" + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
        scanner.close();
    }
}
