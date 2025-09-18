import java.util.List;
import java.util.Scanner;

public class Ui {
    private final Scanner sc = new Scanner(System.in);

    private void frame(String msg) {
        System.out.println("____________________________________________________________\n");
        System.out.println(msg);
        System.out.println("____________________________________________________________\n");
    }

    public void showWelcome() {
        frame("Hello! I'm Jackbot\nWhat can I do for you?");
    }

    public void showGoodbye() {
        frame("Bye. Hope to see you again soon!\n");
        sc.close();
    }

    public void showLoadingError() {
        frame("ERROR: Failed to load tasks from task file, continuing with empty task list.");
    }

    public void showError(String message) {
        frame("ERROR: " + message);
    }

    public void showInfo(String message) {
        frame(message);
    }

    public void showAdded(Task task, int newSize) {
        frame("Got it. I've added this task\n"
            + "  " + task + "\n"
            + "Now you have " + newSize + " tasks in the list.");
    }

    public void showDeleted(Task task, int newSize) {
        frame("Noted. I've removed this task:\n"
            + "  " + task + "\n"
            + "Now you have " + newSize + " tasks in the list.");
    }

    public void showMarked(Task task) {
        frame("Nice, I've marked this task as done:\n"
            + "  " + task);
    }

    public void showUnmarked(Task task) {
        frame("OK, I've marked this task as not done:\n"
            + "  " + task);
    }

    public void showList(List<Task> tasks) {
        StringBuilder sb = new StringBuilder("Your previous entries:");
        for (int i = 0; i < tasks.size(); i++) {
            sb.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        frame(sb.toString());
    }

    public boolean hasNextLine() {
        return sc.hasNextLine();
    }

    public String readLine() {
        return sc.nextLine();
    }
}
