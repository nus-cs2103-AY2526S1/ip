import java.util.Scanner;
import java.util.ArrayList;

public class Romidas {
    public abstract static class Task {
        protected String description;
        protected boolean isDone;

        public Task(String description) {
            this.description = description;
            this.isDone = false;
        }

        public String getDescription() {
            return description;
        }

        public void setIsDone(boolean isDone) {
            this.isDone = isDone;
        }

        public String getStatusIcon() {
            return isDone ? "[X]": "[ ]";
        }

        public abstract String getStatus();

        @Override
        public String toString() {
            return this.getStatus() + this.getStatusIcon() + " " + this.getDescription();
        }
    }

    public static class TodoTask extends Task {
        public TodoTask(String description) {
            super(description);
        }

        @Override
        public String getStatus() {
            return "[T]";
        }

    }

    public static class DeadlineTask extends Task {
        public DeadlineTask(String description) {
            super(description);
        }

        @Override
        public String getStatus() {
            return "[D]";
        }
    }

    public static class Event extends Task {
        public Event(String description) {
            super(description);
        }

        @Override
        public String getStatus() {
            return "[E]";
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> store = new ArrayList<>();
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Romidas");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
        String input = scanner.nextLine();
        while (!input.equalsIgnoreCase("bye")) {
            System.out.println("____________________________________________________________");
            if (input.equalsIgnoreCase("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < store.size(); i++) {
                    Task task = store.get(i);
                    System.out.println(i + 1 + "." + task.toString());
                }
            } else if (input.toLowerCase().startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int index = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                Task task = store.get(index);
                task.setIsDone(true);
                System.out.println("  " + task.toString());

            } else if (input.toLowerCase().startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int index = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                Task task = store.get(index);
                task.setIsDone(false);
                System.out.println("  " + task.toString());
            } else{
                System.out.println("Got it. I've added this task:");
                Task task = null;
                if (input.toLowerCase().startsWith("todo")) {
                    String description = input.substring(5);
                    task = new TodoTask(description);
                } else if (input.toLowerCase().startsWith("deadline")) {
                    String sub = input.substring(9);
                    String[] parts = sub.split(" /by ");
                    task = new DeadlineTask(parts[0] +  " (by: " + parts[1] + ")");
                } else if (input.toLowerCase().startsWith("event")) {
                    String sub = input.substring(6);
                    String[] parts = sub.split(" /from ");
                    String fromAndTo = parts[1];
                    String[] timeParts = fromAndTo.split(" /to ");
                    task = new Event(parts[0] +  " (from: " + timeParts[0] + " to: " +  timeParts[1] + ")");
                }
                store.add(task);
                System.out.println("  " + task.toString());
                System.out.println("Now you have " + store.size() + " tasks in your list.");
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");


    }
}
