import java.util.Scanner;

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); 
    }

    public void markAsDone() {
        isDone = true;
    }

    public void markAsUndone() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}

class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

public class PoopieMeow {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        Task[] tasks = new Task[100];
        int taskCount = 0;
        
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm PoopieMeow");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            input = sc.nextLine();
            
            if (input.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("   Bye. Hope to see you again soon!");
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
                int index = Integer.parseInt(input.substring(5)) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks[index]);
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("unmark ")) {
                int index = Integer.parseInt(input.substring(7)) - 1;
                if (index >= 0 && index < taskCount) {
                    tasks[index].markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks[index]);
                    System.out.println("____________________________________________________________");
                }
            } else if (input.startsWith("todo ")) {
                String description = input.substring(5);
                tasks[taskCount] = new Todo(description);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount]);
                System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println("____________________________________________________________");
                taskCount++;
            } else if (input.startsWith("deadline ")) {
                String[] parts = input.split(" /by ");
                if (parts.length == 2) {
                    String description = parts[0].substring(9);
                    String by = parts[1];
                    tasks[taskCount] = new Deadline(description, by);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + tasks[taskCount]);
                    System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                    taskCount++;
                }
            } else if (input.startsWith("event ")) {
                String[] parts = input.split(" /from ");
                if (parts.length == 2) {
                    String[] secondParts = parts[1].split(" /to ");
                    if (secondParts.length == 2) {
                        String description = parts[0].substring(6);
                        String from = secondParts[0];
                        String to = secondParts[1];
                        tasks[taskCount] = new Event(description, from, to);
                        System.out.println("____________________________________________________________");
                        System.out.println(" Got it. I've added this task:");
                        System.out.println("   " + tasks[taskCount]);
                        System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
                        System.out.println("____________________________________________________________");
                        taskCount++;
                    }
                }
            } else {
                tasks[taskCount] = new Todo(input);
                System.out.println("____________________________________________________________");
                System.out.println(" Got it. I've added this task:");
                System.out.println("   " + tasks[taskCount]);
                System.out.println(" Now you have " + (taskCount + 1) + " tasks in the list.");
                System.out.println("____________________________________________________________");
                taskCount++;
            }
        }
    }
}
