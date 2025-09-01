import java.util.Scanner;

public class Ui {
    private static final String LINE = "____________________________________________________________";
    private static final String LOGO = """
                 ____   _   _   _ _
                |  _ \\ / \\ | | | | |
                | |_) / _ \\| | | | |
                |  __/ ___ \\ |_| | |___
                |_| /_/   \\_\\___/|_____|
                """;
    private final Scanner sc = new Scanner(System.in);

    public void printOutput(String output) {
        System.out.println(LINE);
        System.out.println(output);
        System.out.println(LINE);
    }

    public String readCommand() {
        return sc.nextLine().trim();
    }

    public void greetUser() {
        printOutput("Hello I'm\n" + LOGO + "\nWhat can I do for you?");
    }

    public void byeUser() {
        printOutput("Goodbye! Paul will miss you :(");
    }

    public void showTasks(TaskList tasks) {
        printOutput("Here are the tasks in your list:\n" + tasks);
    }

    public void showTaskAdded(Task task, int total) {
        printOutput("Got it. I've added this task:\n" + task +
                "\nNow you have " + total + " tasks in the list.");
    }

    public void showTaskMarked(Task task) {
        printOutput("Nice! I've marked this task as done:\n" + task);
    }

    public void showTaskUnmarked(Task task) {
        printOutput("OK, I've marked this task as not done yet:\n" + task);
    }

    public void showTaskDeleted(Task task, int total) {
        printOutput("Noted. I've removed this task:\n" + task +
                "\nNow you have " + total + " tasks in the list.");
    }

    public void showLoadingError() {
        printOutput("Error in Loading Tasks!");
    }

    public void showError(String message) {
        printOutput(message);
    }
}
