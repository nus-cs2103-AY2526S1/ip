import java.util.ArrayList;
import java.util.Scanner;

public class Dobby {
    private static ArrayList<Task> userTasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Hello! I'm Dobby \n");
        System.out.println("What can I do for you? \n");
        boolean flag = true;

        while (flag) {
            System.out.print("> "); // prompt
            String input = sc.nextLine(); // read user input

            if (input.equalsIgnoreCase("bye")) {
                System.out.println("Bye! Hope to see you again soon!");
                flag = false;
            } else if (input.equalsIgnoreCase("list")) {
                Dobby.listTexts();
            } else if (input.startsWith("mark")) {
                String[] parts = input.split(" "); // ["mark", "2"]
                int taskNum = Integer.parseInt(parts[1]) - 1; //the number is in index 1
                Dobby.markTask(taskNum);
                System.out.println("Nice! I've marked this task as done.");
            } else if (input.startsWith("unmark")) {
                String[] parts = input.split(" "); // ["mark", "2"]
                int taskNum = Integer.parseInt(parts[1]) - 1; //the number is in index 1
                Dobby.unmarkTask(taskNum);
                System.out.println("Nice! I've marked this task as undone.");
            }
            else {
                Dobby.storeTasks(input);
            }
        }
    }

    public static void storeTasks(String userInput) {
        Task t = new Task(userInput);
        userTasks.add(t);
        System.out.print("added to task list:" + userInput + "\n");
    }

    public static void listTexts() {
        System.out.println("Here are the tasks in your list: \n");
        int counter = 0;
        for (Task t : userTasks) {
            counter++;
            System.out.println(counter + ". " + "[" + t.getStatus() + "] " + t.getDescription());
        }
    }

    public static void markTask(int taskNum) {
        Task toMark = userTasks.get(taskNum);
        toMark.markAsDone();
    }

    public static void unmarkTask(int taskNum) {
        Task toMark = userTasks.get(taskNum);
        toMark.markUndone();
    }
}
