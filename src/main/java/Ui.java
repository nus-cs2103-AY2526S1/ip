import java.util.List;
import java.util.Scanner;

public class Ui {
    private final String line = "____________________________________________________________\n";
    private final Scanner scanner;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void showGreeting() {
        System.out.println(line + "Hello! I'm Byte.\nWhat can I do for you?\n" + line);
    }

    public void showFarewell() {
        System.out.println("\t" + line + "\t" + "Bye, hope to see you again soon!\n" + "\t" + line);
    }

    public void showError(String message) {
        System.out.println("\t" + line + "\t" + message + "\n" + "\t" + line);
    }

    public void showTasks(List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        output.append("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n\t").append(i + 1).append(".").append(tasks.get(i).toString());
        }
        System.out.println("\t" + line + "\t" + output + "\n" + "\t" + line);
    }

    public void showAddedTask(Task task, int total) {
        System.out.println("\t" + line + "\t" + "Got it, I've added this task:\n\t  " + task + "\n\tNow you have " + total + " tasks in the list." + "\n" + "\t" + line);
    }

    public void showMarked(Task task) {
        System.out.println("\t" + line + "\t" + "Nice! I've marked this task as done:\n\t  " + task + "\n" + "\t" + line);
    }

    public void showUnmarked(Task task) {
        System.out.println("\t" + line + "\t" + "OK, I've marked this task as not done yet:\n\t  " + task + "\n" + "\t" + line);
    }

    public void showDeleted(Task removed, int total) {
        System.out.println("\t" + line + "\t" + "I have removed this task:\n\t  " + removed + "\n\tNow you have " + total + " tasks in the list." + "\n" + "\t" + line);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void closeScanner() {
        scanner.close();
    }

}


