import java.util.Scanner;

public class Ui {
    private Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void greet() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm EvansBot");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");
    }

    public void sayBye() {
        System.out.println("____________________________________________________________");
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println("____________________________________________________________");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

    public void close() {
        scanner.close();
    }

    public void showLine() {
        System.out.println("############################################################");
    }
}
