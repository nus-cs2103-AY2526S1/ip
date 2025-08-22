import java.util.ArrayList;
import java.util.Scanner;

public class Dobby {
    private static ArrayList<Task> userTasks = new ArrayList<>();

    public static void main(String[] args) throws InvalidTaskException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Dobby \n");
        System.out.println("What can I do for you? \n");

        boolean flag = true;

        while (flag) {
            System.out.print("> ");
            String input = sc.nextLine().trim();

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                flag = false;
            } else if (input.equalsIgnoreCase("list")) {
                listTasks();
            } else if (input.startsWith("mark")) {
                handleMark(input, true);
            } else if (input.startsWith("unmark")) {
                handleMark(input, false);
            } else if (input.startsWith("delete")) {
                deleteTask(input);
            } else if (input.startsWith("todo")) {
                try {
                    storeTask(new ToDo(input.substring(5).trim()));
                } catch (InvalidTaskException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                String[] parts = input.substring(9).split("/by");
                if (parts.length == 2) {
                    storeTask(new Deadline(parts[0].trim(), parts[1].trim()));
                }
            } else if (input.startsWith("event")) {
                String[] parts = input.substring(5).split("/from|/to");
                if (parts.length == 3) {
                    storeTask(new Event(parts[0].trim(), parts[1].trim(), parts[2].trim()));
                } else {
                    System.out.println("Invalid event format. Use: event <task> /from <start> /to <end>");
                }
            }
        }
    }

    private static void storeTask(Task task) throws InvalidTaskException {
        if (task.getDescription().isEmpty()) {
            throw new InvalidTaskException("Task desription cannot be empty!");
        }
        userTasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println("  " + task);
        System.out.println("Now you have " + userTasks.size() + " tasks in the list.\n");
    }

    private static void listTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < userTasks.size(); i++) {
            System.out.println((i + 1) + "." + userTasks.get(i));
        }
        System.out.println();
    }

    private static void handleMark(String input, boolean mark) {
        try {
            int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = userTasks.get(taskNum);
            if (mark) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done.\n");
            } else {
                task.markUndone();
                System.out.println("Nice! I've marked this task as undone.\n");
            }
        } catch (Exception e) {
            System.out.println("Invalid task number.");
        }
    }

    private static void deleteTask(String input) {
        try {
            int taskNum = Integer.parseInt(input.split(" ")[1]) - 1;
            Task task = userTasks.get(taskNum);
            userTasks.remove(taskNum);
            System.out.println("Noted. I've removed this task: ");
            System.out.println(task.toString());
            } catch (Exception e) {
                System.out.println("Invalid task number.");
        }
    }
}
