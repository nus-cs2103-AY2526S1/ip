import java.util.Scanner;
import java.util.ArrayList;

public class Romidas {
    public static class Task {
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
            return isDone ? "X": " ";
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
                    System.out.println(i + 1 + ".[" + task.getStatusIcon() + "] " + task.getDescription());
                }
            } else if (input.toLowerCase().startsWith("mark")) {
                System.out.println("Nice! I've marked this task as done:");
                int lastChar = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                Task task = store.get(lastChar);
                task.setIsDone(true);
                System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());

            } else if (input.toLowerCase().startsWith("unmark")) {
                System.out.println("OK, I've marked this task as not done yet:");
                int lastChar = Character.getNumericValue(input.charAt(input.length() - 1)) - 1;
                Task task = store.get(lastChar);
                task.setIsDone(false);
                System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
            } else{
                store.add(new Task(input));
                System.out.println("added: " + input);
            }
            System.out.println("____________________________________________________________");
            input = scanner.nextLine();

        }
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");


    }
}
