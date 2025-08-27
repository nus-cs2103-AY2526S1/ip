package zell.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public String readInput() {
        return scanner.nextLine();
    }

    public void showMessage(String message) {
        System.out.println(formatMessage(message));
    }

    private static String formatMessage(String message) {
        return "________________________________________" +
                "__________________________________________________\n" +
                message +
                "\n__________________________________" +
                "________________________________________________________\n\n";
    }
}
