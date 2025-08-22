import java.util.Scanner;

public class Buddy {
    public static void main(String[] args) {
        Task[] tasks = new Task[100];
        int taskCount = 0;
        
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Buddy");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");
        
        Scanner scanner = new Scanner(System.in);
        String input;
        
        while (true) {
            input = scanner.nextLine();
            
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                break;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println(" Here are the tasks in your list:");
                for (int i = 0; i < taskCount; i++) {
                    System.out.println(" " + (i + 1) + "." + tasks[i]);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("mark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(5)) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Task number is out of range.");
                        System.out.println("____________________________________________________________");
                    } else {
                        tasks[taskIndex].markAsDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" Nice! I've marked this task as done:");
                        System.out.println("   " + tasks[taskIndex]);
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please provide a valid task number.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("unmark ")) {
                try {
                    int taskIndex = Integer.parseInt(input.substring(7)) - 1;
                    if (taskIndex < 0 || taskIndex >= taskCount) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Task number is out of range.");
                        System.out.println("____________________________________________________________");
                    } else {
                        tasks[taskIndex].markAsNotDone();
                        System.out.println("____________________________________________________________");
                        System.out.println(" OK, I've marked this task as not done yet:");
                        System.out.println("   " + tasks[taskIndex]);
                        System.out.println("____________________________________________________________");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please provide a valid task number.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.equals("todo")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! The description of a todo cannot be empty.");
                    System.out.println("____________________________________________________________");
                } else {
                    Task newTask = new Todo(description);
                    tasks[taskCount] = newTask;
                    taskCount++;
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + taskCount + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.equals("deadline")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The description of a deadline cannot be empty.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("deadline ")) {
                try {
                    String[] parts = input.substring(9).split(" /by ");
                    if (parts.length < 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Please use the format: deadline <description> /by <time>");
                        System.out.println("____________________________________________________________");
                    } else {
                        String description = parts[0].trim();
                        String by = parts[1].trim();
                        Task newTask = new Deadline(description, by);
                        tasks[taskCount] = newTask;
                        taskCount++;
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + newTask);
                        System.out.println(" Now you have " + taskCount + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                    }
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please use the format: deadline <description> /by <time>");
                    System.out.println("____________________________________________________________");
                }
            } else if (input.equals("event")) {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! The description of an event cannot be empty.");
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("event ")) {
                try {
                    String[] parts = input.substring(6).split(" /from ");
                    if (parts.length < 2) {
                        System.out.println("____________________________________________________________");
                        System.out.println(" OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                        System.out.println("____________________________________________________________");
                    } else {
                        String description = parts[0].trim();
                        String[] timeParts = parts[1].split(" /to ");
                        if (timeParts.length < 2 || description.isEmpty() || timeParts[0].trim().isEmpty() || timeParts[1].trim().isEmpty()) {
                            System.out.println("____________________________________________________________");
                            System.out.println(" OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                            System.out.println("____________________________________________________________");
                        } else {
                            String from = timeParts[0].trim();
                            String to = timeParts[1].trim();
                            Task newTask = new Event(description, from, to);
                            tasks[taskCount] = newTask;
                            taskCount++;
                            System.out.println("____________________________________________________________");
                            System.out.println(" Got it. I've added this task:");
                            System.out.println("   " + newTask);
                            System.out.println(" Now you have " + taskCount + " tasks in the list.");
                            System.out.println("____________________________________________________________");
                        }
                    }
                } catch (Exception e) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" OOPS!!! Please use the format: event <description> /from <start> /to <end>");
                    System.out.println("____________________________________________________________");
                }
            } else {
                System.out.println("____________________________________________________________");
                System.out.println(" OOPS!!! I'm sorry, but I don't know what that means :-(");
                System.out.println("____________________________________________________________");
            }
        }
        
        scanner.close();
    }
}
