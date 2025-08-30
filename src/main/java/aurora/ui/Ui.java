package aurora.ui;

import java.util.Scanner;

public class Ui {
    private final Scanner scanner;

    public Ui(Scanner scanner) {
        this.scanner = scanner;
    }

    public void speak(String text) {
        System.out.println("Aurora: " + text);
    }

    public void speakIntro() {
        speak("Hello! I'm Aurora. What can I do for you?");
    }

    public void speakOutro() {
        speak("Bye. Hope to see you again soon!");
    }

    public String readInput() {
        String input = null;
        while (input == null || input.isEmpty()) {
            if (scanner.hasNextLine()) {
                input = scanner.nextLine();
            }
        }
        return input;
    }
}
