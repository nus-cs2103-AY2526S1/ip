package meeseeks.ui;

import java.util.Scanner;

public class Ui {
    private Scanner sc = new Scanner(System.in);

    private String lastMessage = "";

    public void showWelcome() {
        String message = "Hello! I'm Mr.Meeseeks\nLook at me!\n";
        System.out.println(message);
        lastMessage = message;
    }

    public String readCommand() {
        return sc.nextLine();
    }

    public void showLine() {
        System.out.println("_____________________________");
    }

    public void showExit() {
        String message = "Bye. Hope to see you again soon!";
        System.out.println(message);
        lastMessage = message;
    }

    public void showError(String message) {
        String errorMessage = "Oh Geez! " + message;
        System.out.println(errorMessage);
        lastMessage = errorMessage;
    }

    public void showMessage(String message) {
        System.out.println(message);
        lastMessage = message;
    }
    
    public String getLastMessage() {
        return lastMessage;
    }
}
