import java.util.ArrayList;
import java.util.Scanner;

enum TaskType {
    TODO("[T]"),
    DEADLINE("[D]"),
    EVENT("[E]");

    private final String label;

    TaskType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}

class EmptyDescriptionException extends Exception {
    public EmptyDescriptionException(String message) {
        super(message);
    }
}

class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) throws EmptyDescriptionException {
        if (description.trim().isEmpty()) {
            throw new EmptyDescriptionException("The description cannot be empty!");
        }
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
    public Todo(String description) throws EmptyDescriptionException {
        super(description);
    }

    @Override
    public String toString() {
        return TaskType.TODO.getLabel() + super.toString();
    }
}

class Deadline extends Task {
    protected String by;

    public Deadline(String description, String by) throws EmptyDescriptionException {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return TaskType.DEADLINE.getLabel() + super.toString() + " (by: " + by + ")";
    }
}

class Event extends Task {
    protected String from;
    protected String to;

    public Event(String description, String from, String to) throws EmptyDescriptionException {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return TaskType.EVENT.getLabel() + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

public class PoopieMeow {
    public static void main(String[] args) {
        String input;
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        
        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm PoopieMeow");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        while (true) {
            input = sc.nextLine();
            
            try {
                if (input.equals("bye")) {
                    System.out.println("____________________________________________________________");
                    System.out.println("   Bye. Hope to see you again soon!");
                    System.out.println("____________________________________________________________");
                    break;
                } else if (input.equals("list")) {
                    System.out.println("____________________________________________________________");
                    System.out.println(" Here are the tasks in your list:");
                    for (int i = 0; i < tasks.size(); i++) {
                        System.out.println(" " + (i + 1) + "." + tasks.get(i));
                    }
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("mark ")) {
                    int index = Integer.parseInt(input.substring(5)) - 1;
                    tasks.get(index).markAsDone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" Nice! I've marked this task as done:");
                    System.out.println("   " + tasks.get(index));
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("unmark ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    tasks.get(index).markAsUndone();
                    System.out.println("____________________________________________________________");
                    System.out.println(" OK, I've marked this task as not done yet:");
                    System.out.println("   " + tasks.get(index));
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("delete ")) {
                    int index = Integer.parseInt(input.substring(7)) - 1;
                    Task removedTask = tasks.remove(index);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Noted. I've removed this task:");
                    System.out.println("   " + removedTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.startsWith("todo ")) {
                    String description = input.substring(5);
                    Task newTask = new Todo(description);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.equals("todo")) {
                    throw new EmptyDescriptionException("The description of a todo cannot be empty!");
                } else if (input.startsWith("deadline ")) {
                    String[] parts = input.split(" /by ");
                    if (parts.length != 2) {
                        continue;
                    }
                    String description = parts[0].substring(9);
                    String by = parts[1];
                    Task newTask = new Deadline(description, by);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.equals("deadline")) {
                    throw new EmptyDescriptionException("The description of a deadline cannot be empty!");
                } else if (input.startsWith("event ")) {
                    String[] parts = input.split(" /from ");
                    if (parts.length != 2) {
                        continue;
                    }
                    String[] secondParts = parts[1].split(" /to ");
                    if (secondParts.length != 2) {
                        continue;
                    }
                    String description = parts[0].substring(6);
                    String from = secondParts[0];
                    String to = secondParts[1];
                    Task newTask = new Event(description, from, to);
                    tasks.add(newTask);
                    System.out.println("____________________________________________________________");
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + newTask);
                    System.out.println(" Now you have " + tasks.size() + " tasks in the list.");
                    System.out.println("____________________________________________________________");
                } else if (input.equals("event")) {
                    throw new EmptyDescriptionException("The description of an event cannot be empty!");
                } else if (input.trim().isEmpty()) {
                    throw new EmptyDescriptionException("Please enter a command!");
                } else {
                    System.out.println("____________________________________________________________");
                    System.out.println(" I don't understand '" + input + "'. Please try a valid command!");
                    System.out.println("____________________________________________________________");
                }
            } catch (EmptyDescriptionException e) {
                System.out.println("____________________________________________________________");
                System.out.println(" Oops! " + e.getMessage());
                System.out.println("____________________________________________________________");
            }
        }
    }
}
