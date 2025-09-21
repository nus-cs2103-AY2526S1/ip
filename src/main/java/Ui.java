import java.util.Scanner;
import java.util.ArrayList;

public class Ui {
    private Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String greet = "____________________________________________________________\n" +
                "Hello! I'm SOFI\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        System.out.println(greet);
    }

    public void showGoodbye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showError(String message) {
        System.out.println("____________________________________________________________");
        System.out.println(message);
        System.out.println("____________________________________________________________");
    }

    public void showTaskAdded(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Added this task:\n   " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showTaskList(ArrayList<Task> tasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void showTaskMarked(Task task, boolean isDone) {
        System.out.println("____________________________________________________________");
        if (isDone) {
            System.out.println("Nice! I've marked this task as done:\n" + "   " + task.toString());
        } else {
            System.out.println("OK, I've marked this task as not done yet:\n" + "   " + task.toString());
        }
        System.out.println("____________________________________________________________");
    }

    public void showTaskRemoved(Task task, int totalTasks) {
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:\n  " + task.toString());
        System.out.println("Now you have " + totalTasks + " tasks in the list.");
        System.out.println("____________________________________________________________");
    }

    public void showLoadingError() {
        System.out.println("____________________________________________________________");
        System.out.println("Couldn't load previous tasks. Starting fresh.");
        System.out.println("____________________________________________________________");
    }

    public void close() {
        scanner.close();
    }
}
