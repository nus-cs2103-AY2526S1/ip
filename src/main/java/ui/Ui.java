package ui;

import java.util.Scanner;

public class Ui {
    public static final String HORIZONTAL_LINE = "____________________________________________________________";

    private Scanner s;

    public Ui() {
        s = new Scanner(System.in);
    }

    public void greet() {
        String greeting = "    Hello! I'm Clam!\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________\n";
        System.out.println(greeting);
    }

    public void bye() {
        chatbotPrint("Bye. Hope to see you again soon!");
    }

    public String getInput() {
        return s.nextLine();
    }

    public void printLine() {
        chatbotPrint(HORIZONTAL_LINE);
    }

    public void showError(String msg) {
        chatbotPrint("Error: " + msg);
    }

    public void chatbotPrint(String s) {
        System.out.println("    " + s);
    }

    public void close() {
        s.close();
    }
}
