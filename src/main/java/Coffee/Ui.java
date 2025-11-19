package Coffee;

import java.util.Scanner;

public class Ui {

    private final Scanner scanner = new Scanner(System.in);

    public String readCommand() {
        return scanner.nextLine();
    }

    public void showMessage(String msg) {
        System.out.println(msg);
    }

    public void showLoadingError() {
        System.out.println("There is an error with loading the file!");
    }
}
