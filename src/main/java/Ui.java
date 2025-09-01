import java.util.Scanner;

public class Ui {
    private static final String LINE = "__________________________________________";
    private Scanner scanner;
    private boolean isBye;

    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public boolean isBye() {
        return this.isBye;
    }

    public void greeting() {
        System.out.println("Hello from LunarBot!\n");
        System.out.println("Nice to meet you! What can I do for you?\n" + LINE);
    }

    public void goodbye() {
        System.out.println("Hope to see you soon!\n");
        this.isBye = true;
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public String readCommand() {
        System.out.println("Input: ");
        return scanner.nextLine();
    }
}
